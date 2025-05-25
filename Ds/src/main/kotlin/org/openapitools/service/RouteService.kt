package org.openapitools.service

import org.openapitools.model.converter.toRouteSegmentDTO
import org.openapitools.model.dto.RouteDTO
import org.openapitools.model.dto.RouteSegmentDTO
import org.openapitools.model.entity.Price
import org.openapitools.model.entity.components.WeekDay
import org.openapitools.repository.AirportRepository
import org.openapitools.repository.PriceRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class RouteService(
    private val priceRepository: PriceRepository,
    private val airportRepository: AirportRepository,
) {
    fun findRoutes(
        origin: String,
        destination: String,
        departureDate: LocalDate,
        lang: String,
        bookingClass: String?,
        maxConnections: Int?,
    ): List<RouteDTO> {
        val originAirports = airportRepository.findByCityOrAirportNameOrAirportCode(origin, lang)
        val destinationAirports = airportRepository.findByCityOrAirportNameOrAirportCode(destination, lang)
        if (originAirports.isEmpty() || destinationAirports.isEmpty()) {
            throw IllegalArgumentException("Origin or destination airport not found")
        }

        val weekday = WeekDay.fromLocalDate(departureDate)

        val routes = findConnections(
            originAirports[0].airportCode,
            destinationAirports[0].airportCode,
            weekday,
            LocalTime.MIN,
            bookingClass,
            0,
            maxConnections,
            mutableSetOf(),
            mutableListOf()
        )

        return mapToDto(routes, lang)
    }

    private fun findConnections(
        origin: String,
        destination: String,
        departureDay: WeekDay,
        earliestDeparture: LocalTime,
        bookingClass: String?,
        connectionCount: Int,
        maxConnections: Int?,
        visitedAirports: MutableSet<String>,
        currentRoute: MutableList<Price>
    ): List<List<Price>> {

        val earliestTotalMinutes = earliestDeparture.toSecondOfDay() / 60

        val allCandidates = priceRepository.findAllByDepartureAirport_AirportCodeAndDepartureDayIn(origin, listOf(departureDay, departureDay.next()))
            .filter { price ->
                !visitedAirports.contains(price.arrivalAirport.airportCode)
            }

        var possiblePrices = allCandidates.filter { price ->
            val currentDayMinutes = if (price.departureDay == departureDay) {
                price.departureTime.toSecondOfDay() / 60 - earliestTotalMinutes
            } else {
                (price.departureTime.toSecondOfDay() / 60) + (24 * 60 - earliestTotalMinutes)
            }

            currentDayMinutes in 0 until (23 * 60) &&
                    !visitedAirports.contains(price.arrivalAirport.airportCode)
        }

        if (bookingClass != null) {
            possiblePrices = possiblePrices.filter {
                it.fareConditions.name.equals(bookingClass, ignoreCase = true)
            }
        } else if (currentRoute.isNotEmpty()) {
            val prevClass = currentRoute.last().fareConditions
            val sameClassPrices = possiblePrices.filter { it.fareConditions == prevClass }

            possiblePrices = sameClassPrices.ifEmpty { possiblePrices }
        }

        possiblePrices = possiblePrices.groupBy { it.flightNumber to it.fareConditions }.map { (_, prices) -> prices.minByOrNull { it.amount }!! }



        val routes = mutableListOf<List<Price>>()

        for (price in possiblePrices) {
            val arrivalCode = price.arrivalAirport.airportCode

            val newVisited = visitedAirports.toMutableSet().apply {
                add(arrivalCode)
            }
            val newRoute = currentRoute.toMutableList().apply {
                add(price)
            }

            if (arrivalCode == destination) {
                routes.add(newRoute)
                continue
            }

            if (maxConnections != null && connectionCount >= maxConnections) {
                continue
            }

            val arrivalDay = price.arrivalDay
            val arrivalTime = price.arrivalTime

            val nextPossibleRoutes = mutableListOf<List<Price>>()

            val furtherRoutes = findConnections(
                origin = arrivalCode,
                destination = destination,
                departureDay = arrivalDay,
                earliestDeparture = arrivalTime.plusHours(1),
                bookingClass = bookingClass,
                connectionCount = connectionCount + 1,
                maxConnections = maxConnections,
                visitedAirports = newVisited,
                currentRoute = newRoute
            )

            routes.addAll(furtherRoutes)
        }

        return routes
    }

    private fun mapToDto(routes: List<List<Price>>, lang: String): List<RouteDTO> {
        return routes.map { priceList ->
            RouteDTO(priceListToDto(priceList, lang))
        }
    }

    private fun priceListToDto(prices: List<Price>, lang: String): List<RouteSegmentDTO> {
        return prices.map { it.toRouteSegmentDTO(lang) }
    }
}

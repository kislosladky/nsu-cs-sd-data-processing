package org.openapitools.service

import org.openapitools.model.converter.toRouteSegmentDTO
import org.openapitools.model.dto.RouteDTO
import org.openapitools.model.dto.RouteSegmentDTO
import org.openapitools.model.entity.Flight
import org.openapitools.repository.AirportRepository
import org.openapitools.repository.FlightRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class RouteService(
    private val flightRepository: FlightRepository,
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
        if (originAirports.isEmpty() or destinationAirports.isEmpty()) {
            throw IllegalArgumentException("origin or departure airport not found")
        }
        println("Origin: ${originAirports.get(0)}")
        println("Destination: ${destinationAirports.get(0)}")
        println("Deparute date is $departureDate")

        val routes = findConnections(
            originAirports[0].airportCode,
            destinationAirports[0].airportCode,
            departureDate.atStartOfDay(),
            bookingClass,
            0,
            maxConnections,
            mutableSetOf(),
            mutableListOf()
        )
        println("Found ${routes.size} routes")

        return mapToDto(routes, lang)
    }

    private fun findConnections(
        origin: String,
        destination: String,
        departureTime: LocalDateTime,
        bookingClass: String?,
        connectionCount: Int,
        maxConnections: Int?,
        visitedAirports: MutableSet<String>,
        currentRoute: MutableList<Flight>
    ): List<List<Flight>> {
        // Получаем рейсы из origin с заданной даты
        var possibleFlights = flightRepository.findAllByDepartureAirportAndScheduledDepartureBetween(
            origin,
            departureTime.toInstant(ZoneOffset.UTC),
            departureTime.plusHours(23).toInstant(ZoneOffset.UTC)
        ).filter { flight ->
            !visitedAirports.contains(flight.arrivalAirport.airportCode)
        }

        // Фильтрация по классу бронирования, если задан
        bookingClass?.let {
            possibleFlights = possibleFlights.filter { flight ->
                flight.aircraft.seats.any { seat ->
                    seat.fareConditions.name.equals(bookingClass, ignoreCase = true)
                }
            }
        }

        val routes = mutableListOf<List<Flight>>()

        for (flight in possibleFlights) {
            val arrivalCode = flight.arrivalAirport.airportCode

            val newVisited = visitedAirports.toMutableSet().apply {
                add(arrivalCode)
            }
            val newRoute = currentRoute.toMutableList().apply {
                add(flight)
            }

            // если достигли пункта назначения — добавляем маршрут
            if (arrivalCode == destination) {
                routes.add(newRoute)
                continue
            }

            // если превышен лимит стыковок — прекращаем
            if (maxConnections != null && connectionCount >= maxConnections) {
                continue
            }

            // Рекурсивный вызов: ищем дальше
            val nextDepartureTime = flight.scheduledArrival
                .atOffset(ZoneOffset.UTC)
                .toLocalDateTime()
                .plusHours(1) // добавляем минимум 1 час на пересадку

            val furtherRoutes = findConnections(
                origin = arrivalCode,
                destination = destination,
                departureTime = nextDepartureTime,
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


    private fun mapToDto(routes: List<List<Flight>>, lang: String): List<RouteDTO> {
        return routes.map {
            RouteDTO(flightListToDto(it, lang))
        }
    }

    private fun flightListToDto(flights: List<Flight>, lang: String): List<RouteSegmentDTO> {
        return flights.map { it.toRouteSegmentDTO(lang) }
    }
}
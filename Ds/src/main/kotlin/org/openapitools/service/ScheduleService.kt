package org.openapitools.service

import org.openapitools.model.converter.toSchedule
import org.openapitools.model.dto.ScheduledFlightDto
import org.openapitools.model.entity.Flight
import org.openapitools.repository.FlightRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val flightRepository: FlightRepository,
) {
    fun findScheduledFlights(
        airportCode: String,
        isOrigin: Boolean,
        lang: String,
        day: String?,
        departureArrivalTime: String?,
        flightNumber: String?
    ) : List<ScheduledFlightDto> {
        var spec: Specification<Flight> = FlightSearchService.isScheduled()
        spec = if (isOrigin) {
            spec.and(FlightSearchService.origin(airportCode))
        } else {
            spec.and(FlightSearchService.destination(airportCode))
        }

        FlightSearchService.arrivalDayOfWeek(day)?.let {
            spec = spec.and(it)
        }

        spec = if (isOrigin) {
            spec.and(FlightSearchService.timeOfDeparture(departureArrivalTime))
        } else {
            spec.and(FlightSearchService.timeOfArrival(departureArrivalTime))
        }

        FlightSearchService.flightNumber(flightNumber)?.let {
            spec = spec.and(it)
        }

        return flightRepository.findAll(spec).map { it.toSchedule(lang, isOrigin) }
    }
}
package org.openapitools.model.converter

import org.openapitools.model.dto.RouteSegmentDTO
import org.openapitools.model.dto.ScheduledFlightDto
import org.openapitools.model.entity.Flight
import java.time.ZoneId

fun Flight.toSchedule(lang: String, isOrigin: Boolean): ScheduledFlightDto {
    val departureZonedDateTime = this.scheduledArrival.atZone(ZoneId.systemDefault())
    val arrivalZonedDateTime = this.scheduledArrival.atZone(ZoneId.systemDefault())
    val oppositeAirport = if (isOrigin) this.arrivalAirport else this.departureAirport

    return ScheduledFlightDto(
        scheduledArrival = arrivalZonedDateTime.toLocalDateTime(),
        scheduledDeparture = departureZonedDateTime.toLocalDateTime(),
        flightNumber = this.flightNumber,
        oppositeAirportCode = oppositeAirport.airportCode,
        oppositeAirportName = oppositeAirport.airportName.inLanguage(lang),
        aircraftCode = this.aircraft.aircraftCode
    )
}

fun Flight.toRouteSegmentDTO(lang: String): RouteSegmentDTO {
    val departureTime = this.scheduledDeparture.atZone(ZoneId.systemDefault())
    val arrivalTime = this.scheduledArrival.atZone(ZoneId.systemDefault())
    return RouteSegmentDTO(
        scheduledDeparture = departureTime.toLocalDateTime(),
        scheduledArrival = arrivalTime.toLocalDateTime(),
        flightNumber = this.flightNumber,
        departureAirportCode = this.departureAirport.airportCode,
        arrivalAirportCode = this.arrivalAirport.airportCode,
        arrivalAirportName = this.arrivalAirport.airportName.inLanguage(lang),
        departureAirportName = this.departureAirport.airportName.inLanguage(lang),
        aircraftCode = this.aircraft.aircraftCode
    )
}

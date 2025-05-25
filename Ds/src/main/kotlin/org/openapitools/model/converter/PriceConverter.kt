package org.openapitools.model.converter

import org.openapitools.model.dto.RouteSegmentDTO
import org.openapitools.model.entity.Price

fun Price.toRouteSegmentDTO(lang: String): RouteSegmentDTO {
    return RouteSegmentDTO(
        scheduledDepartureDay = this.departureDay,
        scheduledArrivalDay = this.arrivalDay,
        scheduledDepartureTime = this.departureTime,
        scheduledArrivalTime = this.arrivalTime,
        flightNumber = this.flightNumber,
        departureAirportCode = this.departureAirport.airportCode,
        arrivalAirportCode = this.arrivalAirport.airportCode,
        arrivalAirportName = this.arrivalAirport.airportName.inLanguage(lang),
        departureAirportName = this.departureAirport.airportName.inLanguage(lang),
        aircraftCode = this.aircraft.aircraftCode,
        fareConditions = this.fareConditions,
    )
}
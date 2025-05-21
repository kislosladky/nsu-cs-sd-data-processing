package org.openapitools.model.dto

import java.time.LocalDateTime

data class RouteSegmentDTO(
    val scheduledDeparture: LocalDateTime,
    val scheduledArrival: LocalDateTime,
    val flightNumber: String,
    val departureAirportCode: String,
    val departureAirportName: String,
    val arrivalAirportCode: String,
    val arrivalAirportName: String,
    val aircraftCode: String,
)


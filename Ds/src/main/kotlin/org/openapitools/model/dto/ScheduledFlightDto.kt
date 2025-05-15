package org.openapitools.model.dto

import java.time.LocalDateTime

data class ScheduledFlightDto(
    val scheduledDeparture: LocalDateTime,
    val scheduledArrival: LocalDateTime,
    val flightNumber: String,
    val oppositeAirportCode: String,
    val oppositeAirportName: String,
    val aircraftCode: String,
)

package org.openapitools.model.dto

import java.time.LocalDate

data class FlightBookingDTO(
    val flightNumber: String,
    val flightDate: LocalDate,
)

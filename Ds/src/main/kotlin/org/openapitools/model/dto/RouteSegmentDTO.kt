package org.openapitools.model.dto

import org.openapitools.model.entity.components.FareConditionType
import org.openapitools.model.entity.components.WeekDay
import java.time.LocalTime

data class RouteSegmentDTO(
    val scheduledDepartureDay: WeekDay,
    val scheduledArrivalDay: WeekDay,
    val scheduledDepartureTime: LocalTime,
    val scheduledArrivalTime: LocalTime,
    val flightNumber: String,
    val departureAirportCode: String,
    val departureAirportName: String,
    val arrivalAirportCode: String,
    val arrivalAirportName: String,
    val aircraftCode: String,
    val fareConditions: FareConditionType,
)


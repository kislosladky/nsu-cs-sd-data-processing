package org.openapitools.controller

import org.openapitools.api.ScheduleApi
import org.openapitools.model.dto.ScheduledFlightDto
import org.openapitools.service.ScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ScheduleController(
    private val scheduleService: ScheduleService
) : ScheduleApi {
    override fun scheduleAirportCodeLangGet(
        isInbound: Boolean,
        airportCode: String,
        lang: String,
        day: String?,
        arrivalTime: String?,
        flightNumber: String?
    ): ResponseEntity<List<ScheduledFlightDto>> {
        return ResponseEntity.ok(scheduleService.findScheduledFlights(airportCode, isInbound, lang, day, arrivalTime, flightNumber))
    }
}
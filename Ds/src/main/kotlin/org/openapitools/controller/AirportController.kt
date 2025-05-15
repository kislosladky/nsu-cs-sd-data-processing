package org.openapitools.controller

import org.openapitools.service.AirportService
import org.openapitools.api.AirportsApi
import org.openapitools.model.dto.AirportInfoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AirportController(
    private val airportService: AirportService,
) : AirportsApi {
    override fun airportsLangGet(lang: String): ResponseEntity<List<AirportInfoDto>> {
        return ResponseEntity.ok(airportService.getAirportsInLang(lang));
    }
}
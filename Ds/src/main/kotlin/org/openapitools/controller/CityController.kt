package org.openapitools.controller

import org.openapitools.api.CitiesApi
import org.openapitools.model.dto.AirportInfoDto
import org.openapitools.model.dto.CityResponseDto
import org.openapitools.service.CityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController (
    private val cityService: CityService
) : CitiesApi {
    override fun citiesLangGet(lang: String): ResponseEntity<List<CityResponseDto>> {
        return ResponseEntity.ok(cityService.getAllCities(lang))
    }

    override fun citiesCityAirportsLangGet(city: String, lang: String): ResponseEntity<List<AirportInfoDto>> {
        return ResponseEntity.ok(cityService.citiesCityAirportsLangGet(city, lang))
    }
}
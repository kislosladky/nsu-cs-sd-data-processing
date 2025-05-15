package org.openapitools.service

import org.openapitools.model.converter.inLanguage
import org.openapitools.model.converter.toInfoDto
import org.openapitools.model.dto.AirportInfoDto
import org.openapitools.model.dto.CityResponseDto
import org.openapitools.repository.AirportRepository
import org.springframework.stereotype.Service

@Service
class CityService(
    private val airportRepository: AirportRepository,
) {
    fun getAllCities(lang: String) : List<CityResponseDto> {
        return airportRepository.findAll()
            .map{ CityResponseDto(it.city.inLanguage(lang)) }
    }

    fun citiesCityAirportsLangGet(city: String, lang: String): List<AirportInfoDto> {
        validateLanguage(lang)
        return airportRepository.findAllAirportsInCityInLang(city, lang).map { it.toInfoDto(lang) }
    }

    private fun validateLanguage(lang: String) {
        if (lang != "ru" && lang != "en") {
            throw IllegalArgumentException("Language should be 'ru' or 'en'")
        }
    }
}
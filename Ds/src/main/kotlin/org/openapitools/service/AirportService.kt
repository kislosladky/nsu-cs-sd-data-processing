package org.openapitools.service

import org.openapitools.model.converter.toInfoDto
import org.openapitools.model.dto.AirportInfoDto
import org.openapitools.repository.AirportRepository
import org.springframework.stereotype.Service

@Service
class AirportService(
    private val airportRepository: AirportRepository,
) {
    fun getAirportsInLang(lang: String) : List<AirportInfoDto>{
       return airportRepository.findAll().map{ it.toInfoDto(lang) }
   }
}
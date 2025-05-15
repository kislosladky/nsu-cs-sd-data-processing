package org.openapitools.model.converter

import org.openapitools.model.dto.AirportInfoDto
import org.openapitools.model.entity.Airport

fun Airport.toInfoDto(lang: String) : AirportInfoDto {
    return AirportInfoDto(
            airportCode = this.airportCode,
            airportName = this.airportName.inLanguage(lang),
        )

}
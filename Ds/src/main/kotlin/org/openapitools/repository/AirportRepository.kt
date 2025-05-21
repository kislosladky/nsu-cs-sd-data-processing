package org.openapitools.repository

import org.openapitools.model.entity.Airport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AirportRepository : JpaRepository<Airport, String> {
    @Query(
        value = """
            select * 
            from airports_data
            where city ->> :lang = :city
        """,
        nativeQuery = true
    )
    fun findAllAirportsInCityInLang(
            @Param("city") city: String,
            @Param("lang") lang: String
    ): List<Airport>

    @Query(
        """
            select ad.airport_code, ad.timezone, ad.airport_name, ad.city
            from airports_data ad
            where ad.city ->> :lang = :airportInfo or 
            ad.airport_name ->> :lang = :airportInfo or
            ad.airport_code = :airportInfo
        """, nativeQuery = true
    )
    fun findByCityOrAirportNameOrAirportCode(airportInfo: String, lang: String): List<Airport>
}
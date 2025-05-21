package org.openapitools.repository

import org.openapitools.model.entity.Flight
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.Instant
import java.time.LocalDateTime

interface FlightRepository : CrudRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    @Query(
        """
        select f from Flight f
        where f.departureAirport.airportCode = :departureAirport
          and f.scheduledDeparture between :scheduledDeparture and :scheduledDeparture2
    """
    )
    fun findAllByDepartureAirportAndScheduledDepartureBetween(
        @Param("departureAirport") departureAirport: String,
        @Param("scheduledDeparture") scheduledDeparture: Instant,
        @Param("scheduledDeparture2") scheduledDeparture2: Instant
    ): Collection<Flight>
}
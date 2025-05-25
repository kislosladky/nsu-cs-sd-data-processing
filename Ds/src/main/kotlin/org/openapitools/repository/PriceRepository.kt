package org.openapitools.repository

import org.openapitools.model.entity.Price
import org.openapitools.model.entity.components.FareConditionType
import org.openapitools.model.entity.components.WeekDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.time.LocalDateTime

interface PriceRepository : JpaRepository<Price, Long> {
//    @Query("""
//    SELECT s FROM Seat s
//    WHERE s.fareConditions = :fareConditions
//      AND s.id.aircraftCode = (
//        SELECT f.aircraft.aircraftCode FROM Flight f
//        WHERE f.flightNumber = :flightNo
//          AND f.scheduledDeparture = :scheduledDeparture
//      )
//      AND s.id.seatNumber NOT IN (
//        SELECT bp.seatNumber FROM BoardingPass bp
//        WHERE bp. = :flightNo
//          AND bp.flight.scheduledDeparture = :scheduledDeparture
//      )
//""")
//    fun findAvailableSeats(
//        @Param("flightNo") flightNo: String,
//        @Param("scheduledDeparture") scheduledDeparture: LocalDateTime,
//        @Param("fareConditions") fareConditions: String
//    ): List<String>


    fun findAllByDepartureAirport_AirportCodeAndDepartureDayIn(departureAirportCode: String, departureDay: Iterable<WeekDay>): Iterable<Price>
}
package org.openapitools.model.entity

import org.openapitools.model.entity.components.FareConditionType
import org.openapitools.model.entity.components.WeekDay
import java.math.BigDecimal
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "prices")
data class Price(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "flight_no")
    val flightNumber: String,
    @ManyToOne
    @JoinColumn(name = "departure_airport")
    val departureAirport: Airport,
    @ManyToOne
    @JoinColumn(name = "arrival_airport")
    val arrivalAirport: Airport,
    @Column(name = "fare_conditions")
    @Enumerated(EnumType.STRING)
    val fareConditions: FareConditionType,
    @Column(name = "amount")
    val amount: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "aircraft_code", nullable = false)
    val aircraft: Aircraft,
    @Column(name = "seat_no")
    val seatNumber: String,
    @Column(name = "departure_day")
    @Enumerated(EnumType.STRING)
    val departureDay: WeekDay,
    @Column(name = "departure_time")
    val departureTime: LocalTime,
    @Column(name = "arrival_day")
    @Enumerated(EnumType.STRING)
    val arrivalDay: WeekDay,
    @Column(name = "arrival_time")
    val arrivalTime: LocalTime,
)

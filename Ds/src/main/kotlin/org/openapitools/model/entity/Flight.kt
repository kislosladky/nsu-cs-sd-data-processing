package org.openapitools.model.entity

import java.time.Instant
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "flights")
data class Flight (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_id_gen")
    @SequenceGenerator(name = "flights_id_gen", sequenceName = "flights_flight_id_seq", allocationSize = 1)
    @Column(name = "flight_id", nullable = false)
    var id: Int,

    @Size(max = 6)
    @NotNull
    @Column(name = "flight_no", nullable = false, length = 6)
    var flightNumber: String,

    @NotNull
    @Column(name = "scheduled_departure", nullable = false)
    var scheduledDeparture: Instant,

    @NotNull
    @Column(name = "scheduled_arrival", nullable = false)
    var scheduledArrival: Instant,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_airport", nullable = false)
    var departureAirport: Airport,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrival_airport", nullable = false)
    var arrivalAirport: Airport,

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    val status: String,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aircraft_code", nullable = false)
    var aircraft: Aircraft,

    @Column(name = "actual_departure")
    val actualDeparture: Instant,

    @Column(name = "actual_arrival")
    val actualArrival: Instant,
)
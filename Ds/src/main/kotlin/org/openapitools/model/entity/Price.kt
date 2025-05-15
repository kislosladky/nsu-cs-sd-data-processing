package org.openapitools.model.entity

import org.openapitools.model.entity.components.FareConditionType
import java.math.BigDecimal
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
    @Column(name = "departure")
    val departure: String,
    @Column(name = "arrival")
    val arrival: String,
    @Column(name = "fare_conditions")
    @Enumerated(EnumType.STRING)
    val fareConditions: FareConditionType,
    @Column(name = "price")
    val price: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "aircraft_code", nullable = false)
    val aircraft: Aircraft,
    @Column(name = "seat_no")
    val seatNumber: String
)

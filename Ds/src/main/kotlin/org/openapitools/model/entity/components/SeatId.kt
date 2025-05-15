package org.openapitools.model.entity.components

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class SeatId(
    @Column(name = "aircraft_code")
    val aircraftCode: String,
    @Column(name = "seat_no")
    val seatNumber: String
) : Serializable

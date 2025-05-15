package org.openapitools.model.entity.components

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class TicketFlightId(
    @Column(name = "ticket_no")
    val ticketNumber: String,
    @Column(name = "flight_id")
    val flightId: Int
) : Serializable

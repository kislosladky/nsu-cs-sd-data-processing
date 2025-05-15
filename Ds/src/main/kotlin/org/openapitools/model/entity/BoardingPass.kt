package org.openapitools.model.entity

import org.openapitools.model.entity.components.BoardingPassId
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "boarding_passes")
data class BoardingPass(
    @EmbeddedId
    val id: BoardingPassId,

    @MapsId("id")
    @JoinColumns(
        JoinColumn(name = "ticket_no", referencedColumnName = "ticket_no", nullable = false),
        JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    )
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    val ticketFlights: TicketFlight,

    @NotNull
    @Column(name = "boarding_no", nullable = false)
    val boardingNumber: Int,

    @Size(max = 4)
    @NotNull
    @Column(name = "seat_no", nullable = false, length = 4)
    val seatNumber: String
)
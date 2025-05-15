package org.openapitools.model.entity.components

import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Embeddable
class BoardingPassId : Serializable {
    @Size(max = 13)
    @NotNull
    @Column(name = "ticket_no", nullable = false, length = 13)
    var ticketNo: String? = null

    @NotNull
    @Column(name = "flight_id", nullable = false)
    var flightId: Int? = null

    override fun hashCode(): Int = Objects.hash(ticketNo, flightId)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as BoardingPassId

        return ticketNo == other.ticketNo &&
                flightId == other.flightId
    }

    companion object {
        private const val serialVersionUID = 2235005966193026197L
    }
}
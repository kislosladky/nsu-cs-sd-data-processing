package org.openapitools.model.entity

import org.openapitools.model.entity.components.FareConditionType
import org.openapitools.model.entity.components.TicketFlightId
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "ticket_flights")
data class TicketFlight(
    @EmbeddedId
    val id: TicketFlightId,
    @Column(name = "fare_conditions")
    @Enumerated(EnumType.STRING)
    val fareConditions: FareConditionType,
    @Column(name = "amount")
    val amount: BigDecimal
)

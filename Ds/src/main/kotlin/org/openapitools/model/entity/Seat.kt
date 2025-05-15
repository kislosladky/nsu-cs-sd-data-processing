package org.openapitools.model.entity

import org.openapitools.model.entity.components.FareConditionType
import org.openapitools.model.entity.components.SeatId
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "seats")
data class Seat(
    @EmbeddedId
    val id: SeatId,
    @Column(name = "fare_conditions")
    @Enumerated(EnumType.STRING)
    val fareConditions: FareConditionType
)

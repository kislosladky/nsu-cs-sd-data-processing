package org.openapitools.model.entity

import org.openapitools.model.converter.MultiLanguageFieldConverter
import org.openapitools.model.entity.components.MultiLanguageField
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "aircrafts_data")
data class Aircraft(
    @Id
    @Size(max = 3)
    @Column(name = "aircraft_code", nullable = false, length = 3)
    val aircraftCode: String,

    @NotNull
    @Column(name = "range", nullable = false)
    val range: Int,

    @Column(name = "model", columnDefinition = "jsonb not null")
    @Convert(converter = MultiLanguageFieldConverter::class)
    val model: MultiLanguageField,

    @OneToMany
    @JoinColumn(name = "aircraft_code")
    val seats: MutableSet<Seat>,
)
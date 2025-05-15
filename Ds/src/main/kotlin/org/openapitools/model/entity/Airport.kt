package org.openapitools.model.entity

import org.openapitools.model.converter.MultiLanguageFieldConverter
import org.openapitools.model.entity.components.MultiLanguageField
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "airports_data")
data class Airport (
    @Id
    @Size(max = 3)
    @Column(name = "airport_code", length = 3)
    val airportCode: String,

    @Column(name = "timezone")
    val timezone: String,

    @Column(name = "airport_name", columnDefinition = "jsonb not null")
    @Convert(converter = MultiLanguageFieldConverter::class)
    val airportName: MultiLanguageField,
    @Column(name = "city", columnDefinition = "jsonb not null")
    @Convert(converter = MultiLanguageFieldConverter::class)
    val city: MultiLanguageField
    /*
         TODO [Reverse Engineering] create field to map the 'coordinates' column
         Available actions: Define target Java type | Uncomment as is | Remove column mapping
            @Column(name = "coordinates", columnDefinition = "point not null")
            open var coordinates: Any? = null
        */
)
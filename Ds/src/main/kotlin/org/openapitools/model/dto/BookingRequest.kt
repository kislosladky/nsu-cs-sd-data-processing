package org.openapitools.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param flights 
 * @param passengers 
 */
data class BookingRequest(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("flights", required = true) val flights: kotlin.collections.List<kotlin.String>,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("passengers", required = true) val passengers: kotlin.collections.List<Passenger>
    ) {

}


package org.openapitools.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param passengers 
 * @param ticketNumber 
 * @param bookRef 
 */
data class CheckInRequest(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("passengers", required = true) val passengers: kotlin.collections.List<Passenger>,

    @Schema(example = "null", description = "")
    @get:JsonProperty("ticketNumber") val ticketNumber: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("bookRef") val bookRef: kotlin.String? = null
    ) {

}


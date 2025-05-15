package org.openapitools.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class ScheduleApiTest {

    private val api: ScheduleApiController = ScheduleApiController()

    /**
     * To test ScheduleApiController.scheduleAirportCodeLangGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun scheduleAirportCodeLangGetTest() {
        val airportCode: kotlin.String = TODO()
        val isOrigin: kotlin.Boolean = TODO()
        val lang: kotlin.String = TODO()
        val day: kotlin.String? = TODO()
        val arrivalTime: kotlin.String? = TODO()
        val flightNumber: kotlin.String? = TODO()
        val response: ResponseEntity<Unit> = api.scheduleAirportCodeLangGet(airportCode, isOrigin, lang, day, arrivalTime, flightNumber)

        // TODO: test validations
    }
}

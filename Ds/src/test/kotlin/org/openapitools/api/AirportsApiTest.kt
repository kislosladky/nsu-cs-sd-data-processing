package org.openapitools.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class AirportsApiTest {

    private val api: AirportsApiController = AirportsApiController()

    /**
     * To test AirportsApiController.airportsLangGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun airportsLangGetTest() {
        val lang: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = api.airportsLangGet(lang)

        // TODO: test validations
    }
}

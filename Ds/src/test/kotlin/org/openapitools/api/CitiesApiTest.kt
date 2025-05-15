package org.openapitools.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class CitiesApiTest {

    private val api: CitiesApiController = CitiesApiController()

    /**
     * To test CitiesApiController.citiesCityAirportsLangGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun citiesCityAirportsLangGetTest() {
        val city: kotlin.String = TODO()
        val lang: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = api.citiesCityAirportsLangGet(city, lang)

        // TODO: test validations
    }

    /**
     * To test CitiesApiController.citiesLangGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun citiesLangGetTest() {
        val lang: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = api.citiesLangGet(lang)

        // TODO: test validations
    }
}

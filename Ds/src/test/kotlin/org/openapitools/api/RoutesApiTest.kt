package org.openapitools.api

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class RoutesApiTest {

    private val api: RoutesApiController = RoutesApiController()

    /**
     * To test RoutesApiController.routesGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun routesGetTest() {
        val origin: kotlin.String = TODO()
        val destination: kotlin.String = TODO()
        val departureDate: java.time.LocalDate = TODO()
        val lang: kotlin.String = TODO()
        val bookingClass: kotlin.String? = TODO()
        val maxConnections: kotlin.Int? = TODO()
        val response: ResponseEntity<Unit> = api.routesGet(origin, destination, departureDate, lang, bookingClass, maxConnections)

        // TODO: test validations
    }
}

package org.openapitools.api

import org.openapitools.model.dto.BookingRequest
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class BookingsApiTest {

    private val api: BookingsApiController = BookingsApiController()

    /**
     * To test BookingsApiController.bookingsPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun bookingsPostTest() {
        val bookingRequest: BookingRequest = TODO()
        val response: ResponseEntity<Unit> = api.bookingsPost(bookingRequest)

        // TODO: test validations
    }
}

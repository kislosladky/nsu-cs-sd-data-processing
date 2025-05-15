package org.openapitools.api

import org.openapitools.model.dto.CheckInRequest
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class CheckinApiTest {

    private val api: CheckinApiController = CheckinApiController()

    /**
     * To test CheckinApiController.checkinPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun checkinPostTest() {
        val checkInRequest: CheckInRequest = TODO()
        val response: ResponseEntity<Unit> = api.checkinPost(checkInRequest)

        // TODO: test validations
    }
}

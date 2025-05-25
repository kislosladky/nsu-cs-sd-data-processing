package org.openapitools.service

import org.openapitools.model.dto.BookingRequest
import org.openapitools.model.dto.BookingResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(

) {
    @Transactional
    fun createBookings(bookingRequest: BookingRequest): BookingResponse {
        for (passenger in bookingRequest.passengers) {
            for (flightBooking in bookingRequest.flights) {

            }
        }
        return BookingResponse(
            "ok"
        )
    }
}
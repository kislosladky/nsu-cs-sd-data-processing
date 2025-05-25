package org.openapitools.controller

import org.openapitools.api.BookingsApi
import org.openapitools.model.dto.BookingRequest
import org.openapitools.service.BookingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BookingController(
    private val bookingService: BookingService,
) : BookingsApi {
    override fun bookingsPost(bookingRequest: BookingRequest): ResponseEntity<Unit> {
        bookingService.createBookings(bookingRequest)
        return ResponseEntity.ok().build()
    }
}
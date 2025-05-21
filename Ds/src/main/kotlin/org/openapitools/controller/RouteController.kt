package org.openapitools.controller

import org.openapitools.api.RoutesApi
import org.openapitools.model.dto.RouteDTO
import org.openapitools.service.RouteService
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate


@RestController
class RouteController (
    private val routeService: RouteService,
): RoutesApi {
    override fun routesGet(
        origin: String,
        destination: String,
        departureDate: LocalDate,
        lang: String,
        bookingClass: String?,
        maxConnections: Int?
    ) : List<RouteDTO> {
        return routeService.findRoutes(
            origin,
            destination,
            departureDate,
            lang,
            bookingClass,
            maxConnections
        )
    }
}
package org.openapitools.service

import org.openapitools.model.entity.Airport
import org.openapitools.model.entity.Flight
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.Instant
import javax.persistence.criteria.Expression

@Service
object FlightSearchService{
    fun departureDayOfWeek(dow: String?): Specification<Flight>? {
        return if (dow.isNullOrBlank()) null else Specification { root, query, critBuilder ->
            val toCharExpr: Expression<String>  = critBuilder.function(
                "to_char", String::class.java,
                root.get<Instant>("scheduled_departure"),
                critBuilder.literal("Dy")
            )
            val lowerExpr = critBuilder.function("lower", String::class.java, toCharExpr)

            critBuilder.equal(lowerExpr, dow.lowercase())
        }
    }

    fun arrivalDayOfWeek(dow: String?): Specification<Flight>? {
        return if (dow.isNullOrBlank()) null else Specification { root, query, critBuilder ->
            val toCharExpr: Expression<String>  = critBuilder.function(
                "to_char", String::class.java,
                root.get<Instant>("scheduled_arrival"),
                critBuilder.literal("Dy")
            )
            val lowerExpr = critBuilder.function("lower", String::class.java, toCharExpr)

            critBuilder.equal(lowerExpr, dow.lowercase())
        }
    }

    fun timeOfArrival(arrivalTime: String?): Specification<Flight>? {
        return if (arrivalTime.isNullOrBlank()) null else Specification { root, query, criteriaBuilder ->
            val toCharExpr: Expression<String> = criteriaBuilder.function(
                "to_char", String::class.java,
                root.get<Instant>("scheduled_arrival"),
                criteriaBuilder.literal("HH24:MI:SS")
            )

            criteriaBuilder.equal(toCharExpr, arrivalTime)
        }
    }

    fun timeOfDeparture(departureTime: String?): Specification<Flight>? {
        return if (departureTime.isNullOrBlank()) null else Specification { root, query, criteriaBuilder ->
            val toCharExpr: Expression<String> = criteriaBuilder.function(
                "to_char", String::class.java,
                root.get<Instant>("scheduled_departure"),
                criteriaBuilder.literal("HH24:MI:SS")
            )

            criteriaBuilder.equal(toCharExpr, departureTime)
        }
    }

    fun flightNumber(flightNumber: String?): Specification<Flight>? {
        return if (flightNumber.isNullOrBlank()) null else Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<String>("flightNumber"), flightNumber)
        }
    }

    fun destination(destination: String?): Specification<Flight>? {
        return if (destination.isNullOrBlank()) null else Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<String>("arrivalAirport").get<String>("airportCode"), destination)
        }
    }

    fun origin(origin: String?): Specification<Flight>? {
        return if (origin.isNullOrBlank()) null else Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<Airport>("departureAirport").get<String>("airportCode"), origin)
        }
    }

    fun isScheduled(): Specification<Flight> {
        return Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<String>("status"), "Scheduled")
        }
    }

}
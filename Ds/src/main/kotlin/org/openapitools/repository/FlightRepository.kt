package org.openapitools.repository

import org.openapitools.model.entity.Flight
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface FlightRepository : CrudRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

}
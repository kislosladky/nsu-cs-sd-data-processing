package org.openapitools.model.entity

import java.math.BigDecimal
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "bookings")
data class Booking(
    @Id
    @Size(max = 6)
    @Column(name = "book_ref", nullable = false, length = 6)
    val bookRef: String,

    @NotNull
    @Column(name = "book_date", nullable = false)
    val bookDate: Instant,

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    val totalAmount: BigDecimal,
)
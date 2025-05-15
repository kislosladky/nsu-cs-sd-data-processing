package org.openapitools.model.entity

import org.openapitools.model.converter.ContactDataConverter
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @Column(name = "ticket_no")
    val ticketNumber: String,
//    @Column(name = "book_ref")
//    val bookingRef: String,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "book_ref", nullable = false)
    var booking: Booking? = null,
    @Column(name = "passenger_id")
    val passengerId: String,
    @Column(name = "passenger_name")
    val passengerName: String,
    @Column(name = "contact_data", columnDefinition = "jsonb")
    @Convert(converter = ContactDataConverter::class)
    val contactData: String
)

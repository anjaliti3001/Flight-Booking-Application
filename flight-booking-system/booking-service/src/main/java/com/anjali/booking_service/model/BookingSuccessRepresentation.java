package com.anjali.booking_service.model;

import com.anjali.booking_service.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingSuccessRepresentation {

    private UUID bookingNumber;

    private String flightNumber;

    private String passengerName;

    private BookingStatus status;

    private LocalDate bookingDate;

}

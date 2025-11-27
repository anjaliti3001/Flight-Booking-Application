package com.anjali.booking_service.model;

import lombok.Data;

@Data
public class BookingRequest {

    private String passengerName;

    private Double amount;

    private PaymentMode paymentMode;

    private String flightNumber;

    private Integer seats;

}

package com.anjali.flight_service.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FlightUpdateRequest {

    private Long flightId;

    private String flightNumber;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private Integer totalSeats;

    private Integer availableSeats;

    private Double amount;

}

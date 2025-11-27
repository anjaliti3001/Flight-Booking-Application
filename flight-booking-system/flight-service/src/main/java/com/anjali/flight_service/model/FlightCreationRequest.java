package com.anjali.flight_service.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FlightCreationRequest {

    private String flightNumber;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private Integer totalSeats;

    private Integer availableSeats;

    private Double amount;

}

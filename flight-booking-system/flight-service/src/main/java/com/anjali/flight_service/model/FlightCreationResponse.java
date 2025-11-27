package com.anjali.flight_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightCreationResponse {

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

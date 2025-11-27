package com.anjali.flight_service.model;

import java.time.LocalDate;

public record FlightSearchRequest(
        String origin,
        String destination,
        LocalDate travelDate,
        Integer passengers) {
}
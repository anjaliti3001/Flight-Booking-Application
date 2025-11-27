package com.anjali.flight_service.service;


import com.anjali.flight_service.model.FlightCreationRequest;
import com.anjali.flight_service.model.FlightCreationResponse;
import com.anjali.flight_service.model.FlightSearchRequest;
import com.anjali.flight_service.model.FlightUpdateRequest;
import com.anjali.flight_service.model.FlightResponse;

import java.util.List;

public interface FlightService {

    FlightCreationResponse createFlight(FlightCreationRequest flightCreationRequest);

    FlightResponse updateFlight(FlightUpdateRequest flightUpdateRequest);

    List<FlightResponse> getAllFlights();

    List<FlightResponse> searchFlights(FlightSearchRequest flightSearchRequest);

    void reserveSeats(String flightNumber, int seats);

    void restoreSeats(String flightNumber, int seats);
}

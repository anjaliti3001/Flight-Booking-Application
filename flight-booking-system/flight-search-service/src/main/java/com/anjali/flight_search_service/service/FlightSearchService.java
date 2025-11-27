package com.anjali.flight_search_service.service;



import com.anjali.flight_search_service.model.FlightResponse;
import com.anjali.flight_search_service.model.FlightSearchRequest;

import java.util.List;

public interface FlightSearchService {

    List<FlightResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}
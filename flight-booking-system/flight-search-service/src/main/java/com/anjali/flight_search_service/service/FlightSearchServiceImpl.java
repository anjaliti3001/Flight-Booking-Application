package com.anjali.flight_search_service.service;


import com.anjali.flight_search_service.client.FlightServiceClient;
import com.anjali.flight_search_service.model.FlightResponse;
import com.anjali.flight_search_service.model.FlightSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightServiceClient flightServiceClient;

    public FlightSearchServiceImpl(FlightServiceClient flightServiceClient) {
        this.flightServiceClient = flightServiceClient;
    }

    public List<FlightResponse> searchFlights(FlightSearchRequest request) {
        return flightServiceClient.searchFlights(request);
    }

}

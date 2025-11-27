package com.anjali.flight_search_service.controller;

import com.anjali.flight_search_service.model.FlightResponse;
import com.anjali.flight_search_service.model.FlightSearchRequest;
import com.anjali.flight_search_service.service.FlightSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/search")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @PostMapping("/flights")
    public ResponseEntity<List<FlightResponse>> searchFlights(
            @RequestBody FlightSearchRequest flightSearchRequest) {
        return new ResponseEntity<>(
                flightSearchService.searchFlights(flightSearchRequest), HttpStatus.OK);
    }
}

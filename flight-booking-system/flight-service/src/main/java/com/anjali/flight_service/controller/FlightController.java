package com.anjali.flight_service.controller;

import com.anjali.flight_service.model.FlightCreationRequest;
import com.anjali.flight_service.model.FlightCreationResponse;
import com.anjali.flight_service.model.FlightResponse;
import com.anjali.flight_service.model.FlightSearchRequest;
import com.anjali.flight_service.model.FlightUpdateRequest;
import com.anjali.flight_service.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/flights")
@RequiredArgsConstructor
@Slf4j
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightCreationResponse> createFlight(@RequestBody FlightCreationRequest flightCreationRequest) {
        log.info("Creating flight: {}", flightCreationRequest);
        var flight = flightService.createFlight(flightCreationRequest);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FlightResponse> updateFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        log.info("Updating flight: {}", flightUpdateRequest);
        var flight = flightService.updateFlight(flightUpdateRequest);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        log.debug("Fetching all flights");
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(@RequestBody FlightSearchRequest request) {
        return ResponseEntity.ok(flightService.searchFlights(request));
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<?> reserveSeats(@PathVariable("id") String flightNumber,
                                               @RequestParam int seats) {
        log.info("Reserving {} seats in flight {}", seats, flightNumber);
        flightService.reserveSeats(flightNumber, seats);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restoreSeats(@PathVariable("id") String flightNumber, @RequestParam int seats){
        log.info("Restoring {} seats in flight {}", seats, flightNumber);
        flightService.restoreSeats(flightNumber, seats);
        return ResponseEntity.noContent().build();
    }

}

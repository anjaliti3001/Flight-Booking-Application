package com.anjali.flight_service.service;

import com.anjali.flight_service.entity.Flight;
import com.anjali.flight_service.exception.FlightServiceCustomException;
import com.anjali.flight_service.model.FlightCreationRequest;
import com.anjali.flight_service.model.FlightCreationResponse;
import com.anjali.flight_service.model.FlightSearchRequest;
import com.anjali.flight_service.model.FlightUpdateRequest;
import com.anjali.flight_service.model.FlightResponse;
import com.anjali.flight_service.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public FlightCreationResponse createFlight(FlightCreationRequest request) {
        Flight flight = Flight.builder().flightNumber(request.getFlightNumber()).origin(request.getOrigin()).destination(request.getDestination()).departureDate(request.getDepartureDate()).arrivalDate(request.getArrivalDate()).totalSeats(request.getTotalSeats()).availableSeats(request.getAvailableSeats()).amount(request.getAmount()).build();
        var savedFlight = flightRepository.save(flight);
        var flightResponse = new FlightCreationResponse();
        BeanUtils.copyProperties(savedFlight, flightResponse);
        log.info("Flight Created {} ", flightResponse.getFlightId());
        return flightResponse;
    }

    @Override
    public FlightResponse updateFlight(FlightUpdateRequest request) {
        Flight flight = Flight.builder().flightId(request.getFlightId()).flightNumber(request.getFlightNumber()).origin(request.getOrigin()).destination(request.getDestination()).departureDate(request.getDepartureDate()).arrivalDate(request.getArrivalDate()).totalSeats(request.getTotalSeats()).availableSeats(request.getAvailableSeats()).amount(request.getAmount()).build();
        var savedFlight = flightRepository.save(flight);
        log.info("Flight details updated for flightNumber: {} ", savedFlight.getFlightNumber());
        return mapToFlightResponse(savedFlight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(this::mapToFlightResponse).collect(Collectors.toList());
    }

    @Override
    public List<FlightResponse> searchFlights(FlightSearchRequest request) {
        var mayBeFlight = flightRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(request.origin(), request.destination(), request.travelDate(), request.passengers());
        return mayBeFlight.stream().map(this::mapToFlightResponse).toList();
    }

    private FlightResponse mapToFlightResponse(Flight flight) {
        FlightResponse response = new FlightResponse();
        BeanUtils.copyProperties(flight, response);
        return response;
    }

    @Override
    @Transactional
    public void reserveSeats(String flightNumber, int seats) {
        log.info("Reserve seats {} for flight Number: {}", seats, flightNumber);
        var flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(() -> new FlightServiceCustomException("Flight with given id not found", "FLIGHT_NOT_FOUND"));
        if (flight.getAvailableSeats() < seats) {
            throw  new FlightServiceCustomException("Flights does not have sufficient seats", "INSUFFICIENT_SEATS");
        }
        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        var savedFlight = flightRepository.save(flight);
        log.info("Seats are reserved for flight number {}", savedFlight.getFlightNumber());
    }

    @Override
    public void restoreSeats(String flightNumber, int seats){
        log.info("Restore seats {} for flight Number: {}", seats, flightNumber);
        var flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(() -> new FlightServiceCustomException("Flight with given id not found", "FLIGHT_NOT_FOUND"));
        if (flight.getAvailableSeats() + seats > flight.getTotalSeats()) {
            throw  new FlightServiceCustomException("Flights does not have sufficient seats", "INSUFFICIENT_SEATS");
        }
        flight.setAvailableSeats(flight.getAvailableSeats() + seats);
        var savedFlight = flightRepository.save(flight);
        log.info("Seats are restored for flight number {}", savedFlight.getFlightNumber());
    }

}

package com.anjali.booking_service.external.client;

import com.anjali.booking_service.exception.BookingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightServiceClient {

    @PutMapping("/v1/api/flights/{id}/reserve")
    ResponseEntity<?> reserveSeats(@PathVariable("id") String flightNumber, @RequestParam int seats);

    @PutMapping("/v1/api/flights/{id}/restore")
    ResponseEntity<?> restoreSeats(@PathVariable("id") String flightNumber, @RequestParam int seats);

    default void fallback(Exception e) {
        throw new BookingException("Flight Service is not available",
                "UNAVAILABLE",
                500);
    }
}

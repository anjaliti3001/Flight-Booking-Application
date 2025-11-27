package com.anjali.booking_service.controller;

import com.anjali.booking_service.model.BookingCancelResponse;
import com.anjali.booking_service.model.BookingCancellationRequest;
import com.anjali.booking_service.model.BookingRequest;
import com.anjali.booking_service.model.BookingResponse;
import com.anjali.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/v1/api/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Create booking with request {} ", bookingRequest);
        var response = bookingService.createBooking(bookingRequest);
        return switch (response) {
            case BookingResponse.BookingSuccess bookingSuccess ->
                    new ResponseEntity<>(bookingSuccess.representation(), HttpStatus.CREATED);
            case BookingResponse.Failed failed -> ResponseEntity.unprocessableEntity()
                    .body(failed.response());
        };
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestBody BookingCancellationRequest request) {
        log.info("Cancel booking request received for booking number: {}", request.getBookingNumber());
        var response = bookingService.cancelBooking(request.getBookingNumber(), request.getReason());
        return switch (response) {
            case BookingCancelResponse.CancellationSuccess cancellationSuccess ->
                    new ResponseEntity<>(cancellationSuccess.representation(), HttpStatus.OK);
            case BookingCancelResponse.CancellationFailed cancellationFailed ->
                    new ResponseEntity<>(cancellationFailed.response(), NOT_FOUND);
        };
    }

}

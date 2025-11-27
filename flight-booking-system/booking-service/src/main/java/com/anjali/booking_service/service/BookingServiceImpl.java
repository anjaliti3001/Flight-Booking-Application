package com.anjali.booking_service.service;

import com.anjali.booking_service.entity.Booking;
import com.anjali.booking_service.entity.BookingStatus;
import com.anjali.booking_service.exception.BookingException;
import com.anjali.booking_service.external.client.FlightServiceClient;
import com.anjali.booking_service.external.model.ErrorResponse;
import com.anjali.booking_service.model.BookingCancelResponse;
import com.anjali.booking_service.model.BookingRequest;
import com.anjali.booking_service.model.BookingResponse;
import com.anjali.booking_service.model.BookingSuccessRepresentation;
import com.anjali.booking_service.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private FlightServiceClient flightServiceClient;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        try {
            var response = flightServiceClient.reserveSeats(bookingRequest.getFlightNumber(), bookingRequest.getSeats());
            if (response.getStatusCode().equals(NO_CONTENT)) {
                log.info("create Booking for user {}", bookingRequest.getPassengerName());
                var booking = Booking.builder()
                        .passengerName(bookingRequest.getPassengerName())
                        .flightNumber(bookingRequest.getFlightNumber())
                        .seats(bookingRequest.getSeats())
                        .bookingNumber(UUID.randomUUID())
                        .amount(bookingRequest.getAmount())
                        .paymentMode(bookingRequest.getPaymentMode())
                        .bookingDate(LocalDate.now())
                        .status(BookingStatus.CREATED).build();
                var savedBooking = bookingRepository.save(booking);
                return new BookingResponse.BookingSuccess(mapBookingToBookingSuccessRepresentation(savedBooking));
            }
            throw new BookingException("Flight Service is not available", "UNAVAILABLE", 500);
        } catch (Exception e) {
            log.error("e: ", e);
        }
        return null;
    }

    @Override
    @Transactional
    public BookingCancelResponse cancelBooking(UUID bookingNumber, String reason) {
        var mayBeBooking = bookingRepository.findByBookingNumber(bookingNumber);
        if (Boolean.FALSE.equals(mayBeBooking.isEmpty())) {
            var booking = mayBeBooking.get();
            if (booking.getStatus().equals(BookingStatus.CANCELLED)) {
                return new BookingCancelResponse.CancellationFailed(new ErrorResponse("Booking is already cancelled", "BOOKING_CANNOT_BE_CANCELLED"));
            }
            booking.setStatus(BookingStatus.CANCELLED);
            booking.setReasonForCancellation(reason);
            booking.setLastUpdatedAt(LocalDateTime.now());
            bookingRepository.save(booking);
            var response = flightServiceClient.restoreSeats(booking.getFlightNumber(), booking.getSeats());
            if (response.getStatusCode().equals(NO_CONTENT)) {
                return new BookingCancelResponse.CancellationSuccess(mapBookingToBookingSuccessRepresentation(booking));
            }
            throw new BookingException("Flight Service is not available", "UNAVAILABLE", 500);
        }
        return new BookingCancelResponse.CancellationFailed(new ErrorResponse("Booking not found for booking number", "BOOKING_NOT_FOUND"));
    }

    private static BookingSuccessRepresentation mapBookingToBookingSuccessRepresentation(Booking savedBooking) {
        return new BookingSuccessRepresentation(savedBooking.getBookingNumber(), savedBooking.getFlightNumber(), savedBooking.getPassengerName(), savedBooking.getStatus(), savedBooking.getBookingDate());
    }

}
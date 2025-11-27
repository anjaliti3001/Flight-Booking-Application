package com.anjali.booking_service.service;


import com.anjali.booking_service.model.BookingCancelResponse;
import com.anjali.booking_service.model.BookingRequest;
import com.anjali.booking_service.model.BookingResponse;

import java.util.UUID;

public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingCancelResponse cancelBooking(UUID bookingNumber, String reason);

}

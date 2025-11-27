package com.anjali.booking_service.model;

import com.anjali.booking_service.external.model.ErrorResponse;

public sealed interface BookingResponse {

    record BookingSuccess(BookingSuccessRepresentation representation) implements BookingResponse {
    }

    record Failed(ErrorResponse response) implements BookingResponse {
    }

}
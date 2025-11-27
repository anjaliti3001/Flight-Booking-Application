package com.anjali.booking_service.model;

import com.anjali.booking_service.external.model.ErrorResponse;

public sealed interface BookingCancelResponse {

    record CancellationSuccess(BookingSuccessRepresentation representation) implements BookingCancelResponse{
    }

    record CancellationFailed(ErrorResponse response) implements BookingCancelResponse{
    }

}

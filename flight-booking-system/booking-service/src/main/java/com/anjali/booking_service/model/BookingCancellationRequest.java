package com.anjali.booking_service.model;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class BookingCancellationRequest {

    private UUID bookingNumber;

    private String reason;
}

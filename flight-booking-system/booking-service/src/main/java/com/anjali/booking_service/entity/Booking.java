package com.anjali.booking_service.entity;

import com.anjali.booking_service.model.PaymentMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Bookings")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID bookingNumber;

    private String passengerName;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Double amount;

    private PaymentMode paymentMode;

    private LocalDate bookingDate;

    private String flightNumber;

    private Integer seats;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    private String reasonForCancellation;

}

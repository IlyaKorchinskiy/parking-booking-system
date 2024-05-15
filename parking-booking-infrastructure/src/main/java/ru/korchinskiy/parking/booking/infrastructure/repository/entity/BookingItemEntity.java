package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_items")
@IdClass(BookingItemEntityId.class)
public class BookingItemEntity {

    @Id
    @Column(name = "start_date_time")
    private OffsetDateTime startDateTime;

    @Id
    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "booking_id")
    private UUID bookingId;
}

package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    private UUID id;

    @Column(name = "parking_id")
    private UUID parkingId;

    @Column(name = "user_id")
    private UUID userId;

    @OneToMany
    @JoinColumn(name = "booking_id")
    private Set<BookingItemEntity> bookingItems;
}

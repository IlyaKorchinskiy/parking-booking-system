package ru.korchinskiy.parking.booking.application.port.out;

import ru.korchinskiy.parking.booking.application.entity.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {

    void save(Booking booking);

    Optional<Booking> getById(UUID id);

    List<Booking> findByUserId(UUID userId);
}

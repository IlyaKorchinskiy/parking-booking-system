package ru.korchinskiy.parking.booking.application.port.in;

import ru.korchinskiy.parking.booking.application.entity.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    UUID bookParking(Booking booking);

    Booking getBookingById(UUID id);

    List<Booking> findUserBookings(UUID userId);
}

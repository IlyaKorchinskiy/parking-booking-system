package ru.korchinskiy.parking.booking.application;

import ru.korchinskiy.parking.booking.application.annotation.UseCase;
import ru.korchinskiy.parking.booking.application.annotation.UseCaseService;
import ru.korchinskiy.parking.booking.application.entity.Booking;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.exception.EntityNotFoundException;
import ru.korchinskiy.parking.booking.application.port.in.BookingService;
import ru.korchinskiy.parking.booking.application.port.out.BookingRepository;
import ru.korchinskiy.parking.booking.application.port.out.ParkingRepository;

import java.util.List;
import java.util.UUID;

@UseCaseService
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ParkingRepository parkingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ParkingRepository parkingRepository) {
        this.bookingRepository = bookingRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    @UseCase
    public UUID bookParking(Booking booking) {
        booking.initializeAndValidate();
        Parking parking = getParking(booking.getParkingId());
        parking.bookParkingSlots(booking.getBookingItems());
        parkingRepository.save(parking);
        bookingRepository.save(booking);
        return booking.getId();
    }

    @Override
    @UseCase(readOnly = true)
    public Booking getBookingById(UUID id) {
        return bookingRepository
                .getById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Booking with such id: %s is not found.", id)));
    }

    @Override
    @UseCase(readOnly = true)
    public List<Booking> findUserBookings(UUID userId) {
        return bookingRepository.findByUserId(userId);
    }

    private Parking getParking(UUID parkingId) {
        return parkingRepository
                .getParkingByIdForUpdate(parkingId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Parking with such id: %s is not found.", parkingId)));
    }
}

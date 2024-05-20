package ru.korchinskiy.parking.booking.infrastructure.repository.mapper;

import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Booking;
import ru.korchinskiy.parking.booking.application.entity.BookingItem;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.BookingEntity;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.BookingItemEntity;

import java.util.stream.Collectors;

@Component
public class BookingJpaMapper {

    public Booking bookingEntityToBooking(BookingEntity bookingEntity) {
        return Booking.builder()
                .id(bookingEntity.getId())
                .userId(bookingEntity.getUserId())
                .parkingId(bookingEntity.getParkingId())
                .bookingItems(bookingEntity.getBookingItems().stream()
                        .map(bookingItemEntity -> new BookingItem(
                                bookingItemEntity.getStartDateTime(), bookingItemEntity.getVehicleNumber()))
                        .collect(Collectors.toSet()))
                .build();
    }

    public BookingEntity bookingToBookingEntity(Booking booking) {
        return BookingEntity.builder()
                .id(booking.getId())
                .parkingId(booking.getParkingId())
                .userId(booking.getUserId())
                .bookingItems(booking.getBookingItems().stream()
                        .map(bookingItem -> new BookingItemEntity(
                                bookingItem.getStartDateTime(), bookingItem.getVehicleNumber(), booking.getId()))
                        .collect(Collectors.toSet()))
                .build();
    }
}

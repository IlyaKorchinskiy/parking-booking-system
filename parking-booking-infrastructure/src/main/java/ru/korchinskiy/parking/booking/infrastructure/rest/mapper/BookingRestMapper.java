package ru.korchinskiy.parking.booking.infrastructure.rest.mapper;

import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Booking;
import ru.korchinskiy.parking.booking.application.entity.BookingItem;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.BookingDto;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.BookingDtoBookingItemsInner;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookingRestMapper {

    public Booking bookingDtoToBooking(BookingDto bookingDto, UUID userId) {
        return Booking.builder()
                .userId(userId)
                .parkingId(bookingDto.getParkingId())
                .bookingItems(bookingDto.getBookingItems().stream()
                        .map(itemDto ->
                                new BookingItem(itemDto.getStartDateTime().withNano(0), itemDto.getVehicleNumber()))
                        .collect(Collectors.toSet()))
                .build();
    }

    public BookingDto bookingToBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getId());
        bookingDto.setParkingId(booking.getParkingId());
        bookingDto.setBookingItems(booking.getBookingItems().stream()
                .map(bookingItem ->
                        new BookingDtoBookingItemsInner(bookingItem.getStartDateTime(), bookingItem.getVehicleNumber()))
                .collect(Collectors.toList()));
        return bookingDto;
    }
}

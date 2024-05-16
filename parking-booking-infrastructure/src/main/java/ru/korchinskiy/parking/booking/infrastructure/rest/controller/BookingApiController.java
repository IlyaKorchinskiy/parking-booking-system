package ru.korchinskiy.parking.booking.infrastructure.rest.controller;

import lombok.RequiredArgsConstructor;
import openapi.api.BookingApi;
import openapi.model.BookingDto;
import openapi.model.BookingDtoRef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.korchinskiy.parking.booking.application.port.in.BookingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookingApiController implements BookingApi {

    private final BookingService bookingService;

    @Override
    public ResponseEntity<BookingDtoRef> createBooking(BookingDto bookingDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<BookingDto>> findBookings(UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<BookingDto> getBookingById(UUID id) {
        return null;
    }
}

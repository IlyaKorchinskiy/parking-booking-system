package ru.korchinskiy.parking.booking.infrastructure.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import ru.korchinskiy.parking.booking.application.port.in.BookingService;
import ru.korchinskiy.parking.booking.infrastructure.openapi.api.BookingApi;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.BookingDto;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.BookingDtoRef;
import ru.korchinskiy.parking.booking.infrastructure.rest.mapper.BookingRestMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookingApiController implements BookingApi {

    private final BookingService bookingService;
    private final BookingRestMapper bookingRestMapper;

    @Override
    public ResponseEntity<BookingDtoRef> createBooking(BookingDto bookingDto) {
        return new ResponseEntity<>(
                new BookingDtoRef(bookingService.bookParking(bookingRestMapper.bookingDtoToBooking(
                        bookingDto, UUID.fromString((String) ((JwtAuthenticationToken)
                                        SecurityContextHolder.getContext().getAuthentication())
                                .getTokenAttributes()
                                .get("sub"))))),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<BookingDto>> findBookings(UUID userId) {
        return new ResponseEntity<>(
                bookingService.findUserBookings(userId).stream()
                        .map(bookingRestMapper::bookingToBookingDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDto> getBookingById(UUID id) {
        return new ResponseEntity<>(
                bookingRestMapper.bookingToBookingDto(bookingService.getBookingById(id)), HttpStatus.OK);
    }
}

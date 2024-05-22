package ru.korchinskiy.parking.booking.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korchinskiy.parking.booking.application.entity.Booking;
import ru.korchinskiy.parking.booking.application.entity.BookingItem;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.port.out.BookingRepository;
import ru.korchinskiy.parking.booking.application.port.out.ParkingRepository;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest extends CommonTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void bookParking_Success() {
        Booking booking = Booking.builder()
                .parkingId(UUID.fromString(PARKING_ID_1))
                .userId(UUID.fromString(USER_ID))
                .bookingItems(new HashSet<>(Set.of(new BookingItem(
                        OffsetDateTime.now()
                                .withMinute(0)
                                .withSecond(0)
                                .withNano(0)
                                .plusDays(1),
                        "1234ABC"))))
                .build();
        Parking parking = Parking.builder()
                .id(UUID.fromString(PARKING_ID_1))
                .totalPlaces(20)
                .address("Some address")
                .coordinates(new Coordinates(BigDecimal.valueOf(43.365895), BigDecimal.valueOf(-5.856932)))
                .timeToAvailableSlots(new HashMap<>(Map.of(
                        OffsetDateTime.now()
                                .withMinute(0)
                                .withSecond(0)
                                .withNano(0)
                                .plusDays(1),
                        18,
                        OffsetDateTime.of(2024, 5, 21, 17, 0, 0, 0, ZoneOffset.UTC),
                        19)))
                .build();

        when(parkingRepository.getParkingByIdForUpdate(booking.getParkingId())).thenReturn(Optional.of(parking));

        UUID uuid = bookingService.bookParking(booking);

        assertNotNull(uuid);
        assertEquals(
                17,
                parking.getTimeToAvailableSlots()
                        .get(OffsetDateTime.now()
                                .withMinute(0)
                                .withSecond(0)
                                .withNano(0)
                                .plusDays(1)));
        verify(parkingRepository).save(parking);
        verify(bookingRepository).save(booking);
    }

    @Test
    void getBookingById() {
        Booking booking = Booking.builder()
                .parkingId(UUID.fromString(PARKING_ID_1))
                .userId(UUID.fromString(USER_ID))
                .id(UUID.fromString(BOOKING_ID_1))
                .bookingItems(new HashSet<>(Set.of(
                        new BookingItem(OffsetDateTime.of(2024, 5, 21, 16, 0, 0, 0, ZoneOffset.UTC), "1234ABC"))))
                .build();

        when(bookingRepository.getById(UUID.fromString(BOOKING_ID_1))).thenReturn(Optional.of(booking));

        Booking actual = bookingService.getBookingById(UUID.fromString(BOOKING_ID_1));

        assertEquals(booking, actual);
    }

    @Test
    void findUserBookings() {
        List<Booking> bookings = List.of(
                Booking.builder()
                        .parkingId(UUID.fromString(PARKING_ID_1))
                        .userId(UUID.fromString(USER_ID))
                        .id(UUID.fromString(BOOKING_ID_1))
                        .bookingItems(new HashSet<>(Set.of(new BookingItem(
                                OffsetDateTime.of(2024, 5, 21, 16, 0, 0, 0, ZoneOffset.UTC), "1234ABC"))))
                        .build(),
                Booking.builder()
                        .parkingId(UUID.fromString(PARKING_ID_2))
                        .userId(UUID.fromString(USER_ID))
                        .id(UUID.fromString(BOOKING_ID_2))
                        .bookingItems(new HashSet<>(Set.of(
                                new BookingItem(OffsetDateTime.of(2024, 5, 22, 12, 0, 0, 0, ZoneOffset.UTC), "1234ABC"),
                                new BookingItem(
                                        OffsetDateTime.of(2024, 5, 22, 13, 0, 0, 0, ZoneOffset.UTC), "1234ABC"))))
                        .build());

        when(bookingRepository.findByUserId(UUID.fromString(USER_ID))).thenReturn(bookings);

        List<Booking> actual = bookingService.findUserBookings(UUID.fromString(USER_ID));

        assertEquals(bookings, actual);
    }
}
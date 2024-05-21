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
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private static final String PARKING_ID = "9ad24d2f-ca4f-4a5e-b39a-15a64ff37016";
    private static final String USER_ID = "210a4bf0-2e89-44ba-a193-cbefa50a5ff3";

    @Test
    void bookParking_Success() {
        Booking booking = Booking.builder()
                .parkingId(UUID.fromString(PARKING_ID))
                .userId(UUID.fromString(USER_ID))
                .bookingItems(new HashSet<>(Set.of(
                        new BookingItem(OffsetDateTime.of(2024, 5, 21, 16, 0, 0, 0, ZoneOffset.UTC), "1234ABC"))))
                .build();
        Parking parking = Parking.builder()
                .id(UUID.fromString(PARKING_ID))
                .totalPlaces(20)
                .address("Some address")
                .coordinates(new Coordinates(BigDecimal.valueOf(43.365895), BigDecimal.valueOf(-5.856932)))
                .timeToAvailableSlots(new HashMap<>(Map.of(
                        OffsetDateTime.of(2024, 5, 21, 16, 0, 0, 0, ZoneOffset.UTC),
                        18,
                        OffsetDateTime.of(2024, 5, 21, 17, 0, 0, 0, ZoneOffset.UTC),
                        19)))
                .build();

        when(parkingRepository.getParkingByIdForUpdate(booking.getParkingId())).thenReturn(Optional.of(parking));

        UUID uuid = bookingService.bookParking(booking);

        assertNotNull(uuid);
        assertEquals(
                17, parking.getTimeToAvailableSlots().get(OffsetDateTime.of(2024, 5, 21, 16, 0, 0, 0, ZoneOffset.UTC)));
        verify(parkingRepository).save(parking);
        verify(bookingRepository).save(booking);
    }

    @Test
    void getBookingById() {}

    @Test
    void findUserBookings() {}
}
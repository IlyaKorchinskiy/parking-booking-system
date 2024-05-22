package ru.korchinskiy.parking.booking.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.port.out.ParkingRepository;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest extends CommonTest {

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Test
    void findParkingsByCoordinates() {
        Coordinates leftTop = new Coordinates(BigDecimal.valueOf(46.365898), BigDecimal.valueOf(-5.356987));
        Coordinates rightBottom = new Coordinates(BigDecimal.valueOf(46.489635), BigDecimal.valueOf(-5.758963));
        List<Parking> parkings = List.of(
                Parking.builder()
                        .id(UUID.fromString(PARKING_ID_1))
                        .totalPlaces(20)
                        .address("Some address")
                        .coordinates(new Coordinates(BigDecimal.valueOf(46.465895), BigDecimal.valueOf(-5.556932)))
                        .timeToAvailableSlots(new HashMap<>(Map.of(
                                OffsetDateTime.of(2024, 5, 25, 12, 0, 0, 0, ZoneOffset.UTC),
                                18,
                                OffsetDateTime.of(2024, 5, 21, 17, 0, 0, 0, ZoneOffset.UTC),
                                19)))
                        .build(),
                Parking.builder()
                        .id(UUID.fromString(PARKING_ID_2))
                        .totalPlaces(30)
                        .address("Some address")
                        .coordinates(new Coordinates(BigDecimal.valueOf(46.395895), BigDecimal.valueOf(-5.456932)))
                        .timeToAvailableSlots(new HashMap<>(Map.of(
                                OffsetDateTime.of(2024, 5, 23, 18, 0, 0, 0, ZoneOffset.UTC),
                                18,
                                OffsetDateTime.of(2024, 5, 21, 12, 0, 0, 0, ZoneOffset.UTC),
                                19)))
                        .build());

        when(parkingRepository.findParkingsByCoordinates(
                        leftTop.getX(), rightBottom.getX(), rightBottom.getY(), leftTop.getY()))
                .thenReturn(parkings);

        List<Parking> actual = parkingService.findParkingsByCoordinates(leftTop, rightBottom);

        assertEquals(parkings, actual);
    }

    @Test
    void getParkingById() {
        Parking parking = Parking.builder()
                .id(UUID.fromString(PARKING_ID_1))
                .totalPlaces(20)
                .address("Some address")
                .coordinates(new Coordinates(BigDecimal.valueOf(46.465895), BigDecimal.valueOf(-5.556932)))
                .timeToAvailableSlots(new HashMap<>(Map.of(
                        OffsetDateTime.of(2024, 5, 25, 12, 0, 0, 0, ZoneOffset.UTC),
                        18,
                        OffsetDateTime.of(2024, 5, 21, 17, 0, 0, 0, ZoneOffset.UTC),
                        19)))
                .build();

        when(parkingRepository.getParkingById(UUID.fromString(PARKING_ID_1))).thenReturn(Optional.of(parking));

        Parking actual = parkingService.getParkingById(UUID.fromString(PARKING_ID_1));

        assertEquals(parking, actual);
    }
}
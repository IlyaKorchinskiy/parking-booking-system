package ru.korchinskiy.parking.booking.application.port.out;

import ru.korchinskiy.parking.booking.application.entity.Parking;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingRepository {

    Optional<Parking> getParkingByIdForUpdate(UUID id);

    Optional<Parking> getParkingById(UUID id);

    void save(Parking parking);

    List<Parking> findParkingsByCoordinates(BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2);
}

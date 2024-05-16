package ru.korchinskiy.parking.booking.application.port.in;

import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;

import java.util.List;
import java.util.UUID;

public interface ParkingService {

    List<Parking> findParkingsByCoordinates(Coordinates leftTop, Coordinates rightBottom);

    Parking getParkingById(UUID parkingId);
}

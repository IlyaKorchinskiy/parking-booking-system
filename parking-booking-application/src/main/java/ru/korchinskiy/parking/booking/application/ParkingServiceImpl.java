package ru.korchinskiy.parking.booking.application;

import ru.korchinskiy.parking.booking.application.annotation.UseCase;
import ru.korchinskiy.parking.booking.application.annotation.UseCaseService;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.exception.EntityNotFoundException;
import ru.korchinskiy.parking.booking.application.port.in.ParkingService;
import ru.korchinskiy.parking.booking.application.port.out.ParkingRepository;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;

import java.util.List;
import java.util.UUID;

@UseCaseService
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    @UseCase(readOnly = true)
    public List<Parking> findParkingsByCoordinates(Coordinates leftUp, Coordinates rightDown) {
        return parkingRepository.findParkingsByCoordinates(
                leftUp.getX(), rightDown.getX(), rightDown.getY(), leftUp.getY());
    }

    @Override
    @UseCase(readOnly = true)
    public Parking getParkingById(UUID parkingId) {
        return parkingRepository
                .getParkingById(parkingId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Parking with such id: %s is not found", parkingId)));
    }
}

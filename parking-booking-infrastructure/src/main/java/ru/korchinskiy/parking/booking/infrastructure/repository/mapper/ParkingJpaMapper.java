package ru.korchinskiy.parking.booking.infrastructure.repository.mapper;

import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.ParkingEntity;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.SlotEntity;

import java.util.stream.Collectors;

@Component
public class ParkingJpaMapper {

    public ParkingEntity parkingToParkingEntity(Parking parking) {
        return ParkingEntity.builder()
                .id(parking.getId())
                .address(parking.getAddress())
                .coordinateX(parking.getCoordinates().getX())
                .coordinateY(parking.getCoordinates().getY())
                .totalPlaces(parking.getTotalPlaces())
                .slots(parking.getTimeToAvailableSlots().entrySet().stream()
                        .map(entry -> new SlotEntity(entry.getKey(), parking.getId(), entry.getValue()))
                        .collect(Collectors.toSet()))
                .build();
    }

    public Parking parkingEntityToParking(ParkingEntity parkingEntity) {
        return Parking.builder()
                .id(parkingEntity.getId())
                .address(parkingEntity.getAddress())
                .coordinates(new Coordinates(parkingEntity.getCoordinateX(), parkingEntity.getCoordinateY()))
                .totalPlaces(parkingEntity.getTotalPlaces())
                .timeToAvailableSlots(parkingEntity.getSlots().stream()
                        .collect(Collectors.toMap(SlotEntity::getStartDateTime, SlotEntity::getAvailable)))
                .build();
    }
}

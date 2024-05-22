package ru.korchinskiy.parking.booking.infrastructure.rest.mapper;

import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.ParkingDto;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.ParkingDtoCoordinates;
import ru.korchinskiy.parking.booking.infrastructure.openapi.model.ParkingDtoTimeToAvailableSlotsInner;

import java.util.stream.Collectors;

@Component
public class ParkingRestMapper {

    public ParkingDto parkingToParkingDto(Parking parking) {
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setParkingId(parking.getId());
        parkingDto.setAddress(parking.getAddress());
        parkingDto.setTotalPlaces(parking.getTotalPlaces());
        parkingDto.setCoordinates(new ParkingDtoCoordinates(
                parking.getCoordinates().getX().doubleValue(),
                parking.getCoordinates().getY().doubleValue()));
        parkingDto.setTimeToAvailableSlots(parking.getTimeToAvailableSlots().entrySet().stream()
                .map(entrySet -> new ParkingDtoTimeToAvailableSlotsInner(entrySet.getKey(), entrySet.getValue()))
                .collect(Collectors.toList()));
        return parkingDto;
    }
}

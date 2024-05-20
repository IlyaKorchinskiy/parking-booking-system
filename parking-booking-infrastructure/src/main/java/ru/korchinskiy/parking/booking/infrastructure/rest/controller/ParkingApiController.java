package ru.korchinskiy.parking.booking.infrastructure.rest.controller;

import lombok.RequiredArgsConstructor;
import openapi.api.ParkingApi;
import openapi.model.ParkingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.korchinskiy.parking.booking.application.port.in.ParkingService;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;
import ru.korchinskiy.parking.booking.infrastructure.rest.mapper.ParkingRestMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ParkingApiController implements ParkingApi {

    private final ParkingService parkingService;
    private final ParkingRestMapper parkingRestMapper;

    @Override
    public ResponseEntity<List<ParkingDto>> findParkings(Double x1, Double x2, Double y1, Double y2) {
        return new ResponseEntity<>(
                parkingService
                        .findParkingsByCoordinates(
                                new Coordinates(new BigDecimal(x1), new BigDecimal(y2)),
                                new Coordinates(new BigDecimal(x2), new BigDecimal(y1)))
                        .stream()
                        .map(parkingRestMapper::parkingToParkingDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ParkingDto> getParkingById(UUID id) {
        return new ResponseEntity<>(
                parkingRestMapper.parkingToParkingDto(parkingService.getParkingById(id)), HttpStatus.OK);
    }
}

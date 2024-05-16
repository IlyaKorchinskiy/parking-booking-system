package ru.korchinskiy.parking.booking.infrastructure.rest.controller;

import lombok.RequiredArgsConstructor;
import openapi.api.ParkingApi;
import openapi.model.ParkingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.korchinskiy.parking.booking.application.port.in.ParkingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ParkingApiController implements ParkingApi {

    private final ParkingService parkingService;

    @Override
    public ResponseEntity<List<ParkingDto>> findParkings(Double x1, Double x2, Double y1, Double y2) {
        return null;
    }

    @Override
    public ResponseEntity<ParkingDto> getParkingById(UUID id) {
        return null;
    }
}

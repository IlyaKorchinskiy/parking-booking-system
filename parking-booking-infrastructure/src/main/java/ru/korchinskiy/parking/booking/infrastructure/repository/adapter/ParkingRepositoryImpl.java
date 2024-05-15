package ru.korchinskiy.parking.booking.infrastructure.repository.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Parking;
import ru.korchinskiy.parking.booking.application.port.out.ParkingRepository;
import ru.korchinskiy.parking.booking.infrastructure.repository.jparepository.ParkingJpaRepository;
import ru.korchinskiy.parking.booking.infrastructure.repository.mapper.ParkingJpaMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParkingRepositoryImpl implements ParkingRepository {

    private final ParkingJpaMapper parkingJpaMapper;
    private final ParkingJpaRepository parkingJpaRepository;

    @Override
    public Optional<Parking> getParkingByIdForUpdate(UUID id) {
        return parkingJpaRepository.getByIdForUpdate(id).map(parkingJpaMapper::parkingEntityToParking);
    }

    @Override
    public Optional<Parking> getParkingById(UUID id) {
        return parkingJpaRepository.findById(id).map(parkingJpaMapper::parkingEntityToParking);
    }

    @Override
    public void save(Parking parking) {
        parkingJpaRepository.save(parkingJpaMapper.parkingToParkingEntity(parking));
    }

    @Override
    public List<Parking> findParkingsByCoordinates(BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2) {
        return parkingJpaRepository.findByCoordinateXBetweenAndCoordinateYBetween(x1, x2, y1, y2).stream()
                .map(parkingJpaMapper::parkingEntityToParking)
                .collect(Collectors.toList());
    }
}

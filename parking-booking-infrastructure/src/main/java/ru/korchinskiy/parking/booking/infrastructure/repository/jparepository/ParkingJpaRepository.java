package ru.korchinskiy.parking.booking.infrastructure.repository.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.ParkingEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingJpaRepository extends JpaRepository<ParkingEntity, UUID> {

    @Query(value = "select * from parkings where id = :id for update", nativeQuery = true)
    Optional<ParkingEntity> getByIdForUpdate(UUID id);

    List<ParkingEntity> findByCoordinateXBetweenAndCoordinateYBetween(
            BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2);
}

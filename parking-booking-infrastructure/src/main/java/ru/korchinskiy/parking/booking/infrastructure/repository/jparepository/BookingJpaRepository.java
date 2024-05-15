package ru.korchinskiy.parking.booking.infrastructure.repository.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.parking.booking.infrastructure.repository.entity.BookingEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {

    List<BookingEntity> findByUserId(UUID userId);
}

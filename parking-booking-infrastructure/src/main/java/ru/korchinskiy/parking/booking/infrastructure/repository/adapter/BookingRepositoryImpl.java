package ru.korchinskiy.parking.booking.infrastructure.repository.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korchinskiy.parking.booking.application.entity.Booking;
import ru.korchinskiy.parking.booking.application.port.out.BookingRepository;
import ru.korchinskiy.parking.booking.infrastructure.repository.jparepository.BookingJpaRepository;
import ru.korchinskiy.parking.booking.infrastructure.repository.mapper.BookingJpaMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    private final BookingJpaMapper bookingJpaMapper;
    private final BookingJpaRepository bookingJpaRepository;

    @Override
    public void save(Booking booking) {
        bookingJpaRepository.save(bookingJpaMapper.bookingToBookingEntity(booking));
    }

    @Override
    public Optional<Booking> getById(UUID id) {
        return bookingJpaRepository.findById(id).map(bookingJpaMapper::bookingEntityToBooking);
    }

    @Override
    public List<Booking> findByUserId(UUID userId) {
        return bookingJpaRepository.findByUserId(userId).stream()
                .map(bookingJpaMapper::bookingEntityToBooking)
                .toList();
    }
}

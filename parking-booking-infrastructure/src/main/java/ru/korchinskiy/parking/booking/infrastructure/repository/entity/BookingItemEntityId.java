package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
public class BookingItemEntityId implements Serializable {

    private OffsetDateTime startDateTime;
    private String vehicleNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingItemEntityId that = (BookingItemEntityId) o;
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(vehicleNumber, that.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, vehicleNumber);
    }
}

package ru.korchinskiy.parking.booking.infrastructure.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class SlotEntityId implements Serializable {

    private OffsetDateTime startDateTime;
    private UUID parkingId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotEntityId that = (SlotEntityId) o;
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(parkingId, that.parkingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, parkingId);
    }
}

package ru.korchinskiy.parking.booking.application.entity;

import ru.korchinskiy.parking.booking.application.exception.BusinessValidationException;
import ru.korchinskiy.parking.booking.application.valueobject.Coordinates;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Parking extends BaseEntity<UUID> {

    private final Coordinates coordinates;
    private final String address;
    private final Integer totalPlaces;
    private final Map<OffsetDateTime, Integer> timeToAvailableSlots;

    private Parking(Builder builder) {
        setId(builder.id);
        coordinates = builder.coordinates;
        address = builder.address;
        totalPlaces = builder.totalPlaces;
        timeToAvailableSlots = builder.timeToAvailableSlots;
    }

    public void bookParkingSlots(Set<BookingItem> bookingItems) {
        bookingItems.forEach(bookingItem -> {
            Integer available = timeToAvailableSlots.getOrDefault(bookingItem.getStartDateTime(), totalPlaces);
            if (available == 0) {
                throw new BusinessValidationException("All slots for this time is already booked.");
            }
            timeToAvailableSlots.put(bookingItem.getStartDateTime(), available - 1);
        });
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getAddress() {
        return address;
    }

    public Integer getTotalPlaces() {
        return totalPlaces;
    }

    public Map<OffsetDateTime, Integer> getTimeToAvailableSlots() {
        return timeToAvailableSlots;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private Coordinates coordinates;
        private String address;
        private Integer totalPlaces;
        private Map<OffsetDateTime, Integer> timeToAvailableSlots;

        private Builder() {}

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder coordinates(Coordinates val) {
            coordinates = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder totalPlaces(Integer val) {
            totalPlaces = val;
            return this;
        }

        public Builder timeToAvailableSlots(Map<OffsetDateTime, Integer> val) {
            timeToAvailableSlots = val;
            return this;
        }

        public Parking build() {
            return new Parking(this);
        }
    }
}

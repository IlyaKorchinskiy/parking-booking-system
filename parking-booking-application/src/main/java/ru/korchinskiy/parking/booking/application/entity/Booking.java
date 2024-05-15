package ru.korchinskiy.parking.booking.application.entity;

import java.util.Set;
import java.util.UUID;

public class Booking extends BaseEntity<UUID> {

    private final UUID parkingId;
    private final UUID userId;
    private final Set<BookingItem> bookingItems;

    private Booking(Builder builder) {
        setId(builder.id);
        parkingId = builder.parkingId;
        userId = builder.userId;
        bookingItems = builder.bookingItems;
    }

    public void initializeAndValidate() {
        setId(UUID.randomUUID());
        bookingItems.forEach(BookingItem::validate);
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Set<BookingItem> getBookingItems() {
        return bookingItems;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private UUID parkingId;
        private UUID userId;
        private Set<BookingItem> bookingItems;

        private Builder() {}

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder parkingId(UUID val) {
            parkingId = val;
            return this;
        }

        public Builder userId(UUID val) {
            userId = val;
            return this;
        }

        public Builder bookingItems(Set<BookingItem> val) {
            bookingItems = val;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}

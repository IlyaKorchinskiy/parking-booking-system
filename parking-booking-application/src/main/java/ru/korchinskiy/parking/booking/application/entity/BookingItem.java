package ru.korchinskiy.parking.booking.application.entity;

import ru.korchinskiy.parking.booking.application.exception.BusinessValidationException;

import java.time.OffsetDateTime;
import java.util.Objects;

public class BookingItem {

    private final OffsetDateTime startDateTime;
    private final String vehicleNumber;

    public BookingItem(OffsetDateTime startDateTime, String vehicleNumber) {
        this.startDateTime = startDateTime;
        this.vehicleNumber = vehicleNumber;
    }

    public void validate() {
        if (startDateTime.getMinute() != 0 || startDateTime.getSecond() != 0) {
            throw new BusinessValidationException("Start time of booking is not correct.");
        }
    }

    public OffsetDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingItem that = (BookingItem) o;
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(vehicleNumber, that.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, vehicleNumber);
    }
}

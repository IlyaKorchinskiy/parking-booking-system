package ru.korchinskiy.parking.booking.application.exception;

public class BusinessValidationException extends RuntimeException {

    public BusinessValidationException(String message) {
        super(message);
    }
}

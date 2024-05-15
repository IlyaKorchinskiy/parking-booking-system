package ru.korchinskiy.parking.booking.application.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}

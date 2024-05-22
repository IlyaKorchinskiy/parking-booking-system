package ru.korchinskiy.parking.booking.infrastructure.rest.exception;

import lombok.extern.slf4j.Slf4j;
import openapi.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.korchinskiy.parking.booking.application.exception.BusinessValidationException;
import ru.korchinskiy.parking.booking.application.exception.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({BusinessValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        log.warn("Exception happened: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(EntityNotFoundException exception) {
        log.warn("Exception happened: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnknownException(Exception exception) {
        log.error("Exception happened: {}", exception.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

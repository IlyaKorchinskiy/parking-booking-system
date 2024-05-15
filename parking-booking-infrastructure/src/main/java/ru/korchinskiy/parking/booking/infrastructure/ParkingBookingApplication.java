package ru.korchinskiy.parking.booking.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.korchinskiy.parking.booking.application.annotation.UseCaseService;

@SpringBootApplication
@ComponentScan(
        basePackages = "ru.korchinskiy.parking.booking",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCaseService.class))
public class ParkingBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingBookingApplication.class, args);
    }
}

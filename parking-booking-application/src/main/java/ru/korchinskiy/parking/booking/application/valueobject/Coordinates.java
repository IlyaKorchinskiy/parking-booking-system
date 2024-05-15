package ru.korchinskiy.parking.booking.application.valueobject;

import java.math.BigDecimal;

public class Coordinates {
    private final BigDecimal x;
    private final BigDecimal y;

    public Coordinates(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }
}

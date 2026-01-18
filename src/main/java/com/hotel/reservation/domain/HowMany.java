package com.hotel.reservation.domain;

import java.util.Objects;

/**
 * Represents a quantity value in reservations.
 * As per UML: has number attribute and associations with RoomType and Reservation.
 */
public class HowMany {
    private final int number;

    public HowMany(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        if (number > 10) {
            throw new IllegalArgumentException("Number cannot exceed 10 rooms per reservation");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HowMany howMany = (HowMany) o;
        return number == howMany.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "HowMany{number=" + number + "}";
    }
}

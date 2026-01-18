package com.hotel.reservation.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Value object representing a date.
 * Wraps java.time.LocalDate for domain modeling.
 */
public class Date {
    private final LocalDate localDate;

    public Date(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.localDate = localDate;
    }

    public Date(int year, int month, int day) {
        try {
            this.localDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date: " + year + "-" + month + "-" + day, e);
        }
    }

    public boolean isBefore(Date other) {
        return this.localDate.isBefore(other.localDate);
    }

    public boolean isAfter(Date other) {
        return this.localDate.isAfter(other.localDate);
    }

    public boolean isEqual(Date other) {
        return this.localDate.isEqual(other.localDate);
    }

    public long daysBetween(Date other) {
        return Math.abs(java.time.temporal.ChronoUnit.DAYS.between(this.localDate, other.localDate));
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getYear() {
        return localDate.getYear();
    }

    public int getMonth() {
        return localDate.getMonthValue();
    }

    public int getDay() {
        return localDate.getDayOfMonth();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate);
    }

    @Override
    public String toString() {
        return localDate.toString();
    }
}

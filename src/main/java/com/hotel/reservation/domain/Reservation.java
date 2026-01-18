package com.hotel.reservation.domain;

import com.hotel.reservation.model.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a hotel room reservation.
 * As per UML: has reservationDate, startDate, endDate, number, and create() operation.
 */
public class Reservation {
    private final Date reservationDate;
    private final Date startDate;
    private final Date endDate;
    private final int number;
    private final ReserverPayer reserverPayer;
    private final List<Room> rooms;
    private HowMany quantity;

    private Reservation(Date reservationDate, Date startDate, Date endDate, int number, ReserverPayer reserverPayer) {
        validateReservationDate(reservationDate);
        validateDates(startDate, endDate);
        validateNumber(number);
        validateReserverPayer(reserverPayer);
        
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.number = number;
        this.reserverPayer = reserverPayer;
        this.rooms = new ArrayList<>();
    }

    public static Reservation create(Date reservationDate, Date startDate, Date endDate, int number, ReserverPayer reserverPayer) {
        return new Reservation(reservationDate, startDate, endDate, number, reserverPayer);
    }

    private void validateReservationDate(Date reservationDate) {
        if (reservationDate == null) {
            throw new IllegalArgumentException("Reservation date cannot be null");
        }
    }

    private void validateDates(Date startDate, Date endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    private void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Reservation number must be positive");
        }
    }

    private void validateReserverPayer(ReserverPayer reserverPayer) {
        if (reserverPayer == null) {
            throw new IllegalArgumentException("ReserverPayer cannot be null");
        }
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumber() {
        return number;
    }

    public ReserverPayer getReserverPayer() {
        return reserverPayer;
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public HowMany getQuantity() {
        return quantity;
    }

    public void setQuantity(HowMany quantity) {
        this.quantity = quantity;
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (!rooms.contains(room)) {
            rooms.add(room);
            room.addReservation(this);
        }
    }

    public boolean isActive() {
        Date today = new Date(java.time.LocalDate.now());
        return !startDate.isAfter(today) && !endDate.isBefore(today);
    }

    public long getDurationInDays() {
        return startDate.daysBetween(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return number == that.number && Objects.equals(reserverPayer, that.reserverPayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, reserverPayer);
    }

    @Override
    public String toString() {
        return "Reservation{number=" + number + ", startDate=" + startDate + 
               ", endDate=" + endDate + ", rooms=" + rooms.size() + "}";
    }
}

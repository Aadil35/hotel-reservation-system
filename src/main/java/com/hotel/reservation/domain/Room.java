package com.hotel.reservation.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a physical room in a hotel.
 * As per UML: has number attribute, createGuest() operation, and associations.
 */
public class Room {
    private final int number;
    private final Hotel hotel;
    private final RoomType roomType;
    private Guest occupant;
    private final List<Reservation> reservations;

    public Room(int number, Hotel hotel, RoomType roomType) {
        validateNumber(number);
        validateHotel(hotel);
        validateRoomType(roomType);
        
        this.number = number;
        this.hotel = hotel;
        this.roomType = roomType;
        this.reservations = new ArrayList<>();
    }

    private void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be positive");
        }
    }

    private void validateHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
    }

    private void validateRoomType(RoomType roomType) {
        if (roomType == null) {
            throw new IllegalArgumentException("Room type cannot be null");
        }
    }

    public int getNumber() {
        return number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Guest getOccupant() {
        return occupant;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public boolean isAvailable() {
        return !isOccupied();
    }

    public Guest createGuest() {
        if (isOccupied()) {
            throw new IllegalStateException("Room is already occupied");
        }
        // Note: This method creates a guest association but guest creation is typically handled elsewhere
        // Keeping it as per UML but it may be a design artifact
        return occupant;
    }

    void setOccupant(Guest guest) {
        this.occupant = guest;
    }

    void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, hotel);
    }

    @Override
    public String toString() {
        return "Room{number=" + number + ", type=" + roomType.getKind() + ", occupied=" + isOccupied() + "}";
    }
}

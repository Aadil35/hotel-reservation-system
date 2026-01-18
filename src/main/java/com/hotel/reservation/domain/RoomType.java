package com.hotel.reservation.domain;

import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.RoomKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a type of room in a hotel.
 * As per UML: has kind, cost, and associations with Hotel, Room, and HowMany.
 */
public class RoomType {
    private final RoomKind kind;
    private final Money cost;
    private final Hotel hotel;
    private final List<Room> rooms;
    private final List<HowMany> quantities;

    public RoomType(RoomKind kind, Money cost, Hotel hotel) {
        validateKind(kind);
        validateCost(cost);
        validateHotel(hotel);
        
        this.kind = kind;
        this.cost = cost;
        this.hotel = hotel;
        this.rooms = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    private void validateKind(RoomKind kind) {
        if (kind == null) {
            throw new IllegalArgumentException("Room kind cannot be null");
        }
    }

    private void validateCost(Money cost) {
        if (cost == null) {
            throw new IllegalArgumentException("Room cost cannot be null");
        }
    }

    private void validateHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
    }

    public RoomKind getKind() {
        return kind;
    }

    public Money getCost() {
        return cost;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public List<HowMany> getQuantities() {
        return new ArrayList<>(quantities);
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    void addQuantity(HowMany quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (!quantities.contains(quantity)) {
            quantities.add(quantity);
        }
    }

    public int getAvailableRoomCount() {
        return (int) rooms.stream().filter(room -> !room.isOccupied()).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return kind == roomType.kind && Objects.equals(hotel, roomType.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, hotel);
    }

    @Override
    public String toString() {
        return "RoomType{kind=" + kind + ", cost=" + cost + "}";
    }
}

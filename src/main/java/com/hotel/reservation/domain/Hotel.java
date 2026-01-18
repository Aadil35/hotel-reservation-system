package com.hotel.reservation.domain;

import com.hotel.reservation.model.Date;
import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a hotel in the hotel chain.
 * As per UML: has name, createReservation(), available() operations.
 */
public class Hotel {
    private final Name name;
    private HotelChain hotelChain;
    private final List<RoomType> roomTypes;
    private final List<Room> rooms;

    public Hotel(Name name) {
        validateName(name);
        this.name = name;
        this.roomTypes = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    private void validateName(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Hotel name cannot be null");
        }
    }

    public Name getName() {
        return name;
    }

    public HotelChain getHotelChain() {
        return hotelChain;
    }

    public List<RoomType> getRoomTypes() {
        return new ArrayList<>(roomTypes);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    void setHotelChain(HotelChain hotelChain) {
        this.hotelChain = hotelChain;
    }

    public void addRoomType(RoomType roomType) {
        if (roomType == null) {
            throw new IllegalArgumentException("Room type cannot be null");
        }
        if (!roomTypes.contains(roomType)) {
            roomTypes.add(roomType);
        }
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public Reservation createReservation(Date reservationDate, Date startDate, Date endDate, 
                                       int reservationNumber, ReserverPayer reserverPayer) {
        return Reservation.create(reservationDate, startDate, endDate, reservationNumber, reserverPayer);
    }

    public boolean available(RoomKind roomKind, Date startDate, Date endDate, int quantity) {
        if (roomKind == null || startDate == null || endDate == null) {
            return false;
        }
        if (quantity <= 0) {
            return false;
        }

        RoomType requestedRoomType = findRoomType(roomKind);
        if (requestedRoomType == null) {
            return false;
        }

        List<Room> availableRooms = requestedRoomType.getRooms().stream()
                .filter(room -> isRoomAvailableForDates(room, startDate, endDate))
                .collect(Collectors.toList());

        return availableRooms.size() >= quantity;
    }

    private RoomType findRoomType(RoomKind roomKind) {
        return roomTypes.stream()
                .filter(rt -> rt.getKind() == roomKind)
                .findFirst()
                .orElse(null);
    }

    private boolean isRoomAvailableForDates(Room room, Date startDate, Date endDate) {
        if (room.isOccupied()) {
            return false;
        }

        List<Reservation> conflictingReservations = room.getReservations().stream()
                .filter(reservation -> datesOverlap(
                    reservation.getStartDate(), reservation.getEndDate(),
                    startDate, endDate))
                .collect(Collectors.toList());

        return conflictingReservations.isEmpty();
    }

    private boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    public List<Room> findAvailableRooms(RoomKind roomKind, Date startDate, Date endDate, int quantity) {
        RoomType requestedRoomType = findRoomType(roomKind);
        if (requestedRoomType == null) {
            return new ArrayList<>();
        }

        return requestedRoomType.getRooms().stream()
                .filter(room -> isRoomAvailableForDates(room, startDate, endDate))
                .limit(quantity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Hotel{name=" + name + ", rooms=" + rooms.size() + ", roomTypes=" + roomTypes.size() + "}";
    }
}

package com.hotel.reservation.domain;

import com.hotel.reservation.model.Address;
import com.hotel.reservation.model.CreditCard;
import com.hotel.reservation.model.Date;
import com.hotel.reservation.model.Identity;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main aggregate root for the hotel reservation system.
 * As per UML: manages reservations, guests, hotels, and reserverPayers.
 */
public class HotelChain {
    private final Map<Integer, ReserverPayer> reserverPayers;
    private final Map<String, Guest> guests;
    private final Map<String, Hotel> hotels;

    public HotelChain() {
        this.reserverPayers = new HashMap<>();
        this.guests = new HashMap<>();
        this.hotels = new HashMap<>();
    }

    public ReserverPayer createReserverPayer(CreditCard creditCard, Identity id, int number) {
        if (reserverPayers.containsKey(number)) {
            throw new IllegalArgumentException("ReserverPayer with number " + number + " already exists");
        }
        
        ReserverPayer reserverPayer = ReserverPayer.create(creditCard, id, number);
        reserverPayers.put(number, reserverPayer);
        return reserverPayer;
    }

    public Reservation makeReservation(int reserverPayerNumber, String guestName, String hotelName,
                                      RoomKind roomKind, Date startDate, Date endDate, int quantity) {
        // Validate inputs
        if (!canMakeReservation(reserverPayerNumber, guestName, hotelName, roomKind, startDate, endDate, quantity)) {
            throw new IllegalStateException("Cannot make reservation: validation failed");
        }

        ReserverPayer reserverPayer = reserverPayers.get(reserverPayerNumber);
        Guest guest = guests.get(guestName);
        Hotel hotel = hotels.get(hotelName);

        // Create reservation
        int reservationNumber = generateReservationNumber();
        Date reservationDate = new Date(java.time.LocalDate.now());
        Reservation reservation = hotel.createReservation(reservationDate, startDate, endDate, 
                                                         reservationNumber, reserverPayer);

        // Find available rooms
        List<Room> availableRooms = hotel.findAvailableRooms(roomKind, startDate, endDate, quantity);
        
        if (availableRooms.size() < quantity) {
            throw new IllegalStateException("Not enough available rooms");
        }

        // Assign rooms to reservation
        HowMany howMany = new HowMany(quantity);
        reservation.setQuantity(howMany);
        
        for (int i = 0; i < quantity; i++) {
            reservation.addRoom(availableRooms.get(i));
        }

        // Link reservation to reserverPayer
        reserverPayer.setReservation(reservation);

        return reservation;
    }

    public void cancelReservation(int reservationNumber) {
        if (!canCancelReservation(reservationNumber)) {
            throw new IllegalStateException("Cannot cancel reservation: validation failed or reservation not found");
        }

        Reservation reservation = findReservationByNumber(reservationNumber);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation with number " + reservationNumber + " not found");
        }

        // Remove reservation from rooms
        for (Room room : reservation.getRooms()) {
            room.removeReservation(reservation);
        }

        // Clear reserverPayer link
        if (reservation.getReserverPayer() != null) {
            reservation.getReserverPayer().setReservation(null);
        }
    }

    public void checkinGuest(String guestName, int roomNumber, String hotelName) {
        if (!canCheckinGuest(guestName, roomNumber, hotelName)) {
            throw new IllegalStateException("Cannot check in guest: validation failed");
        }

        Guest guest = guests.get(guestName);
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRooms().stream()
                .filter(r -> r.getNumber() == roomNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (room.isOccupied()) {
            throw new IllegalStateException("Room is already occupied");
        }

        if (guest.isCheckedIn()) {
            throw new IllegalStateException("Guest is already checked in to another room");
        }

        room.setOccupant(guest);
        guest.setOccupiedRoom(room);
    }

    public void checkOutGuest(String guestName) {
        if (!canCheckOutGuest(guestName)) {
            throw new IllegalStateException("Cannot check out guest: validation failed");
        }

        Guest guest = guests.get(guestName);
        if (!guest.isCheckedIn()) {
            throw new IllegalStateException("Guest is not checked in");
        }

        Room room = guest.getOccupiedRoom();
        room.setOccupant(null);
        guest.setOccupiedRoom(null);
    }

    private boolean canMakeReservation(int reserverPayerNumber, String guestName, String hotelName,
                                      RoomKind roomKind, Date startDate, Date endDate, int quantity) {
        if (!reserverPayers.containsKey(reserverPayerNumber)) {
            return false;
        }
        if (!guests.containsKey(guestName)) {
            return false;
        }
        if (!hotels.containsKey(hotelName)) {
            return false;
        }
        if (roomKind == null || startDate == null || endDate == null) {
            return false;
        }
        if (quantity <= 0) {
            return false;
        }

        Hotel hotel = hotels.get(hotelName);
        return hotel.available(roomKind, startDate, endDate, quantity);
    }

    private boolean canCancelReservation(int reservationNumber) {
        return findReservationByNumber(reservationNumber) != null;
    }

    private boolean canCheckinGuest(String guestName, int roomNumber, String hotelName) {
        if (!guests.containsKey(guestName)) {
            return false;
        }
        if (!hotels.containsKey(hotelName)) {
            return false;
        }

        Hotel hotel = hotels.get(hotelName);
        return hotel.getRooms().stream().anyMatch(r -> r.getNumber() == roomNumber);
    }

    private boolean canCheckOutGuest(String guestName) {
        if (!guests.containsKey(guestName)) {
            return false;
        }
        Guest guest = guests.get(guestName);
        return guest.isCheckedIn();
    }

    private Reservation findReservationByNumber(int reservationNumber) {
        return reserverPayers.values().stream()
                .map(ReserverPayer::getReservation)
                .filter(r -> r != null && r.getNumber() == reservationNumber)
                .findFirst()
                .orElse(null);
    }

    private int generateReservationNumber() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public void addGuest(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null");
        }
        String guestName = guest.getName().getValue();
        if (guests.containsKey(guestName)) {
            throw new IllegalArgumentException("Guest with name " + guestName + " already exists");
        }
        guests.put(guestName, guest);
    }

    public void addHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        String hotelName = hotel.getName().getValue();
        if (hotels.containsKey(hotelName)) {
            throw new IllegalArgumentException("Hotel with name " + hotelName + " already exists");
        }
        hotel.setHotelChain(this);
        hotels.put(hotelName, hotel);
    }

    public List<Reservation> getAllReservations() {
        return reserverPayers.values().stream()
                .map(ReserverPayer::getReservation)
                .filter(r -> r != null)
                .collect(Collectors.toList());
    }

    public ReserverPayer getReserverPayer(int number) {
        return reserverPayers.get(number);
    }

    public Guest getGuest(String name) {
        return guests.get(name);
    }

    public Hotel getHotel(String name) {
        return hotels.get(name);
    }
}

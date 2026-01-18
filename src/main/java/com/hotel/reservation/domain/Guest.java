package com.hotel.reservation.domain;

import com.hotel.reservation.model.Address;
import com.hotel.reservation.model.Name;

import java.util.Objects;

/**
 * Represents a guest in the hotel reservation system.
 * As per UML: has name, addressDetails, and create() operation.
 */
public class Guest {
    private final Name name;
    private final Address addressDetails;
    private Room occupiedRoom;

    private Guest(Name name, Address addressDetails) {
        validateName(name);
        validateAddress(addressDetails);
        
        this.name = name;
        this.addressDetails = addressDetails;
    }

    public static Guest create(Name name, Address addressDetails) {
        return new Guest(name, addressDetails);
    }

    private void validateName(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Guest name cannot be null");
        }
    }

    private void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Guest address cannot be null");
        }
    }

    public Name getName() {
        return name;
    }

    public Address getAddressDetails() {
        return addressDetails;
    }

    public Room getOccupiedRoom() {
        return occupiedRoom;
    }

    void setOccupiedRoom(Room room) {
        this.occupiedRoom = room;
    }

    public boolean isCheckedIn() {
        return occupiedRoom != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) && Objects.equals(addressDetails, guest.addressDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, addressDetails);
    }

    @Override
    public String toString() {
        return "Guest{name=" + name + ", addressDetails=" + addressDetails + "}";
    }
}

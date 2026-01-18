package com.hotel.reservation.domain;

import com.hotel.reservation.model.Address;
import com.hotel.reservation.model.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void testCreateGuest() {
        // Arrange
        Name name = new Name("John Doe");
        Address address = new Address("123 Main St", "New York", "NY", "10001", "USA");

        // Act
        Guest guest = Guest.create(name, address);

        // Assert
        assertNotNull(guest);
        assertEquals(name, guest.getName());
        assertEquals(address, guest.getAddressDetails());
        assertFalse(guest.isCheckedIn());
        assertNull(guest.getOccupiedRoom());
    }

    @Test
    void testCreateGuestWithNullName() {
        // Arrange
        Address address = new Address("123 Main St", "New York", "NY", "10001", "USA");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Guest.create(null, address);
        });
    }

    @Test
    void testCreateGuestWithNullAddress() {
        // Arrange
        Name name = new Name("John Doe");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Guest.create(name, null);
        });
    }

    @Test
    void testGuestEquality() {
        // Arrange
        Name name1 = new Name("John Doe");
        Address address1 = new Address("123 Main St", "New York", "NY", "10001", "USA");
        Name name2 = new Name("John Doe");
        Address address2 = new Address("123 Main St", "New York", "NY", "10001", "USA");

        // Act
        Guest guest1 = Guest.create(name1, address1);
        Guest guest2 = Guest.create(name2, address2);

        // Assert
        assertEquals(guest1, guest2);
        assertEquals(guest1.hashCode(), guest2.hashCode());
    }
}

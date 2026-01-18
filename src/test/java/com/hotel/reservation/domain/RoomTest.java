package com.hotel.reservation.domain;

import com.hotel.reservation.model.Address;
import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Hotel hotel;
    private RoomType roomType;
    private Room room;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(new Name("Grand Hotel"));
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), hotel);
        room = new Room(101, hotel, roomType);
    }

    @Test
    void testCreateRoom() {
        // Arrange, Act
        Room newRoom = new Room(102, hotel, roomType);

        // Assert
        assertNotNull(newRoom);
        assertEquals(102, newRoom.getNumber());
        assertEquals(hotel, newRoom.getHotel());
        assertEquals(roomType, newRoom.getRoomType());
        assertFalse(newRoom.isOccupied());
        assertTrue(newRoom.isAvailable());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void testCreateRoomWithInvalidNumber(int number) {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Room(number, hotel, roomType);
        });
    }

    @Test
    void testCreateRoomWithNullHotel() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Room(101, null, roomType);
        });
    }

    @Test
    void testCreateRoomWithNullRoomType() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Room(101, hotel, null);
        });
    }

    @Test
    void testRoomOccupiedState() {
        // Arrange
        Guest guest = Guest.create(new Name("John Doe"), 
                                   new Address("123 St", "City", "State", "12345", "Country"));

        // Act
        room.setOccupant(guest);

        // Assert
        assertTrue(room.isOccupied());
        assertFalse(room.isAvailable());
        assertEquals(guest, room.getOccupant());
    }

    @Test
    void testRoomEquality() {
        // Arrange
        Room room1 = new Room(101, hotel, roomType);
        Room room2 = new Room(101, hotel, roomType);

        // Act & Assert
        assertEquals(room1, room2);
        assertEquals(room1.hashCode(), room2.hashCode());
    }
}

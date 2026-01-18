package com.hotel.reservation.domain;

import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTypeTest {

    private Hotel hotel;
    private RoomType roomType;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(new Name("Grand Hotel"));
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), hotel);
    }

    @Test
    void testCreateRoomType() {
        // Arrange, Act
        RoomType type = new RoomType(RoomKind.DOUBLE, new Money(150.0, "USD"), hotel);

        // Assert
        assertNotNull(type);
        assertEquals(RoomKind.DOUBLE, type.getKind());
        assertEquals(new Money(150.0, "USD"), type.getCost());
        assertEquals(hotel, type.getHotel());
    }

    @Test
    void testCreateRoomTypeWithNullKind() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomType(null, new Money(100.0, "USD"), hotel);
        });
    }

    @Test
    void testCreateRoomTypeWithNullCost() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomType(RoomKind.SINGLE, null, hotel);
        });
    }

    @Test
    void testCreateRoomTypeWithNullHotel() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), null);
        });
    }

    @Test
    void testGetAvailableRoomCount() {
        // Arrange
        Room room1 = new Room(101, hotel, roomType);
        Room room2 = new Room(102, hotel, roomType);
        roomType.addRoom(room1);
        roomType.addRoom(room2);

        // Act & Assert
        assertEquals(2, roomType.getAvailableRoomCount());
    }
}

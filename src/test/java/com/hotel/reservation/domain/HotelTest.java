package com.hotel.reservation.domain;

import com.hotel.reservation.model.CreditCard;
import com.hotel.reservation.model.Date;
import com.hotel.reservation.model.Identity;
import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;
    private RoomType roomType;
    private Room room;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(new Name("Grand Hotel"));
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), hotel);
        hotel.addRoomType(roomType);
        room = new Room(101, hotel, roomType);
        hotel.addRoom(room);
        roomType.addRoom(room);
    }

    @Test
    void testCreateHotel() {
        // Arrange, Act
        Hotel newHotel = new Hotel(new Name("Test Hotel"));

        // Assert
        assertNotNull(newHotel);
        assertEquals(new Name("Test Hotel"), newHotel.getName());
    }

    @Test
    void testCreateHotelWithNullName() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Hotel(null);
        });
    }

    @Test
    void testAvailable() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);

        // Act
        boolean available = hotel.available(RoomKind.SINGLE, startDate, endDate, 1);

        // Assert
        assertTrue(available);
    }

    @Test
    void testNotAvailable() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);

        // Act
        boolean available = hotel.available(RoomKind.DOUBLE, startDate, endDate, 1);

        // Assert
        assertFalse(available);
    }

    @Test
    void testCreateReservation() {
        // Arrange
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123", Identity.IdentityType.PASSPORT);
        ReserverPayer payer = ReserverPayer.create(creditCard, identity, 1);
        Date reservationDate = new Date(2024, 1, 1);
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);

        // Act
        Reservation reservation = hotel.createReservation(reservationDate, startDate, endDate, 1001, payer);

        // Assert
        assertNotNull(reservation);
        assertEquals(payer, reservation.getReserverPayer());
    }
}

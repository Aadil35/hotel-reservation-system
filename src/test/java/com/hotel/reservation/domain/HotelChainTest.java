package com.hotel.reservation.domain;

import com.hotel.reservation.model.Address;
import com.hotel.reservation.model.CreditCard;
import com.hotel.reservation.model.Date;
import com.hotel.reservation.model.Identity;
import com.hotel.reservation.model.Money;
import com.hotel.reservation.model.Name;
import com.hotel.reservation.model.RoomKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelChainTest {

    private HotelChain hotelChain;
    private Hotel hotel;
    private Guest guest;
    private ReserverPayer reserverPayer;
    private RoomType roomType;
    private Room room;

    @BeforeEach
    void setUp() {
        hotelChain = new HotelChain();
        
        // Setup hotel
        hotel = new Hotel(new Name("Grand Hotel"));
        hotelChain.addHotel(hotel);
        
        // Setup room type and room
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), hotel);
        hotel.addRoomType(roomType);
        room = new Room(101, hotel, roomType);
        hotel.addRoom(room);
        roomType.addRoom(room);
        
        // Setup guest
        guest = Guest.create(new Name("John Doe"), 
                            new Address("123 Main St", "New York", "NY", "10001", "USA"));
        hotelChain.addGuest(guest);
        
        // Setup reserver payer
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123", Identity.IdentityType.PASSPORT);
        reserverPayer = hotelChain.createReserverPayer(creditCard, identity, 1);
    }

    @Test
    void testCreateReserverPayer() {
        // Arrange
        CreditCard creditCard = new CreditCard("9876543210987654", "Jane Smith", "06/26", "456");
        Identity identity = new Identity("ID456", Identity.IdentityType.NATIONAL_ID);

        // Act
        ReserverPayer payer = hotelChain.createReserverPayer(creditCard, identity, 2);

        // Assert
        assertNotNull(payer);
        assertEquals(2, payer.getNumber());
    }

    @Test
    void testMakeReservation() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);

        // Act
        Reservation reservation = hotelChain.makeReservation(1, "John Doe", "Grand Hotel", 
                                                             RoomKind.SINGLE, startDate, endDate, 1);

        // Assert
        assertNotNull(reservation);
        assertEquals(reserverPayer, reservation.getReserverPayer());
        assertFalse(reservation.getRooms().isEmpty());
    }

    @Test
    void testMakeReservationFailsWhenRoomNotAvailable() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            hotelChain.makeReservation(1, "John Doe", "Grand Hotel", 
                                      RoomKind.DOUBLE, startDate, endDate, 1);
        });
    }

    @Test
    void testCancelReservation() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);
        Reservation reservation = hotelChain.makeReservation(1, "John Doe", "Grand Hotel", 
                                                             RoomKind.SINGLE, startDate, endDate, 1);

        // Act
        hotelChain.cancelReservation(reservation.getNumber());

        // Assert
        assertNull(reserverPayer.getReservation());
    }

    @Test
    void testCheckinGuest() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);
        hotelChain.makeReservation(1, "John Doe", "Grand Hotel", 
                                   RoomKind.SINGLE, startDate, endDate, 1);

        // Act
        hotelChain.checkinGuest("John Doe", 101, "Grand Hotel");

        // Assert
        assertTrue(guest.isCheckedIn());
        assertTrue(room.isOccupied());
    }

    @Test
    void testCheckOutGuest() {
        // Arrange
        Date startDate = new Date(2024, 6, 1);
        Date endDate = new Date(2024, 6, 5);
        hotelChain.makeReservation(1, "John Doe", "Grand Hotel", 
                                   RoomKind.SINGLE, startDate, endDate, 1);
        hotelChain.checkinGuest("John Doe", 101, "Grand Hotel");

        // Act
        hotelChain.checkOutGuest("John Doe");

        // Assert
        assertFalse(guest.isCheckedIn());
        assertFalse(room.isOccupied());
    }
}

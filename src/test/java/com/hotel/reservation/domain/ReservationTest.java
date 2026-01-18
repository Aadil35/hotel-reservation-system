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

class ReservationTest {

    private ReserverPayer reserverPayer;
    private Date startDate;
    private Date endDate;
    private Date reservationDate;

    @BeforeEach
    void setUp() {
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123", Identity.IdentityType.PASSPORT);
        reserverPayer = ReserverPayer.create(creditCard, identity, 1);
        reservationDate = new Date(2024, 1, 1);
        startDate = new Date(2024, 6, 1);
        endDate = new Date(2024, 6, 5);
    }

    @Test
    void testCreateReservation() {
        // Arrange, Act
        Reservation reservation = Reservation.create(reservationDate, startDate, endDate, 1001, reserverPayer);

        // Assert
        assertNotNull(reservation);
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(1001, reservation.getNumber());
        assertEquals(reserverPayer, reservation.getReserverPayer());
    }

    @Test
    void testCreateReservationWithNullReservationDate() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Reservation.create(null, startDate, endDate, 1001, reserverPayer);
        });
    }

    @Test
    void testCreateReservationWithStartDateAfterEndDate() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Reservation.create(reservationDate, endDate, startDate, 1001, reserverPayer);
        });
    }

    @Test
    void testCreateReservationWithSameStartAndEndDate() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Reservation.create(reservationDate, startDate, startDate, 1001, reserverPayer);
        });
    }

    @Test
    void testReservationDuration() {
        // Arrange
        Reservation reservation = Reservation.create(reservationDate, startDate, endDate, 1001, reserverPayer);

        // Act
        long duration = reservation.getDurationInDays();

        // Assert
        assertEquals(4, duration);
    }

    @Test
    void testReservationEquality() {
        // Arrange
        Reservation reservation1 = Reservation.create(reservationDate, startDate, endDate, 1001, reserverPayer);
        Reservation reservation2 = Reservation.create(reservationDate, startDate, endDate, 1001, reserverPayer);

        // Act & Assert
        assertEquals(reservation1, reservation2);
    }
}

package com.hotel.reservation.domain;

import com.hotel.reservation.model.CreditCard;
import com.hotel.reservation.model.Identity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ReserverPayerTest {

    @Test
    void testCreateReserverPayer() {
        // Arrange
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123456", Identity.IdentityType.PASSPORT);
        int number = 1;

        // Act
        ReserverPayer reserverPayer = ReserverPayer.create(creditCard, identity, number);

        // Assert
        assertNotNull(reserverPayer);
        assertEquals(creditCard, reserverPayer.getCreditCardDetails());
        assertEquals(identity, reserverPayer.getId());
        assertEquals(number, reserverPayer.getNumber());
        assertNull(reserverPayer.getReservation());
    }

    @Test
    void testCreateReserverPayerWithNullCreditCard() {
        // Arrange
        Identity identity = new Identity("ID123456", Identity.IdentityType.PASSPORT);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            ReserverPayer.create(null, identity, 1);
        });
    }

    @Test
    void testCreateReserverPayerWithNullIdentity() {
        // Arrange
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            ReserverPayer.create(creditCard, null, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void testCreateReserverPayerWithInvalidNumber(int number) {
        // Arrange
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123456", Identity.IdentityType.PASSPORT);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            ReserverPayer.create(creditCard, identity, number);
        });
    }

    @Test
    void testReserverPayerEquality() {
        // Arrange
        CreditCard creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity = new Identity("ID123456", Identity.IdentityType.PASSPORT);

        // Act
        ReserverPayer payer1 = ReserverPayer.create(creditCard, identity, 1);
        ReserverPayer payer2 = ReserverPayer.create(creditCard, identity, 1);

        // Assert
        assertEquals(payer1, payer2);
    }
}

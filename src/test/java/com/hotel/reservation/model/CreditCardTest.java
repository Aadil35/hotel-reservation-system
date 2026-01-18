package com.hotel.reservation.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void testCreateValidCreditCard() {
        // Arrange
        String cardNumber = "1234567890123456";
        String cardHolderName = "John Doe";
        String expiryDate = "12/25";
        String cvv = "123";

        // Act
        CreditCard creditCard = new CreditCard(cardNumber, cardHolderName, expiryDate, cvv);

        // Assert
        assertNotNull(creditCard);
        assertEquals(cardNumber, creditCard.getCardNumber());
        assertEquals(cardHolderName, creditCard.getCardHolderName());
        assertEquals(expiryDate, creditCard.getExpiryDate());
        assertEquals(cvv, creditCard.getCvv());
    }

    @Test
    void testCreateCreditCardWithNullCardNumber() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCard(null, "John Doe", "12/25", "123");
        });
    }

    @Test
    void testCreateCreditCardWithInvalidCardNumber() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCard("12345", "John Doe", "12/25", "123");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testCreateCreditCardWithEmptyCardHolderName(String name) {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCard("1234567890123456", name, "12/25", "123");
        });
    }

    @Test
    void testCreateCreditCardWithInvalidExpiryDate() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCard("1234567890123456", "John Doe", "12-25", "123");
        });
    }

    @Test
    void testCreateCreditCardWithInvalidCvv() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCard("1234567890123456", "John Doe", "12/25", "12");
        });
    }

    @Test
    void testCreditCardEquality() {
        // Arrange
        CreditCard card1 = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        CreditCard card2 = new CreditCard("1234567890123456", "John Doe", "12/25", "123");

        // Act & Assert
        assertEquals(card1, card2);
        assertEquals(card1.hashCode(), card2.hashCode());
    }

    @Test
    void testCreditCardInequality() {
        // Arrange
        CreditCard card1 = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        CreditCard card2 = new CreditCard("9876543210987654", "John Doe", "12/25", "123");

        // Act & Assert
        assertNotEquals(card1, card2);
    }
}

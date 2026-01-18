package com.hotel.reservation.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void testCreateValidMoney() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.50");
        String currency = "USD";

        // Act
        Money money = new Money(amount, currency);

        // Assert
        assertNotNull(money);
        assertEquals(amount, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    void testCreateMoneyWithDouble() {
        // Arrange, Act
        Money money = new Money(100.50, "USD");

        // Assert
        assertNotNull(money);
        assertEquals(new BigDecimal("100.50"), money.getAmount());
    }

    @Test
    void testCreateMoneyWithNullAmount() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Money((BigDecimal) null, "USD");
        });
    }

    @Test
    void testCreateMoneyWithNegativeAmount() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(new BigDecimal("-100"), "USD");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "US", "USDD"})
    void testCreateMoneyWithInvalidCurrency(String currency) {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(new BigDecimal("100"), currency);
        });
    }

    @Test
    void testAddMoney() {
        // Arrange
        Money money1 = new Money(100.50, "USD");
        Money money2 = new Money(50.25, "USD");

        // Act
        Money result = money1.add(money2);

        // Assert
        assertEquals(new BigDecimal("150.75"), result.getAmount());
    }

    @Test
    void testAddMoneyDifferentCurrency() {
        // Arrange
        Money money1 = new Money(100.50, "USD");
        Money money2 = new Money(50.25, "EUR");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            money1.add(money2);
        });
    }

    @Test
    void testMultiplyMoney() {
        // Arrange
        Money money = new Money(100.50, "USD");

        // Act
        Money result = money.multiply(3);

        // Assert
        assertEquals(new BigDecimal("301.50"), result.getAmount());
    }

    @Test
    void testMoneyEquality() {
        // Arrange
        Money money1 = new Money(100.50, "USD");
        Money money2 = new Money(100.50, "USD");

        // Act & Assert
        assertEquals(money1, money2);
        assertEquals(money1.hashCode(), money2.hashCode());
    }
}

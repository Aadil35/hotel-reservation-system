package com.hotel.reservation.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testCreateValidDate() {
        // Arrange
        LocalDate localDate = LocalDate.of(2024, 6, 15);

        // Act
        Date date = new Date(localDate);

        // Assert
        assertNotNull(date);
        assertEquals(2024, date.getYear());
        assertEquals(6, date.getMonth());
        assertEquals(15, date.getDay());
    }

    @Test
    void testCreateDateWithConstructor() {
        // Arrange, Act
        Date date = new Date(2024, 6, 15);

        // Assert
        assertNotNull(date);
        assertEquals(2024, date.getYear());
        assertEquals(6, date.getMonth());
        assertEquals(15, date.getDay());
    }

    @Test
    void testCreateDateWithNull() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Date((LocalDate) null);
        });
    }

    @Test
    void testCreateDateWithInvalidDate() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(2024, 13, 1);
        });
    }

    @Test
    void testDateIsBefore() {
        // Arrange
        Date date1 = new Date(2024, 6, 15);
        Date date2 = new Date(2024, 6, 20);

        // Act & Assert
        assertTrue(date1.isBefore(date2));
        assertFalse(date2.isBefore(date1));
    }

    @Test
    void testDateIsAfter() {
        // Arrange
        Date date1 = new Date(2024, 6, 15);
        Date date2 = new Date(2024, 6, 20);

        // Act & Assert
        assertTrue(date2.isAfter(date1));
        assertFalse(date1.isAfter(date2));
    }

    @ParameterizedTest
    @CsvSource({
        "2024, 6, 15, 2024, 6, 25, 10",
        "2024, 1, 1, 2024, 1, 31, 30",
        "2024, 6, 1, 2024, 7, 1, 30"
    })
    void testDaysBetween(int y1, int m1, int d1, int y2, int m2, int d2, long expectedDays) {
        // Arrange
        Date date1 = new Date(y1, m1, d1);
        Date date2 = new Date(y2, m2, d2);

        // Act
        long days = date1.daysBetween(date2);

        // Assert
        assertEquals(expectedDays, days);
    }

    @Test
    void testDateEquality() {
        // Arrange
        Date date1 = new Date(2024, 6, 15);
        Date date2 = new Date(2024, 6, 15);

        // Act & Assert
        assertEquals(date1, date2);
        assertEquals(date1.hashCode(), date2.hashCode());
    }
}

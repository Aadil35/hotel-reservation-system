package com.hotel.reservation.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HowManyTest {

    @Test
    void testCreateHowMany() {
        // Arrange
        int number = 3;

        // Act
        HowMany howMany = new HowMany(number);

        // Assert
        assertNotNull(howMany);
        assertEquals(number, howMany.getNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void testCreateHowManyWithInvalidNumber(int number) {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new HowMany(number);
        });
    }

    @Test
    void testCreateHowManyWithNumberExceedingLimit() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new HowMany(11);
        });
    }

    @Test
    void testHowManyEquality() {
        // Arrange
        HowMany howMany1 = new HowMany(3);
        HowMany howMany2 = new HowMany(3);

        // Act & Assert
        assertEquals(howMany1, howMany2);
        assertEquals(howMany1.hashCode(), howMany2.hashCode());
    }
}

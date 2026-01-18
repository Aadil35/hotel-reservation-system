package com.hotel.reservation.domain;

import com.hotel.reservation.model.CreditCard;
import com.hotel.reservation.model.Identity;

import java.util.Objects;

/**
 * Represents a person who reserves and pays for hotel rooms.
 * As per UML: has creditCardDetails, id, and create() operation.
 */
public class ReserverPayer {
    private final CreditCard creditCardDetails;
    private final Identity id;
    private final int number;
    private Reservation reservation;

    private ReserverPayer(CreditCard creditCardDetails, Identity id, int number) {
        validateCreditCard(creditCardDetails);
        validateIdentity(id);
        validateNumber(number);
        
        this.creditCardDetails = creditCardDetails;
        this.id = id;
        this.number = number;
    }

    public static ReserverPayer create(CreditCard creditCardDetails, Identity id, int number) {
        return new ReserverPayer(creditCardDetails, id, number);
    }

    private void validateCreditCard(CreditCard creditCard) {
        if (creditCard == null) {
            throw new IllegalArgumentException("Credit card details cannot be null");
        }
    }

    private void validateIdentity(Identity identity) {
        if (identity == null) {
            throw new IllegalArgumentException("Identity cannot be null");
        }
    }

    private void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("ReserverPayer number must be positive");
        }
    }

    public CreditCard getCreditCardDetails() {
        return creditCardDetails;
    }

    public Identity getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Reservation getReservation() {
        return reservation;
    }

    void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserverPayer that = (ReserverPayer) o;
        return number == that.number && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "ReserverPayer{id=" + id + ", number=" + number + "}";
    }
}

package com.hotel.reservation.model;

import java.util.Objects;

/**
 * Value object representing identity information.
 */
public class Identity {
    private final String idNumber;
    private final IdentityType type;

    public enum IdentityType {
        PASSPORT, NATIONAL_ID, DRIVER_LICENSE
    }

    public Identity(String idNumber, IdentityType type) {
        validateIdNumber(idNumber);
        validateType(type);
        
        this.idNumber = idNumber;
        this.type = type;
    }

    private void validateIdNumber(String idNumber) {
        if (idNumber == null || idNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("ID number cannot be null or empty");
        }
        if (idNumber.length() < 5 || idNumber.length() > 20) {
            throw new IllegalArgumentException("ID number must be between 5 and 20 characters");
        }
    }

    private void validateType(IdentityType type) {
        if (type == null) {
            throw new IllegalArgumentException("Identity type cannot be null");
        }
    }

    public String getIdNumber() {
        return idNumber;
    }

    public IdentityType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(idNumber, identity.idNumber) && type == identity.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, type);
    }

    @Override
    public String toString() {
        return "Identity{idNumber='" + idNumber + "', type=" + type + "}";
    }
}

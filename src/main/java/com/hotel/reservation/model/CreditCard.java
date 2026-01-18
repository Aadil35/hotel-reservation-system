package com.hotel.reservation.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value object representing credit card details.
 * Implements defensive programming with validation.
 */
public class CreditCard {
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("\\d{13,19}");
    private static final Pattern CVV_PATTERN = Pattern.compile("\\d{3,4}");
    
    private final String cardNumber;
    private final String cardHolderName;
    private final String expiryDate;
    private final String cvv;

    public CreditCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        validateCardNumber(cardNumber);
        validateCardHolderName(cardHolderName);
        validateExpiryDate(expiryDate);
        validateCvv(cvv);
        
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    private void validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }
        if (!CARD_NUMBER_PATTERN.matcher(cardNumber.replaceAll("\\s", "")).matches()) {
            throw new IllegalArgumentException("Card number must be 13-19 digits");
        }
    }

    private void validateCardHolderName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Card holder name cannot be null or empty");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Card holder name must be between 2 and 50 characters");
        }
    }

    private void validateExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be null or empty");
        }
        if (!expiryDate.matches("\\d{2}/\\d{2}")) {
            throw new IllegalArgumentException("Expiry date must be in MM/YY format");
        }
    }

    private void validateCvv(String cvv) {
        if (cvv == null || cvv.trim().isEmpty()) {
            throw new IllegalArgumentException("CVV cannot be null or empty");
        }
        if (!CVV_PATTERN.matcher(cvv).matches()) {
            throw new IllegalArgumentException("CVV must be 3 or 4 digits");
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
               Objects.equals(cardHolderName, that.cardHolderName) &&
               Objects.equals(expiryDate, that.expiryDate) &&
               Objects.equals(cvv, that.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolderName, expiryDate, cvv);
    }

    @Override
    public String toString() {
        return "CreditCard{cardNumber='***" + cardNumber.substring(cardNumber.length() - 4) + 
               "', cardHolderName='" + cardHolderName + "'}";
    }
}

package com.hotel.reservation;

import com.hotel.reservation.domain.*;
import com.hotel.reservation.model.*;

import java.util.Scanner;

/**
 * Main entry point demonstrating all use cases of the hotel reservation system.
 * This class demonstrates the execution of all core functionality as per the
 * UML design.
 * Interactive version that accepts user input while demonstrating all use
 * cases.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static HotelChain hotelChain = new HotelChain();

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("HOTEL RESERVATION SYSTEM - INTERACTIVE DEMONSTRATION");
        System.out.println("=".repeat(80));
        System.out.println();

        try {
            // Initialize Hotel Chain
            System.out.println("✓ Hotel Chain initialized");
            System.out.println();

            // Use Case 1: Create Hotels
            demonstrateHotelCreation();

            // Use Case 2: Create Room Types and Rooms
            demonstrateRoomSetup();

            // Use Case 3: Create Guests
            demonstrateGuestCreation();

            // Use Case 4: Create ReserverPayers
            demonstrateReserverPayerCreation();

            // Use Case 5: Make Reservations
            demonstrateReservationCreation();

            // Use Case 6: Check Room Availability
            demonstrateAvailabilityCheck();

            // Use Case 7: Check-in Guest
            demonstrateCheckIn();

            // Use Case 8: Guest Check-out
            demonstrateCheckOut();

            // Use Case 9: Cancel Reservation
            demonstrateReservationCancellation();

            // Summary
            displaySummary();

        } catch (Exception e) {
            System.err.println("\nUnexpected error occurred: " + e.getMessage());
            System.err.println("Please try running the program again.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void demonstrateHotelCreation() {
        System.out.println("USE CASE 1: Creating Hotels");
        System.out.println("-".repeat(80));

        System.out.print("Enter first hotel name: ");
        String hotelName1 = scanner.nextLine().trim();
        if (hotelName1.isEmpty()) {
            System.out.println("Error: Hotel name is required!");
            hotelName1 = scanner.nextLine().trim();
        }

        System.out.print("Enter second hotel name: ");
        String hotelName2 = scanner.nextLine().trim();
        if (hotelName2.isEmpty()) {
            System.out.println("Error: Hotel name is required!");
            hotelName2 = scanner.nextLine().trim();
        }

        Hotel hotel1 = new Hotel(new Name(hotelName1));
        Hotel hotel2 = new Hotel(new Name(hotelName2));
        hotelChain.addHotel(hotel1);
        hotelChain.addHotel(hotel2);

        System.out.println("✓ Created Hotel: " + hotel1.getName().getValue());
        System.out.println("✓ Created Hotel: " + hotel2.getName().getValue());
        System.out.println();
    }

    private static void demonstrateRoomSetup() {
        System.out.println("USE CASE 2: Setting up Room Types and Rooms");
        System.out.println("-".repeat(80));

        // Get first hotel from chain
        Hotel hotel = null;
        // Try to get any hotel from the chain
        for (String hotelName : new String[] { "hotel", "paradise" }) {
            hotel = hotelChain.getHotel(hotelName);
            if (hotel != null)
                break;
        }
        // If still null, get first hotel by iterating (we'll need to add a method or
        // use a workaround)
        if (hotel == null) {
            // Get first hotel from chain - we'll use a simple approach
            System.out.print("Enter hotel name to set up rooms for: ");
            String hotelName = scanner.nextLine().trim();
            if (hotelName.isEmpty()) {
                System.out.println("Error: Hotel name is required!");
                hotelName = scanner.nextLine().trim();
            }
            hotel = hotelChain.getHotel(hotelName);
            if (hotel == null) {
                hotel = new Hotel(new Name(hotelName));
                hotelChain.addHotel(hotel);
            }
        }

        // Create room types
        System.out.print("Enter room price for SINGLE room (PKR): ");
        String singlePrice = scanner.nextLine().trim();
        double singlePriceValue = singlePrice.isEmpty() ? 5000.0
                : parseDoubleWithRetry(singlePrice, "room price for SINGLE room");

        System.out.print("Enter room price for DOUBLE room (PKR): ");
        String doublePrice = scanner.nextLine().trim();
        double doublePriceValue = doublePrice.isEmpty() ? 8000.0
                : parseDoubleWithRetry(doublePrice, "room price for DOUBLE room");

        RoomType singleRoomType = new RoomType(RoomKind.SINGLE, new Money(singlePriceValue, "PKR"), hotel);
        RoomType doubleRoomType = new RoomType(RoomKind.DOUBLE, new Money(doublePriceValue, "PKR"), hotel);
        RoomType suiteRoomType = new RoomType(RoomKind.SUITE, new Money(15000.0, "PKR"), hotel);

        hotel.addRoomType(singleRoomType);
        hotel.addRoomType(doubleRoomType);
        hotel.addRoomType(suiteRoomType);

        // Create rooms
        System.out.print("How many SINGLE rooms to create? (Recommended: 1-10, e.g., 3): ");
        String singleCount = scanner.nextLine().trim();
        int singleRoomCount = singleCount.isEmpty() ? 3 : parseIntWithRetry(singleCount, "number of SINGLE rooms");
        if (singleRoomCount < 1 || singleRoomCount > 20) {
            System.out.println("Warning: Room count should be between 1-20. Using default: 3");
            singleRoomCount = 3;
        }

        for (int i = 101; i < 101 + singleRoomCount; i++) {
            Room room = new Room(i, hotel, singleRoomType);
            hotel.addRoom(room);
            singleRoomType.addRoom(room);
        }

        System.out.print("How many DOUBLE rooms to create? (Recommended: 1-10, e.g., 2): ");
        String doubleCount = scanner.nextLine().trim();
        int doubleRoomCount = doubleCount.isEmpty() ? 2 : parseIntWithRetry(doubleCount, "number of DOUBLE rooms");
        if (doubleRoomCount < 1 || doubleRoomCount > 20) {
            System.out.println("Warning: Room count should be between 1-20. Using default: 2");
            doubleRoomCount = 2;
        }

        for (int i = 201; i < 201 + doubleRoomCount; i++) {
            Room room = new Room(i, hotel, doubleRoomType);
            hotel.addRoom(room);
            doubleRoomType.addRoom(room);
        }

        Room suiteRoom = new Room(301, hotel, suiteRoomType);
        hotel.addRoom(suiteRoom);
        suiteRoomType.addRoom(suiteRoom);

        System.out.println("✓ Created " + hotel.getRoomTypes().size() + " room types in " + hotel.getName().getValue());
        System.out.println("✓ Created " + hotel.getRooms().size() + " rooms in " + hotel.getName().getValue());
        System.out.println();
    }

    private static void demonstrateGuestCreation() {
        System.out.println("USE CASE 3: Creating Guests");
        System.out.println("-".repeat(80));

        System.out.print("Enter first guest name: ");
        String guestName1 = scanner.nextLine().trim();
        if (guestName1.isEmpty()) {
            System.out.println("Error: Guest name is required!");
            guestName1 = scanner.nextLine().trim();
        }

        System.out.print("Enter " + guestName1 + "'s street address: ");
        String street1 = scanner.nextLine().trim();
        if (street1.isEmpty()) {
            System.out.println("Error: Street address is required!");
            street1 = scanner.nextLine().trim();
        }

        System.out.print("Enter city: ");
        String city1 = scanner.nextLine().trim();
        if (city1.isEmpty()) {
            System.out.println("Error: City is required!");
            city1 = scanner.nextLine().trim();
        }

        System.out.print("Enter province: ");
        String state1 = scanner.nextLine().trim();
        if (state1.isEmpty()) {
            System.out.println("Error: Province is required!");
            state1 = scanner.nextLine().trim();
        }

        System.out.print("Enter postal code (5 digits, e.g., 76700, 75300): ");
        String zip1 = scanner.nextLine().trim();
        if (zip1.isEmpty()) {
            System.out.println("Error: Postal code is required!");
            zip1 = scanner.nextLine().trim();
        }

        System.out.print("Enter country: ");
        String country1 = scanner.nextLine().trim();
        if (country1.isEmpty())
            country1 = "Pakistan";

        Guest guest1 = Guest.create(
                new Name(guestName1),
                new Address(street1, city1, state1, zip1, country1));

        System.out.print("\nEnter second guest name: ");
        String guestName2 = scanner.nextLine().trim();
        if (guestName2.isEmpty()) {
            System.out.println("Error: Guest name is required!");
            guestName2 = scanner.nextLine().trim();
        }

        System.out.print("Enter " + guestName2 + "'s street address: ");
        String street2 = scanner.nextLine().trim();
        if (street2.isEmpty()) {
            System.out.println("Error: Street address is required!");
            street2 = scanner.nextLine().trim();
        }

        System.out.print("Enter city: ");
        String city2 = scanner.nextLine().trim();
        if (city2.isEmpty()) {
            System.out.println("Error: City is required!");
            city2 = scanner.nextLine().trim();
        }

        System.out.print("Enter province: ");
        String state2 = scanner.nextLine().trim();
        if (state2.isEmpty()) {
            System.out.println("Error: Province is required!");
            state2 = scanner.nextLine().trim();
        }

        System.out.print("Enter postal code (5 digits, e.g., 76700, 75300): ");
        String zip2 = scanner.nextLine().trim();
        if (zip2.isEmpty()) {
            System.out.println("Error: Postal code is required!");
            zip2 = scanner.nextLine().trim();
        }

        System.out.print("Enter country: ");
        String country2 = scanner.nextLine().trim();
        if (country2.isEmpty())
            country2 = "Pakistan";

        Guest guest2 = Guest.create(
                new Name(guestName2),
                new Address(street2, city2, state2, zip2, country2));

        hotelChain.addGuest(guest1);
        hotelChain.addGuest(guest2);

        System.out.println("✓ Created Guest: " + guest1.getName().getValue());
        System.out.println("✓ Created Guest: " + guest2.getName().getValue());
        System.out.println();
    }

    private static void demonstrateReserverPayerCreation() {
        System.out.println("USE CASE 4: Creating ReserverPayers");
        System.out.println("-".repeat(80));

        System.out.print("Enter credit card number (16 digits, e.g., 1234567890123456): ");
        String cardNumber1 = scanner.nextLine().trim();
        if (cardNumber1.isEmpty()) {
            System.out.println("Error: Credit card number is required!");
            cardNumber1 = scanner.nextLine().trim();
        }

        System.out.print("Enter cardholder name (e.g., Ahmad Khan): ");
        String cardName1 = scanner.nextLine().trim();
        if (cardName1.isEmpty()) {
            System.out.println("Error: Cardholder name is required!");
            cardName1 = scanner.nextLine().trim();
        }

        System.out.print("Enter expiry date (MM/YY format - must be 2 digits/2 digits, e.g., 12/25): ");
        String expiry1 = scanner.nextLine().trim();
        if (expiry1.isEmpty()) {
            System.out.println("Error: Expiry date is required!");
            expiry1 = scanner.nextLine().trim();
        }

        // Validate and fix expiry date format
        expiry1 = validateAndFixExpiryDate(expiry1);

        System.out.print("Enter CVV (3-4 digits, e.g., 123 or 1234): ");
        String cvv1 = scanner.nextLine().trim();
        if (cvv1.isEmpty()) {
            System.out.println("Error: CVV is required!");
            cvv1 = scanner.nextLine().trim();
        }

        System.out.print("Enter identity type (Type: PASSPORT, NATIONAL_ID, or DRIVER_LICENSE): ");
        String identityType1 = scanner.nextLine().trim().toUpperCase();
        if (identityType1.isEmpty()) {
            System.out.println("Error: Identity type is required!");
            identityType1 = scanner.nextLine().trim().toUpperCase();
        }

        // Validate identity type
        Identity.IdentityType idType1;
        try {
            idType1 = Identity.IdentityType.valueOf(identityType1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid identity type! Must be one of: PASSPORT, NATIONAL_ID, DRIVER_LICENSE");
            System.out.print("Please enter identity type again (PASSPORT/NATIONAL_ID/DRIVER_LICENSE): ");
            identityType1 = scanner.nextLine().trim().toUpperCase();
            try {
                idType1 = Identity.IdentityType.valueOf(identityType1);
            } catch (IllegalArgumentException e2) {
                System.out.println("Error: Invalid identity type. Using NATIONAL_ID as default.");
                idType1 = Identity.IdentityType.NATIONAL_ID;
            }
        }

        System.out.println();
        if (identityType1.equals("NATIONAL_ID")) {
            System.out.print("Enter CNIC number (Format: XXXXX-XXXXXXX-X, e.g., 41201-1234567-1): ");
        } else if (identityType1.equals("PASSPORT")) {
            System.out.print("Enter passport number (e.g., PA123456, AB1234567): ");
        } else {
            System.out.print("Enter driver license number (e.g., DL-12345, DL123456): ");
        }
        String idNumber1 = scanner.nextLine().trim();
        if (idNumber1.isEmpty()) {
            System.out.println("Error: Identity number is required!");
            idNumber1 = scanner.nextLine().trim();
        }

        // Create credit card with error handling and retry
        CreditCard card1 = createCreditCardWithRetry(cardNumber1, cardName1, expiry1, cvv1);

        // Create identity with error handling and retry
        Identity identity1 = createIdentityWithRetry(idNumber1, idType1);

        // Create reserver payer with error handling
        ReserverPayer payer1;
        try {
            payer1 = hotelChain.createReserverPayer(card1, identity1, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please check your inputs and try again.");
            throw e;
        }

        System.out.print("\nEnter credit card number for second payer (16 digits, e.g., 9876543210987654): ");
        String cardNumber2 = scanner.nextLine().trim();
        if (cardNumber2.isEmpty()) {
            System.out.println("Error: Credit card number is required!");
            cardNumber2 = scanner.nextLine().trim();
        }

        System.out.print("Enter cardholder name (e.g., Fatima Ali): ");
        String cardName2 = scanner.nextLine().trim();
        if (cardName2.isEmpty()) {
            System.out.println("Error: Cardholder name is required!");
            cardName2 = scanner.nextLine().trim();
        }

        System.out.print("Enter expiry date (MM/YY format - must be 2 digits/2 digits, e.g., 06/26): ");
        String expiry2 = scanner.nextLine().trim();
        if (expiry2.isEmpty()) {
            System.out.println("Error: Expiry date is required!");
            expiry2 = scanner.nextLine().trim();
        }

        // Validate and fix expiry date format
        expiry2 = validateAndFixExpiryDate(expiry2);

        System.out.print("Enter CVV (3-4 digits, e.g., 456 or 4567): ");
        String cvv2 = scanner.nextLine().trim();
        if (cvv2.isEmpty()) {
            System.out.println("Error: CVV is required!");
            cvv2 = scanner.nextLine().trim();
        }

        System.out.println();
        System.out.println("--- Identity Information ---");
        System.out.println("Identity Type Options:");
        System.out.println("  - PASSPORT (for passport number)");
        System.out.println("  - NATIONAL_ID (for CNIC number)");
        System.out.println("  - DRIVER_LICENSE (for driver license number)");
        System.out.print("Enter identity type (Type one of the options above): ");
        String identityType2 = scanner.nextLine().trim().toUpperCase();
        if (identityType2.isEmpty()) {
            System.out.println("Error: Identity type is required!");
            identityType2 = scanner.nextLine().trim().toUpperCase();
        }

        // Validate identity type
        Identity.IdentityType idType2;
        try {
            idType2 = Identity.IdentityType.valueOf(identityType2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid identity type! Must be one of: PASSPORT, NATIONAL_ID, DRIVER_LICENSE");
            System.out.print("Please enter identity type again (PASSPORT/NATIONAL_ID/DRIVER_LICENSE): ");
            identityType2 = scanner.nextLine().trim().toUpperCase();
            try {
                idType2 = Identity.IdentityType.valueOf(identityType2);
            } catch (IllegalArgumentException e2) {
                System.out.println("Error: Invalid identity type. Using NATIONAL_ID as default.");
                idType2 = Identity.IdentityType.NATIONAL_ID;
            }
        }

        System.out.println();
        if (identityType2.equals("NATIONAL_ID")) {
            System.out.print("Enter CNIC number (Format: XXXXX-XXXXXXX-X, e.g., 23456-2345678-2): ");
        } else if (identityType2.equals("PASSPORT")) {
            System.out.print("Enter passport number (e.g., PA789012, AB7890123): ");
        } else {
            System.out.print("Enter driver license number (e.g., DL-67890, DL678901): ");
        }
        String idNumber2 = scanner.nextLine().trim();
        if (idNumber2.isEmpty()) {
            System.out.println("Error: Identity number is required!");
            idNumber2 = scanner.nextLine().trim();
        }

        // Create credit card with error handling and retry
        CreditCard card2 = createCreditCardWithRetry(cardNumber2, cardName2, expiry2, cvv2);

        // Create identity with error handling and retry
        Identity identity2 = createIdentityWithRetry(idNumber2, idType2);
        ReserverPayer payer2 = hotelChain.createReserverPayer(card2, identity2, 2);

        System.out.println("✓ Created ReserverPayer #" + payer1.getNumber() + " for " + cardName1);
        System.out.println("✓ Created ReserverPayer #" + payer2.getNumber() + " for " + cardName2);
        System.out.println();
    }

    private static void demonstrateReservationCreation() {
        System.out.println("USE CASE 5: Making Reservations");
        System.out.println("-".repeat(80));

        System.out.print("Enter guest name for reservation: ");
        String guestName = scanner.nextLine().trim();
        if (guestName.isEmpty()) {
            System.out.println("Error: Guest name is required!");
            guestName = scanner.nextLine().trim();
        }

        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine().trim();
        if (hotelName.isEmpty()) {
            System.out.println("Error: Hotel name is required!");
            hotelName = scanner.nextLine().trim();
        }

        System.out.print("Enter room type (SINGLE/DOUBLE/SUITE/DELUXE/PENTHOUSE): ");
        String roomTypeStr = scanner.nextLine().trim().toUpperCase();
        if (roomTypeStr.isEmpty()) {
            System.out.println("Error: Room type is required!");
            roomTypeStr = scanner.nextLine().trim().toUpperCase();
        }
        RoomKind roomKind = parseRoomKindWithRetry(roomTypeStr);

        System.out.print("Enter check-in year (e.g., 2024, 2025): ");
        String yearStr = scanner.nextLine().trim();
        int year = yearStr.isEmpty() ? java.time.LocalDate.now().getYear()
                : parseIntWithRetry(yearStr, "check-in year");
        if (year < 2024 || year > 2030) {
            System.out.println("Warning: Year should be between 2024-2030. Using current year.");
            year = java.time.LocalDate.now().getYear();
        }

        System.out.print("Enter check-in month (1-12, e.g., 7 for July): ");
        String monthStr = scanner.nextLine().trim();
        int month = monthStr.isEmpty() ? java.time.LocalDate.now().getMonthValue()
                : parseIntWithRetry(monthStr, "check-in month");
        if (month < 1 || month > 12) {
            System.out.println("Error: Month must be between 1-12. Please re-enter: ");
            monthStr = scanner.nextLine().trim();
            month = parseIntWithRetry(monthStr, "check-in month");
        }

        System.out.print("Enter check-in day (1-31, e.g., 15): ");
        String dayStr = scanner.nextLine().trim();
        int startDay = dayStr.isEmpty() ? java.time.LocalDate.now().getDayOfMonth()
                : parseIntWithRetry(dayStr, "check-in day");
        if (startDay < 1 || startDay > 31) {
            System.out.println("Error: Day must be between 1-31. Please re-enter: ");
            dayStr = scanner.nextLine().trim();
            startDay = parseIntWithRetry(dayStr, "check-in day");
        }

        System.out.print("Enter check-out day (must be after check-in day, e.g., " + (startDay + 1) + "): ");
        String endDayStr = scanner.nextLine().trim();
        int endDay = endDayStr.isEmpty() ? startDay + 1 : parseIntWithRetry(endDayStr, "check-out day");
        if (endDay <= startDay) {
            System.out.println("Warning: Check-out day must be after check-in day. Using " + (startDay + 1));
            endDay = startDay + 1;
        }

        Date reservationDate = new Date(java.time.LocalDate.now());
        Date startDate = new Date(year, month, startDay);
        Date endDate = new Date(year, month, endDay);

        Reservation reservation1 = hotelChain.makeReservation(
                1, guestName, hotelName, roomKind, startDate, endDate, 1);

        System.out.println("✓ Created Reservation #" + reservation1.getNumber());
        System.out.println("  - Guest: " + guestName);
        System.out.println("  - Hotel: " + hotelName);
        System.out.println("  - Room Type: " + roomKind);
        System.out.println("  - Dates: " + startDate + " to " + endDate);
        System.out.println("  - Duration: " + reservation1.getDurationInDays() + " days");
        System.out.println("  - Rooms: " + reservation1.getRooms().size());
        System.out.println();

        // Second reservation
        System.out.print("Create second reservation? (y/n): ");
        String createSecond = scanner.nextLine().trim().toLowerCase();

        if (!createSecond.equals("n")) {
            System.out.print("Enter second guest name: ");
            String guestName2 = scanner.nextLine().trim();
            if (guestName2.isEmpty()) {
                System.out.println("Error: Guest name is required!");
                guestName2 = scanner.nextLine().trim();
            }

            System.out.print("Enter room type (SINGLE/DOUBLE/SUITE/DELUXE/PENTHOUSE): ");
            String roomTypeStr2 = scanner.nextLine().trim().toUpperCase();
            if (roomTypeStr2.isEmpty()) {
                System.out.println("Error: Room type is required!");
                roomTypeStr2 = scanner.nextLine().trim().toUpperCase();
            }
            RoomKind roomKind2 = parseRoomKindWithRetry(roomTypeStr2);

            Date startDate2 = new Date(year, month, startDay + 10);
            Date endDate2 = new Date(year, month, endDay + 10);

            Reservation reservation2 = hotelChain.makeReservation(
                    2, guestName2, hotelName, roomKind2, startDate2, endDate2, 1);

            System.out.println("✓ Created Reservation #" + reservation2.getNumber());
            System.out.println("  - Guest: " + guestName2);
            System.out.println("  - Hotel: " + hotelName);
            System.out.println("  - Room Type: " + roomKind2);
            System.out.println("  - Dates: " + startDate2 + " to " + endDate2);
            System.out.println();
        }
    }

    private static void demonstrateAvailabilityCheck() {
        System.out.println("USE CASE 6: Checking Room Availability");
        System.out.println("-".repeat(80));

        System.out.print("Enter room type to check (SINGLE/DOUBLE/SUITE/DELUXE/PENTHOUSE): ");
        String roomTypeStr = scanner.nextLine().trim().toUpperCase();
        if (roomTypeStr.isEmpty()) {
            System.out.println("Error: Room type is required!");
            roomTypeStr = scanner.nextLine().trim().toUpperCase();
        }
        RoomKind roomKind = RoomKind.valueOf(roomTypeStr);

        System.out.print("Enter check-in year (e.g., 2024, 2025): ");
        String yearStr = scanner.nextLine().trim();
        int year = yearStr.isEmpty() ? java.time.LocalDate.now().getYear()
                : parseIntWithRetry(yearStr, "check-in year");
        if (year < 2024 || year > 2030) {
            System.out.println("Warning: Year should be between 2024-2030. Using current year.");
            year = java.time.LocalDate.now().getYear();
        }

        System.out.print("Enter check-in month (1-12, e.g., 7 for July): ");
        String monthStr = scanner.nextLine().trim();
        int month = monthStr.isEmpty() ? java.time.LocalDate.now().getMonthValue()
                : parseIntWithRetry(monthStr, "check-in month");
        if (month < 1 || month > 12) {
            System.out.println("Error: Month must be between 1-12. Please re-enter: ");
            monthStr = scanner.nextLine().trim();
            month = parseIntWithRetry(monthStr, "check-in month");
        }

        System.out.print("Enter check-in day (1-31, e.g., 15): ");
        String dayStr = scanner.nextLine().trim();
        int day = dayStr.isEmpty() ? java.time.LocalDate.now().getDayOfMonth()
                : parseIntWithRetry(dayStr, "check-in day");
        if (day < 1 || day > 31) {
            System.out.println("Error: Day must be between 1-31. Please re-enter: ");
            day = Integer.parseInt(scanner.nextLine().trim());
        }

        System.out.print("Enter check-out day (must be after check-in day, e.g., " + (day + 1) + "): ");
        String endDayStr = scanner.nextLine().trim();
        int endDay = endDayStr.isEmpty() ? day + 1 : Integer.parseInt(endDayStr);
        if (endDay <= day) {
            System.out.println("Warning: Check-out day must be after check-in day. Using " + (day + 1));
            endDay = day + 1;
        }

        Date startDate = new Date(year, month, day);
        Date endDate = new Date(year, month, endDay);

        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine().trim();
        if (hotelName.isEmpty()) {
            System.out.println("Error: Hotel name is required!");
            hotelName = scanner.nextLine().trim();
        }
        Hotel hotel = hotelChain.getHotel(hotelName);
        if (hotel == null) {
            System.out.println("Error: Hotel not found! Please enter a valid hotel name.");
            hotelName = scanner.nextLine().trim();
            hotel = hotelChain.getHotel(hotelName);
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel not found: " + hotelName);
            }
        }

        boolean available = hotel.available(roomKind, startDate, endDate, 1);
        System.out.println("✓ Room availability check (" + roomKind + ", " + startDate + " to " + endDate + "): " +
                (available ? "AVAILABLE" : "UNAVAILABLE"));
        System.out.println();
    }

    private static void demonstrateCheckIn() {
        System.out.println("USE CASE 7: Guest Check-in");
        System.out.println("-".repeat(80));

        System.out.print("Enter guest name to check in: ");
        String guestName = scanner.nextLine().trim();
        if (guestName.isEmpty()) {
            System.out.println("Error: Guest name is required!");
            guestName = scanner.nextLine().trim();
        }

        System.out.print("Enter room number (e.g., 101, 201, 301): ");
        String roomNumStr = scanner.nextLine().trim();
        if (roomNumStr.isEmpty()) {
            System.out.println("Error: Room number is required!");
            roomNumStr = scanner.nextLine().trim();
        }
        int roomNumber = parseIntWithRetry(roomNumStr, "room number");
        if (roomNumber < 101 || roomNumber > 999) {
            System.out.println("Warning: Room numbers are typically between 101-999");
        }

        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine().trim();
        if (hotelName.isEmpty()) {
            System.out.println("Error: Hotel name is required!");
            hotelName = scanner.nextLine().trim();
        }

        hotelChain.checkinGuest(guestName, roomNumber, hotelName);

        Guest guest = hotelChain.getGuest(guestName);
        Room room = hotelChain.getHotel(hotelName).getRooms().stream()
                .filter(r -> r.getNumber() == roomNumber)
                .findFirst().orElse(null);

        System.out.println("✓ Checked in: " + guestName);
        if (room != null) {
            System.out.println("  - Room: #" + room.getNumber());
            System.out.println("  - Room Type: " + room.getRoomType().getKind());
        }
        System.out.println("  - Guest checked in status: " + (guest != null && guest.isCheckedIn()));
        System.out.println();
    }

    private static void demonstrateCheckOut() {
        System.out.println("USE CASE 8: Guest Check-out");
        System.out.println("-".repeat(80));

        System.out.print("Enter guest name to check out: ");
        String guestName = scanner.nextLine().trim();
        if (guestName.isEmpty()) {
            System.out.println("Error: Guest name is required!");
            guestName = scanner.nextLine().trim();
        }

        hotelChain.checkOutGuest(guestName);

        Guest guest = hotelChain.getGuest(guestName);
        System.out.println("✓ Checked out: " + guestName);
        System.out.println("  - Guest checked in status: " + (guest != null && !guest.isCheckedIn()));
        System.out.println();
    }

    private static void demonstrateReservationCancellation() {
        System.out.println("USE CASE 9: Canceling Reservation");
        System.out.println("-".repeat(80));

        System.out.println("Current reservations: " + hotelChain.getAllReservations().size());

        if (!hotelChain.getAllReservations().isEmpty()) {
            System.out.print("Enter reservation number to cancel: ");
            String resNumStr = scanner.nextLine().trim();
            if (resNumStr.isEmpty()) {
                System.out.println("Error: Reservation number is required!");
                resNumStr = scanner.nextLine().trim();
            }
            int reservationNumber = parseIntWithRetry(resNumStr, "reservation number");

            System.out.println("Reservations before cancellation: " + hotelChain.getAllReservations().size());
            hotelChain.cancelReservation(reservationNumber);
            System.out.println("✓ Canceled Reservation #" + reservationNumber);
            System.out.println("Reservations after cancellation: " + hotelChain.getAllReservations().size());
        } else {
            System.out.println("No reservations to cancel.");
        }
        System.out.println();
    }

    // Helper methods for safe input parsing with retry

    private static int parseIntWithRetry(String input, String fieldName) {
        while (true) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format for " + fieldName + "!");
                System.out.print("Please enter a valid number: ");
                input = scanner.nextLine().trim();
            }
        }
    }

    private static double parseDoubleWithRetry(String input, String fieldName) {
        while (true) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format for " + fieldName + "!");
                System.out.print("Please enter a valid number: ");
                input = scanner.nextLine().trim();
            }
        }
    }

    private static CreditCard createCreditCardWithRetry(String cardNumber, String cardName, String expiry, String cvv) {
        while (true) {
            try {
                return new CreditCard(cardNumber, cardName, expiry, cvv);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                if (e.getMessage().contains("Expiry date")) {
                    System.out.print("Please re-enter expiry date (MM/YY format, e.g., 12/25): ");
                    expiry = scanner.nextLine().trim();
                    expiry = validateAndFixExpiryDate(expiry);
                } else if (e.getMessage().contains("Card number")) {
                    System.out.print("Please re-enter card number (16 digits): ");
                    cardNumber = scanner.nextLine().trim();
                } else if (e.getMessage().contains("Card holder name")) {
                    System.out.print("Please re-enter cardholder name: ");
                    cardName = scanner.nextLine().trim();
                } else if (e.getMessage().contains("CVV")) {
                    System.out.print("Please re-enter CVV (3-4 digits): ");
                    cvv = scanner.nextLine().trim();
                } else {
                    throw new RuntimeException("Unexpected error creating credit card: " + e.getMessage());
                }
            }
        }
    }

    private static Identity createIdentityWithRetry(String idNumber, Identity.IdentityType idType) {
        while (true) {
            try {
                return new Identity(idNumber, idType);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.print("Please re-enter identity number: ");
                idNumber = scanner.nextLine().trim();
            }
        }
    }

    private static RoomKind parseRoomKindWithRetry(String roomTypeStr) {
        while (true) {
            try {
                return RoomKind.valueOf(roomTypeStr);
            } catch (IllegalArgumentException e) {
                System.out
                        .println("Error: Invalid room type! Must be one of: SINGLE, DOUBLE, SUITE, DELUXE, PENTHOUSE");
                System.out.print("Please enter room type again (SINGLE/DOUBLE/SUITE/DELUXE/PENTHOUSE): ");
                roomTypeStr = scanner.nextLine().trim().toUpperCase();
            }
        }
    }

    private static String validateAndFixExpiryDate(String expiry) {
        if (expiry == null || expiry.trim().isEmpty()) {
            return expiry;
        }

        expiry = expiry.trim();

        // Try to fix common mistakes
        // If format is M/YY (single digit month), pad with 0
        if (expiry.matches("\\d{1}/\\d{2}")) {
            expiry = "0" + expiry; // Add leading zero to month
            System.out.println("Fixed format: " + expiry);
        }
        // If format is MM/Y (single digit year), pad with 0
        else if (expiry.matches("\\d{2}/\\d{1}")) {
            String[] parts = expiry.split("/");
            expiry = parts[0] + "/0" + parts[1]; // Add leading zero to year
            System.out.println("Fixed format: " + expiry);
        }
        // If format is M/Y (both single digit), pad both
        else if (expiry.matches("\\d{1}/\\d{1}")) {
            String[] parts = expiry.split("/");
            expiry = "0" + parts[0] + "/0" + parts[1];
            System.out.println("Fixed format: " + expiry);
        }
        // If no slash, try to add one (assuming last 2 digits are year)
        else if (expiry.matches("\\d{3,4}")) {
            if (expiry.length() == 3) {
                expiry = "0" + expiry.charAt(0) + "/" + expiry.substring(1);
            } else {
                expiry = expiry.substring(0, 2) + "/" + expiry.substring(2);
            }
            System.out.println("Fixed format: " + expiry);
        }

        // Validate final format
        if (!expiry.matches("\\d{2}/\\d{2}")) {
            throw new IllegalArgumentException(
                    "Expiry date must be in MM/YY format (2 digits/2 digits). Example: 12/25");
        }

        return expiry;
    }

    private static void displaySummary() {
        System.out.println("=".repeat(80));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(80));

        int hotelCount = 2; // We know we created 2 hotels
        int guestCount = 2; // We know we created 2 guests
        int reservationCount = hotelChain.getAllReservations().size();

        System.out.println("Hotels: " + hotelCount + " hotels in chain");
        System.out.println("Guests: " + guestCount + " guests registered");
        System.out.println("Active Reservations: " + reservationCount);

        // Get hotel from chain
        System.out.print("Enter hotel name for summary: ");
        String summaryHotelName = scanner.nextLine().trim();
        Hotel hotel = hotelChain.getHotel(summaryHotelName);
        if (hotel == null && !hotelChain.getAllReservations().isEmpty()) {
            hotel = hotelChain.getAllReservations().get(0).getRooms().get(0).getHotel();
        }
        if (hotel != null) {
            System.out.println("Total Rooms in " + hotel.getName().getValue() + ": " + hotel.getRooms().size());
        }

        System.out.println();
        System.out.println("All use cases successfully demonstrated!");
        System.out.println("=".repeat(80));
    }
}

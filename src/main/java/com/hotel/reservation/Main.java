package com.hotel.reservation;

import com.hotel.reservation.domain.*;
import com.hotel.reservation.model.*;

/**
 * Main entry point demonstrating all use cases of the hotel reservation system.
 * This class demonstrates the execution of all core functionality as per the UML design.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("HOTEL RESERVATION SYSTEM - USE CASE DEMONSTRATION");
        System.out.println("=".repeat(80));
        System.out.println();

        // Initialize Hotel Chain
        HotelChain hotelChain = new HotelChain();
        System.out.println("✓ Hotel Chain initialized");
        System.out.println();

        // Use Case 1: Create Hotels
        System.out.println("USE CASE 1: Creating Hotels");
        System.out.println("-".repeat(80));
        Hotel grandHotel = new Hotel(new Name("Grand Hotel"));
        Hotel paradiseResort = new Hotel(new Name("Paradise Resort"));
        hotelChain.addHotel(grandHotel);
        hotelChain.addHotel(paradiseResort);
        System.out.println("✓ Created Hotel: " + grandHotel.getName().getValue());
        System.out.println("✓ Created Hotel: " + paradiseResort.getName().getValue());
        System.out.println();

        // Use Case 2: Create Room Types and Rooms
        System.out.println("USE CASE 2: Setting up Room Types and Rooms");
        System.out.println("-".repeat(80));
        
        // Grand Hotel rooms
        RoomType singleRoomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"), grandHotel);
        RoomType doubleRoomType = new RoomType(RoomKind.DOUBLE, new Money(150.0, "USD"), grandHotel);
        RoomType suiteRoomType = new RoomType(RoomKind.SUITE, new Money(300.0, "USD"), grandHotel);
        
        grandHotel.addRoomType(singleRoomType);
        grandHotel.addRoomType(doubleRoomType);
        grandHotel.addRoomType(suiteRoomType);
        
        // Create rooms for Grand Hotel
        for (int i = 101; i <= 103; i++) {
            Room room = new Room(i, grandHotel, singleRoomType);
            grandHotel.addRoom(room);
            singleRoomType.addRoom(room);
        }
        
        for (int i = 201; i <= 202; i++) {
            Room room = new Room(i, grandHotel, doubleRoomType);
            grandHotel.addRoom(room);
            doubleRoomType.addRoom(room);
        }
        
        Room suiteRoom = new Room(301, grandHotel, suiteRoomType);
        grandHotel.addRoom(suiteRoom);
        suiteRoomType.addRoom(suiteRoom);
        
        System.out.println("✓ Created " + grandHotel.getRoomTypes().size() + " room types in Grand Hotel");
        System.out.println("✓ Created " + grandHotel.getRooms().size() + " rooms in Grand Hotel");
        System.out.println();

        // Use Case 3: Create Guests
        System.out.println("USE CASE 3: Creating Guests");
        System.out.println("-".repeat(80));
        Guest guest1 = Guest.create(
            new Name("John Doe"),
            new Address("123 Main Street", "New York", "NY", "10001", "USA")
        );
        Guest guest2 = Guest.create(
            new Name("Jane Smith"),
            new Address("456 Oak Avenue", "Los Angeles", "CA", "90001", "USA")
        );
        hotelChain.addGuest(guest1);
        hotelChain.addGuest(guest2);
        System.out.println("✓ Created Guest: " + guest1.getName().getValue());
        System.out.println("✓ Created Guest: " + guest2.getName().getValue());
        System.out.println();

        // Use Case 4: Create ReserverPayers
        System.out.println("USE CASE 4: Creating ReserverPayers");
        System.out.println("-".repeat(80));
        CreditCard card1 = new CreditCard("1234567890123456", "John Doe", "12/25", "123");
        Identity identity1 = new Identity("ID123456", Identity.IdentityType.PASSPORT);
        ReserverPayer payer1 = hotelChain.createReserverPayer(card1, identity1, 1);
        
        CreditCard card2 = new CreditCard("9876543210987654", "Jane Smith", "06/26", "456");
        Identity identity2 = new Identity("ID789012", Identity.IdentityType.NATIONAL_ID);
        ReserverPayer payer2 = hotelChain.createReserverPayer(card2, identity2, 2);
        
        System.out.println("✓ Created ReserverPayer #" + payer1.getNumber() + " for " + guest1.getName().getValue());
        System.out.println("✓ Created ReserverPayer #" + payer2.getNumber() + " for " + guest2.getName().getValue());
        System.out.println();

        // Use Case 5: Make Reservations
        System.out.println("USE CASE 5: Making Reservations");
        System.out.println("-".repeat(80));
        Date reservationDate1 = new Date(java.time.LocalDate.now());
        Date startDate1 = new Date(2024, 7, 1);
        Date endDate1 = new Date(2024, 7, 5);
        
        Reservation reservation1 = hotelChain.makeReservation(
            1, "John Doe", "Grand Hotel", RoomKind.SINGLE, startDate1, endDate1, 1
        );
        System.out.println("✓ Created Reservation #" + reservation1.getNumber());
        System.out.println("  - Guest: " + guest1.getName().getValue());
        System.out.println("  - Hotel: Grand Hotel");
        System.out.println("  - Room Type: SINGLE");
        System.out.println("  - Dates: " + startDate1 + " to " + endDate1);
        System.out.println("  - Duration: " + reservation1.getDurationInDays() + " days");
        System.out.println("  - Rooms: " + reservation1.getRooms().size());
        System.out.println();

        Date startDate2 = new Date(2024, 7, 10);
        Date endDate2 = new Date(2024, 7, 15);
        Reservation reservation2 = hotelChain.makeReservation(
            2, "Jane Smith", "Grand Hotel", RoomKind.DOUBLE, startDate2, endDate2, 1
        );
        System.out.println("✓ Created Reservation #" + reservation2.getNumber());
        System.out.println("  - Guest: " + guest2.getName().getValue());
        System.out.println("  - Hotel: Grand Hotel");
        System.out.println("  - Room Type: DOUBLE");
        System.out.println("  - Dates: " + startDate2 + " to " + endDate2);
        System.out.println("  - Rooms: " + reservation2.getRooms().size());
        System.out.println();

        // Use Case 6: Check Room Availability
        System.out.println("USE CASE 6: Checking Room Availability");
        System.out.println("-".repeat(80));
        boolean available = grandHotel.available(RoomKind.SINGLE, startDate1, endDate1, 1);
        System.out.println("✓ Room availability check (SINGLE, " + startDate1 + " to " + endDate1 + "): " + 
                          (available ? "UNAVAILABLE (reserved)" : "AVAILABLE"));
        
        Date futureStart = new Date(2024, 8, 1);
        Date futureEnd = new Date(2024, 8, 5);
        boolean available2 = grandHotel.available(RoomKind.SINGLE, futureStart, futureEnd, 1);
        System.out.println("✓ Room availability check (SINGLE, " + futureStart + " to " + futureEnd + "): " + 
                          (available2 ? "AVAILABLE" : "UNAVAILABLE"));
        System.out.println();

        // Use Case 7: Check-in Guest
        System.out.println("USE CASE 7: Guest Check-in");
        System.out.println("-".repeat(80));
        Room reservedRoom = reservation1.getRooms().get(0);
        hotelChain.checkinGuest("John Doe", reservedRoom.getNumber(), "Grand Hotel");
        System.out.println("✓ Checked in: " + guest1.getName().getValue());
        System.out.println("  - Room: #" + reservedRoom.getNumber());
        System.out.println("  - Room Type: " + reservedRoom.getRoomType().getKind());
        System.out.println("  - Guest checked in status: " + guest1.isCheckedIn());
        System.out.println("  - Room occupied status: " + reservedRoom.isOccupied());
        System.out.println();

        // Use Case 8: Guest Check-out
        System.out.println("USE CASE 8: Guest Check-out");
        System.out.println("-".repeat(80));
        hotelChain.checkOutGuest("John Doe");
        System.out.println("✓ Checked out: " + guest1.getName().getValue());
        System.out.println("  - Guest checked in status: " + guest1.isCheckedIn());
        System.out.println("  - Room occupied status: " + reservedRoom.isOccupied());
        System.out.println();

        // Use Case 9: Cancel Reservation
        System.out.println("USE CASE 9: Canceling Reservation");
        System.out.println("-".repeat(80));
        System.out.println("Reservations before cancellation: " + hotelChain.getAllReservations().size());
        hotelChain.cancelReservation(reservation2.getNumber());
        System.out.println("✓ Canceled Reservation #" + reservation2.getNumber());
        System.out.println("Reservations after cancellation: " + hotelChain.getAllReservations().size());
        System.out.println();

        // Summary
        System.out.println("=".repeat(80));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(80));
        System.out.println("Hotels: 2 hotels in chain (Grand Hotel, Paradise Resort)");
        System.out.println("Guests: 2 guests registered (John Doe, Jane Smith)");
        System.out.println("Active Reservations: " + hotelChain.getAllReservations().size());
        System.out.println("Total Rooms in Grand Hotel: " + grandHotel.getRooms().size());
        System.out.println();
        System.out.println("All use cases successfully demonstrated!");
        System.out.println("=".repeat(80));
    }
}

# Hotel Reservation System

## Project Overview

This project is a complete implementation of a hotel reservation system based on a UML class diagram. It demonstrates object-oriented design principles, clean code practices, defensive programming techniques, and comprehensive unit testing.

The system allows users to:
- Create and manage hotels with different room types
- Register guests and reserver/payers
- Make, view, and cancel reservations
- Check in and check out guests
- Check room availability

## Architecture

The project follows a clean, modular architecture with:

- **Domain Classes**: Core business logic entities (HotelChain, Hotel, Guest, Reservation, Room, RoomType, ReserverPayer, HowMany)
- **Value Objects**: Immutable objects representing domain concepts (CreditCard, Identity, Name, Address, Date, Money, RoomKind)
- **Test Classes**: Comprehensive unit tests using JUnit 5 with parameterized tests

## Technology Stack

- **Java 17**
- **Maven** (Build and dependency management)
- **JUnit 5** (Testing framework)
- **Mockito** (Mocking framework for tests)

## Project Structure

```
hotel-reservation-system/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── hotel/
    │               └── reservation/
    │                   ├── Main.java
    │                   ├── domain/
    │                   │   ├── Guest.java
    │                   │   ├── Hotel.java
    │                   │   ├── HotelChain.java
    │                   │   ├── HowMany.java
    │                   │   ├── Reservation.java
    │                   │   ├── ReserverPayer.java
    │                   │   ├── Room.java
    │                   │   └── RoomType.java
    │                   └── model/
    │                       ├── Address.java
    │                       ├── CreditCard.java
    │                       ├── Date.java
    │                       ├── Identity.java
    │                       ├── Money.java
    │                       ├── Name.java
    │                       └── RoomKind.java
    └── test/
        └── java/
            └── com/
                └── hotel/
                    └── reservation/
                        ├── domain/
                        │   ├── GuestTest.java
                        │   ├── HotelChainTest.java
                        │   ├── HotelTest.java
                        │   ├── HowManyTest.java
                        │   ├── ReservationTest.java
                        │   ├── ReserverPayerTest.java
                        │   ├── RoomTest.java
                        │   └── RoomTypeTest.java
                        └── model/
                            ├── CreditCardTest.java
                            ├── DateTest.java
                            └── MoneyTest.java
```

## Prerequisites

- **Java Development Kit (JDK) 17 or higher**
- **Maven 3.6+**
- **Git** (for version control)

## Building the Project

### Using Maven Command Line

1. **Clone the repository** (or navigate to the project directory):
   ```bash
   cd hotel-reservation-system
   ```

2. **Compile the project**:
   ```bash
   mvn clean compile
   ```

3. **Package the project**:
   ```bash
   mvn clean package
   ```

   This will create a JAR file in the `target/` directory.

## Running the Application

### Execute the Main Program

Run the main class to see the system in action:

```bash
mvn exec:java -Dexec.mainClass="com.hotel.reservation.Main"
```

Or compile and run directly:

```bash
# Compile
mvn compile

# Run
java -cp target/classes com.hotel.reservation.Main
```

The main program demonstrates all key use cases:
- Creating hotels and room types
- Registering guests and reserver/payers
- Making reservations
- Checking room availability
- Guest check-in and check-out
- Canceling reservations

## Executing Tests

### Run All Tests

```bash
mvn test
```

### Run Tests with Detailed Output

```bash
mvn test -Dtest=*Test
```

### Run Specific Test Class

```bash
mvn test -Dtest=GuestTest
mvn test -Dtest=HotelChainTest
mvn test -Dtest=ReservationTest
```

### Generate Test Reports

```bash
mvn surefire-report:report
```

Test reports will be generated in `target/site/surefire-report.html`.

## Testing Strategy

The project includes comprehensive unit tests following:

- **AAA Pattern**: All tests follow Arrange-Act-Assert structure
- **Parameterized Tests**: Used for testing multiple input scenarios (e.g., invalid inputs)
- **Boundary Testing**: Tests include normal cases, boundary conditions, and invalid inputs
- **Defensive Programming**: All tests verify proper validation and error handling

### Test Coverage

Each domain class has a corresponding test class:
- `GuestTest` - Tests guest creation and validation
- `HotelTest` - Tests hotel operations and room availability
- `HotelChainTest` - Tests reservation lifecycle (create, cancel, check-in, check-out)
- `ReservationTest` - Tests reservation creation and date validation
- `RoomTest` - Tests room state management
- `ReserverPayerTest` - Tests payer creation and validation
- `RoomTypeTest` - Tests room type associations
- `HowManyTest` - Tests quantity validation

### Model/Value Object Tests

- `CreditCardTest` - Tests credit card validation
- `DateTest` - Tests date operations and comparisons
- `MoneyTest` - Tests money calculations and currency handling

## Key Features

### Defensive Programming

- All constructors validate input parameters
- Null checks prevent NullPointerException
- Range validation for numeric inputs
- Format validation for strings (e.g., credit card numbers, dates)
- Illegal state prevention (e.g., cannot check in if already checked in)

### Clean Code Principles

- Meaningful class, method, and variable names
- Small, focused methods with single responsibility
- Proper encapsulation (private fields, public getters)
- Immutable value objects where appropriate
- Clear separation of concerns (domain vs. model)

### UML Compliance

The implementation strictly follows the provided UML diagram:
- All classes, attributes, and operations are implemented
- Associations and multiplicities are respected
- Access modifiers match the UML specification (+ for public, - for private)

## Use Cases Demonstrated

1. **Hotel Management**: Creating hotels with room types and rooms
2. **Guest Registration**: Registering guests with personal information
3. **Reservation Creation**: Making reservations with dates and room types
4. **Availability Checking**: Checking room availability for specific dates
5. **Check-in Process**: Assigning guests to reserved rooms
6. **Check-out Process**: Releasing rooms when guests leave
7. **Reservation Cancellation**: Canceling existing reservations

## Development Notes

- The project uses Maven for dependency management
- All tests follow JUnit 5 conventions
- The codebase is organized into logical packages (domain, model)
- Value objects ensure data integrity and validation
- Exception handling provides clear error messages

## Contributing

This is an academic project demonstrating UML-to-code translation with emphasis on:
- Clean code principles
- Defensive programming
- Comprehensive testing
- Proper object-oriented design

## License

This project is created for academic purposes.

## Author

Student Project - Hotel Reservation System Implementation

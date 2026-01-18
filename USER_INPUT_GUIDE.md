# User Input Guide - Interactive Hotel Reservation System

## ✅ System Updated to User Input Based!

The system is now **interactive** and accepts user input for all operations while still demonstrating all 9 use cases from the UML.

---

## 🚀 How to Run

### Option 1: Double-Click
1. Double-click `RUN_IT.bat`
2. The program will start and ask for input

### Option 2: Command Line
```powershell
cd "d:\Adil CCP 2"
javac -d target/classes -sourcepath src/main/java src/main/java/com/hotel/reservation/model/*.java src/main/java/com/hotel/reservation/domain/*.java src/main/java/com/hotel/reservation/Main.java
java -cp target/classes com.hotel.reservation.Main
```

---

## 📝 How It Works

The program will prompt you for each piece of information. You can:

1. **Enter your own data** - Type values when prompted
2. **Use defaults** - Press Enter to use suggested default values

---

## 🎯 Example Interaction Flow

### Use Case 1: Creating Hotels
```
Enter first hotel name: [You type: "Grand Hotel" or press Enter]
Enter second hotel name (or press Enter for 'Paradise Resort'): [Press Enter]
```

### Use Case 2: Room Setup
```
Enter room price for SINGLE room (or press Enter for 100): [Type: 120 or Enter]
How many SINGLE rooms to create? (or press Enter for 3): [Type: 5 or Enter]
```

### Use Case 3: Creating Guests
```
Enter first guest name: [Type: "John Doe" or Enter]
Enter John Doe's street address: [Type: "123 Main St" or Enter]
Enter city: [Type: "New York" or Enter]
...
```

### Use Case 4: ReserverPayers
```
Enter credit card number for first payer: [Type: 16-digit number or Enter]
Enter cardholder name: [Type: "John Doe" or Enter]
...
```

### Use Case 5: Making Reservations
```
Enter guest name for reservation: [Type guest name or Enter]
Enter hotel name: [Type hotel name or Enter]
Enter room type (SINGLE/DOUBLE/SUITE/DELUXE/PENTHOUSE): [Type or Enter for SINGLE]
Enter check-in year: [Type: 2024 or Enter]
...
```

### Use Case 6-9: Continue Following Prompts
The program will continue asking for input for each operation.

---

## ✨ Key Features

1. **Flexible Input**: Enter custom data or use defaults
2. **Clear Prompts**: Each step explains what to enter
3. **Error Handling**: Invalid input shows errors
4. **All Use Cases**: Still demonstrates all 9 UML use cases
5. **Interactive**: You control the data used

---

## 💡 Tips

- **Quick Testing**: Just press Enter repeatedly to use all defaults
- **Custom Data**: Enter your own values to see different results
- **Room Types**: Must be one of: SINGLE, DOUBLE, SUITE, DELUXE, PENTHOUSE
- **Identity Types**: Must be one of: PASSPORT, NATIONAL_ID, DRIVER_LICENSE
- **Dates**: Enter valid dates (year, month 1-12, day 1-31)

---

## 🎯 What This Demonstrates

Even with user input, the system still proves:
- ✅ All UML classes working
- ✅ All UML operations functioning
- ✅ Object creation and interactions
- ✅ All 9 use cases executed
- ✅ System handles real input

---

## 🆚 Comparison: Old vs New

| Feature | Old (Preset) | New (User Input) |
|---------|--------------|------------------|
| Data Source | Hardcoded | User enters |
| Flexibility | Fixed values | Customizable |
| Testing | Same every time | Different each run |
| Use Cases | ✅ All 9 | ✅ All 9 |
| Requirements | ✅ Meets | ✅ Meets |

---

**The system is now interactive while maintaining all demonstration capabilities!** 🎉

# How to Run the Program Yourself

## Quick Method: Double-Click the Batch File

1. **Double-click** `RUN_IT.bat` in your project folder
2. The program will compile and run automatically
3. You'll see the output in a window

---

## Manual Method: Using PowerShell

Open PowerShell and run these commands:

### Step 1: Navigate to Your Project
```powershell
cd "d:\Adil CCP 2"
```

### Step 2: Compile the Project
```powershell
javac -d target/classes -sourcepath src/main/java src/main/java/com/hotel/reservation/model/*.java src/main/java/com/hotel/reservation/domain/*.java src/main/java/com/hotel/reservation/Main.java
```

### Step 3: Run the Program
```powershell
java -cp target/classes com.hotel.reservation.Main
```

---

## What You'll See

The program will display:
- All 9 use cases being executed
- Hotel creation
- Guest registration
- Reservation making
- Check-in/check-out processes
- Room availability checking
- And much more!

---

## Troubleshooting

**If you get "javac is not recognized":**
- Make sure Java is installed
- Check Java: `java -version`

**If compilation fails:**
- Make sure you're in the correct folder: `d:\Adil CCP 2`
- Check that all source files are present

**If runtime fails:**
- Make sure compilation succeeded first
- Check that `target/classes` folder exists

---

## Quick Run Commands (Copy-Paste Ready)

Copy and paste this entire block into PowerShell:

```powershell
cd "d:\Adil CCP 2"
javac -d target/classes -sourcepath src/main/java src/main/java/com/hotel/reservation/model/*.java src/main/java/com/hotel/reservation/domain/*.java src/main/java/com/hotel/reservation/Main.java
java -cp target/classes com.hotel.reservation.Main
```

Enjoy watching your system in action! 🚀

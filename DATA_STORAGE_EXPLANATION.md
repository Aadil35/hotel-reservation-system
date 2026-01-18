# Data Storage Explanation

## 📋 What This Project Is

This is an **academic UML implementation project**. It's purpose is to:
- ✅ Demonstrate that the UML design works correctly
- ✅ Show all UML classes and operations functioning
- ✅ Prove object creation and interactions work
- ✅ Provide screenshots for your submission

**This is NOT a production/hotel system.** It's a **demonstration/proof of concept**.

---

## 💾 What Happens to Your Data?

### Current System: In-Memory Only

When you run the program:

1. **During Execution**: 
   - All data (hotels, guests, reservations) is stored **in computer memory (RAM)**
   - Objects are created and interact with each other
   - You see output showing everything working

2. **When Program Ends**:
   - ❌ **Data is LOST** (deleted from memory)
   - ❌ **Nothing is saved** to files or database
   - ❌ **No persistence** - data doesn't survive program restart

### Why? This is Normal for Academic Projects!

- **Purpose**: Demonstrate design works, not build a real system
- **Focus**: UML implementation, not data storage
- **Requirement**: Show screenshots proving it works
- **Typical**: Academic UML projects don't need databases

---

## 📸 What You Need for Submission

The assignment asks for **screenshots showing**:

1. ✅ **Main Program Output** - Shows your data being used during execution
2. ✅ **Test Results** - Shows code is correct
3. ✅ **GitHub Repository** - Shows your code
4. ✅ **Commit History** - Shows your work progress

**You DON'T need:**
- ❌ Database
- ❌ File storage
- ❌ Data persistence
- ❌ Long-term data storage

The screenshots show the system **working during execution** - that's what matters!

---

## 🎯 What Happens When You Run It?

### Step-by-Step:

1. **You run the program** → `java -cp target/classes com.hotel.reservation.Main`

2. **Program prompts you** → You enter hotel names, guest info, etc.

3. **Objects are created in memory**:
   ```
   Hotel hotel1 = new Hotel(new Name("Grand Hotel"));
   Guest guest1 = Guest.create(new Name("Ahmad"), ...);
   Reservation res1 = hotelChain.makeReservation(...);
   ```

4. **Objects interact**:
   - `hotelChain.makeReservation()` creates reservation
   - `hotelChain.checkinGuest()` assigns guest to room
   - `hotel.available()` checks room availability

5. **Output is displayed** → You see confirmation messages:
   ```
   ✓ Created Hotel: Grand Hotel
   ✓ Created Guest: Ahmad
   ✓ Created Reservation #123456
   ```

6. **Program shows summary** → All use cases demonstrated

7. **Program ends** → Data is cleared from memory (this is normal!)

---

## 🖼️ For Your Submission Screenshot

When you take the screenshot, you'll show:

```
================================================================================
HOTEL RESERVATION SYSTEM - INTERACTIVE DEMONSTRATION
================================================================================

✓ Hotel Chain initialized

USE CASE 1: Creating Hotels
--------------------------------------------------------------------------------
Enter first hotel name: Grand Hotel
Enter second hotel name: Paradise Resort
✓ Created Hotel: Grand Hotel
✓ Created Hotel: Paradise Resort

USE CASE 2: Setting up Room Types and Rooms
--------------------------------------------------------------------------------
...
```

**This output PROVES:**
- ✅ Objects are created
- ✅ Operations work
- ✅ Use cases execute
- ✅ UML design is correct

**That's what the assignment wants!**

---

## 🤔 Could We Add Data Storage?

Yes, but **it's NOT required** for this assignment:

### If You Wanted Persistence (Optional):

1. **Save to File** (JSON/XML):
   - Save hotels, guests, reservations to a file
   - Load from file when program starts
   - More complex, not needed for UML project

2. **Database** (MySQL/PostgreSQL):
   - Store data in a database
   - Much more complex
   - **Way beyond** UML implementation requirements

3. **Current Approach** (What You Have):
   - ✅ Simple
   - ✅ Focuses on UML demonstration
   - ✅ Perfect for academic project
   - ✅ **Exactly what's needed**

---

## ✅ Summary

### What You Have:
- ✅ **In-memory data** during program execution
- ✅ **Demonstration** of all UML use cases
- ✅ **Output** showing system works
- ✅ **Perfect for screenshots**

### What You DON'T Need:
- ❌ Database storage
- ❌ File persistence
- ❌ Long-term data saving

### What Assignment Wants:
- ✅ Screenshot of program **executing** and showing use cases
- ✅ Proof that UML design **works**
- ✅ Demonstration that objects **interact correctly**

---

## 🎓 This is Standard Academic Practice

**Every UML implementation project** works this way:
- Data in memory only
- Demonstration during execution
- Screenshots as proof
- No persistence required

**Your system is correct as-is!** Just run it, enter data, take screenshots of the output. That's what the assignment needs.

---

**TL;DR**: Data exists in memory while the program runs, gets cleared when it ends. This is normal and expected. You just need screenshots showing it working for your submission!

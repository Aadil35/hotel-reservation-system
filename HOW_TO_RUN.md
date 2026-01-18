# How to Run and Test Your Hotel Reservation System

## Prerequisites

You need **Java** and **Maven** installed to run the project. Here are your options:

---

## Option 1: Install Maven (Recommended)

### Step 1: Check if Java is Installed

Open PowerShell and run:
```powershell
java -version
```

If you see a version number, Java is installed. If not, install Java first:
- Download: https://www.oracle.com/java/technologies/downloads/
- Or use: https://adoptium.net/ (free, recommended)

### Step 2: Install Maven

1. **Download Maven**: 
   - Go to: https://maven.apache.org/download.cgi
   - Download `apache-maven-3.9.5-bin.zip` (or latest version)

2. **Extract and Install**:
   - Extract the zip file to `C:\Program Files\Apache\maven` (or any folder)
   - Copy the folder path (e.g., `C:\Program Files\Apache\maven\bin`)

3. **Add to PATH**:
   - Press `Win + R`, type `sysdm.cpl`, press Enter
   - Go to **Advanced** tab → **Environment Variables**
   - Under **System Variables**, find **Path** → Click **Edit**
   - Click **New** → Paste your Maven bin path (e.g., `C:\Program Files\Apache\maven\bin`)
   - Click **OK** on all dialogs
   - **Restart PowerShell** for changes to take effect

4. **Verify Installation**:
   ```powershell
   mvn --version
   ```

### Step 3: Run the Project

Once Maven is installed, open PowerShell in your project folder and run:

**Compile the project:**
```powershell
cd "d:\Adil CCP 2"
mvn clean compile
```

**Run tests:**
```powershell
mvn test
```

**Run the main program:**
```powershell
mvn exec:java -Dexec.mainClass="com.hotel.reservation.Main"
```

---

## Option 2: Use an IDE (Easier - No Command Line)

If you don't want to use command line, use an IDE:

### IntelliJ IDEA (Free Community Edition)

1. **Download**: https://www.jetbrains.com/idea/download/
2. **Install** and open IntelliJ IDEA
3. **Open Project**:
   - File → Open
   - Select folder: `d:\Adil CCP 2`
   - Click OK
4. **Wait for Maven Import**: IntelliJ will automatically download dependencies
5. **Run Tests**:
   - Right-click on `src/test` folder → Run 'All Tests'
   - Or open any `*Test.java` file and click the green play button
6. **Run Main Program**:
   - Open `src/main/java/com/hotel/reservation/Main.java`
   - Click the green play button next to `public static void main`
   - Or right-click in the file → Run 'Main.main()'

### Eclipse IDE (Free)

1. **Download**: https://www.eclipse.org/downloads/
2. **Install Eclipse IDE for Java Developers**
3. **Import Project**:
   - File → Import → Maven → Existing Maven Projects
   - Browse to `d:\Adil CCP 2`
   - Click Finish
4. **Run Tests**: Right-click project → Run As → JUnit Test
5. **Run Main**: Right-click `Main.java` → Run As → Java Application

### Visual Studio Code (Free)

1. **Install VS Code**: https://code.visualstudio.com/
2. **Install Extensions**:
   - Java Extension Pack (by Microsoft)
   - Maven for Java (by Microsoft)
3. **Open Folder**: File → Open Folder → Select `d:\Adil CCP 2`
4. **Run**: Click "Run" button in Main.java or use Run → Run Without Debugging

---

## Option 3: Quick Verification (Without Running)

If you just want to verify your code is correct without installing tools:

### View Files on GitHub

1. Go to your GitHub repository: `https://github.com/YOUR_USERNAME/hotel-reservation-system`
2. Click through the folders to verify all files are there:
   - `src/main/java/com/hotel/reservation/` - Should have all domain and model classes
   - `src/test/java/com/hotel/reservation/` - Should have all test classes
   - `Main.java` - Should exist
   - `README.md` - Should exist
   - `pom.xml` - Should exist

### Verify Code Structure

All these files should be visible in your repository:
- ✅ `pom.xml`
- ✅ `README.md`
- ✅ `src/main/java/com/hotel/reservation/Main.java`
- ✅ `src/main/java/com/hotel/reservation/domain/*.java` (8 files)
- ✅ `src/main/java/com/hotel/reservation/model/*.java` (7 files)
- ✅ `src/test/java/com/hotel/reservation/domain/*Test.java` (8 files)
- ✅ `src/test/java/com/hotel/reservation/model/*Test.java` (3 files)

---

## Screenshots for Submission

Once you can run the project, take these screenshots:

### 1. Test Results Screenshot
```powershell
mvn test
```
Take a screenshot showing: "Tests run: X, Failures: 0, Errors: 0"

### 2. Main Program Output Screenshot
```powershell
mvn exec:java -Dexec.mainClass="com.hotel.reservation.Main"
```
Take a screenshot of the console output showing all use cases

### 3. GitHub Repository Screenshot
- Go to your GitHub repo
- Show all files in the repository

### 4. Commit History Screenshot
- Go to your GitHub repo
- Click "Commits" tab
- Show your commit history

---

## Quick Start (If Maven is Already Working)

If you have Maven installed, just run these commands in PowerShell:

```powershell
# Navigate to project
cd "d:\Adil CCP 2"

# Compile
mvn clean compile

# Run tests (this is important for your submission!)
mvn test

# Run main program
mvn exec:java -Dexec.mainClass="com.hotel.reservation.Main"
```

---

## Troubleshooting

**Problem**: "mvn is not recognized"
- **Solution**: Maven is not installed or not in PATH. Follow Option 1 above.

**Problem**: "java is not recognized"
- **Solution**: Java is not installed. Install Java first, then Maven.

**Problem**: Tests fail
- **Solution**: Make sure all dependencies are downloaded: `mvn clean install`

**Problem**: IDE shows errors
- **Solution**: Let the IDE download Maven dependencies (it usually does automatically)

---

## Recommendation

**For beginners**: Use **IntelliJ IDEA Community Edition** (Option 2). It's the easiest way to run Java projects with Maven. It handles everything automatically.

**For command line users**: Install Maven (Option 1) and use the commands provided.

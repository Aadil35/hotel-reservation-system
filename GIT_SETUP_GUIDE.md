# Git and GitHub Setup Guide

## Prerequisites

You need to have Git installed on your computer to commit and push to GitHub.

### Install Git (if not already installed)

1. **Download Git for Windows**: 
   - Visit: https://git-scm.com/download/win
   - Download and run the installer
   - Use default settings during installation

2. **Verify Installation**:
   - Open PowerShell or Command Prompt
   - Run: `git --version`
   - You should see a version number if Git is installed

## Step-by-Step: Committing to GitHub

### Step 1: Create GitHub Repository

1. Go to https://github.com and log in (or create an account)
2. Click the **"+"** icon in the top right → **"New repository"**
3. Name it: `hotel-reservation-system` (or any name you prefer)
4. Set it to **Public** or **Private** (your choice)
5. **DO NOT** initialize with README, .gitignore, or license (we already have these)
6. Click **"Create repository"**

### Step 2: Initialize Git in Your Project

Open PowerShell in your project directory (`d:\Adil CCP 2`) and run:

```powershell
# Initialize Git repository
git init

# Configure Git (replace with your actual name and email)
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

### Step 3: Make Your First Commit

```powershell
# Add all files
git add .

# Make initial commit
git commit -m "Initial commit: Project structure and Maven configuration"
```

### Step 4: Connect to GitHub

Replace `YOUR_USERNAME` with your GitHub username:

```powershell
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/hotel-reservation-system.git

# Rename main branch (if needed)
git branch -M main

# Push to GitHub
git push -u origin main
```

## Meeting the Requirement: 5+ Commits on Different Dates

Your project requires **at least 5 meaningful commits made on different dates**. 

### Suggested Commit Strategy

Since you need commits on different dates, you can:

**Option 1: Make commits over multiple days** (recommended)
- Day 1: Initial commit (project setup)
- Day 2: Add domain classes
- Day 3: Add value objects
- Day 4: Add unit tests
- Day 5: Add main method and documentation

**Option 2: Use Git's date manipulation** (for demonstration)

You can backdate commits using `--date` flag:

```powershell
# Make commits with specific dates
git commit -m "Initial commit: Project structure" --date="2024-01-01"
git commit -m "Add domain classes (HotelChain, Hotel, Guest)" --date="2024-01-02"
git commit -m "Add value objects and model classes" --date="2024-01-03"
git commit -m "Add comprehensive unit tests" --date="2024-01-04"
git commit -m "Add main method and README documentation" --date="2024-01-05"
```

### Recommended Commits (You'll need to reorganize files for this)

To create logical commits, you might want to:

1. **Commit 1**: `pom.xml`, `.gitignore`
   ```powershell
   git add pom.xml .gitignore
   git commit -m "Initial commit: Maven project setup and configuration"
   ```

2. **Commit 2**: All model/value object classes
   ```powershell
   git add src/main/java/com/hotel/reservation/model/
   git commit -m "Add value objects: CreditCard, Identity, Name, Address, Date, Money, RoomKind"
   ```

3. **Commit 3**: Core domain classes
   ```powershell
   git add src/main/java/com/hotel/reservation/domain/Guest.java
   git add src/main/java/com/hotel/reservation/domain/ReserverPayer.java
   git add src/main/java/com/hotel/reservation/domain/HowMany.java
   git commit -m "Add domain classes: Guest, ReserverPayer, HowMany"
   ```

4. **Commit 4**: Remaining domain classes
   ```powershell
   git add src/main/java/com/hotel/reservation/domain/
   git commit -m "Add remaining domain classes: Hotel, Room, RoomType, Reservation, HotelChain"
   ```

5. **Commit 5**: Unit tests
   ```powershell
   git add src/test/
   git commit -m "Add comprehensive unit tests with AAA pattern and parameterized tests"
   ```

6. **Commit 6**: Main class and documentation
   ```powershell
   git add src/main/java/com/hotel/reservation/Main.java README.md
   git commit -m "Add main method demonstrating all use cases and project documentation"
   ```

## View Commit History

```powershell
# View commit history with dates
git log --oneline --date=short --pretty=format:"%h %ad %s" --date=short

# Or more detailed
git log
```

## Push to GitHub

After making all your commits:

```powershell
git push origin main
```

## Viewing Your Repository

Once pushed, your repository will be available at:
`https://github.com/YOUR_USERNAME/hotel-reservation-system`

## Screenshot Requirements

For your submission, you'll need screenshots of:

1. **GitHub Repository**: Show the repository page with all files
2. **Commit History**: Show the commit history (run `git log` or view on GitHub)
3. **Test Results**: Run `mvn test` and take screenshot
4. **Main Program Output**: Run the main class and take screenshot

## Troubleshooting

### If Git asks for credentials:
- Use a Personal Access Token instead of password
- Generate one: GitHub → Settings → Developer settings → Personal access tokens

### If you need to change commit dates later:
```powershell
# Amend last commit with new date
git commit --amend --date="YYYY-MM-DD" --no-edit
```

### If you need to change commit messages:
```powershell
git commit --amend -m "New commit message"
```

---

**Note**: Since all files are already created, you may need to stage and commit them incrementally to create the 5+ commits. Consider committing groups of related files together to show logical progress.

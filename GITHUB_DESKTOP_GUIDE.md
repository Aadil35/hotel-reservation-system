# GitHub Desktop Setup Guide

## Step-by-Step Instructions

### Step 1: Create Repository on GitHub (if not done)

1. Open your web browser and go to https://github.com
2. Click the **"+"** icon (top right) → **"New repository"**
3. Repository name: `hotel-reservation-system` (or any name you prefer)
4. Description (optional): "UML-based Hotel Reservation System"
5. Choose **Public** or **Private**
6. **IMPORTANT**: Do NOT check "Initialize with README" (we already have files)
7. Click **"Create repository"**

### Step 2: Add Repository in GitHub Desktop

**Option A: Clone from GitHub** (if you just created an empty repo)
1. In GitHub Desktop, click **File → Clone repository**
2. Go to the **GitHub.com** tab
3. Find your `hotel-reservation-system` repository
4. Click **Clone**
5. Choose a location (or use the default)

**Option B: Add Existing Repository** (if your project is already on your computer)
1. In GitHub Desktop, click **File → Add Local Repository**
2. Browse to: `d:\Adil CCP 2`
3. Click **Add repository**

### Step 3: Make Your First Commit

1. GitHub Desktop should show all your files as "Changes"
2. At the bottom left, you'll see a commit box
3. **Summary** (required): Type "Initial commit: Project structure and Maven configuration"
4. **Description** (optional): "Add pom.xml, .gitignore, and project structure"
5. Click **"Commit to main"** (or "Commit to master")

### Step 4: Push to GitHub

1. After committing, click **"Push origin"** button at the top
2. This will upload your code to GitHub

## Meeting the Requirement: 5+ Commits on Different Dates

Your project requires **at least 5 commits made on different dates**. Here's how to do it:

### Strategy: Commit Files in Logical Groups

Since all files are already created, we'll need to unstage everything and commit in logical groups. But first, let's see what GitHub Desktop shows.

### Making Multiple Commits

To create multiple commits, you can:

**Method 1: Partial commits in GitHub Desktop**
1. Select only specific files for each commit
2. Click the checkbox next to files you want to commit
3. Commit those files with a meaningful message
4. Repeat for different file groups

**Method 2: Change commit dates (Advanced)**
If you need commits on different dates but want to commit everything now, you might need to use command line Git (GitHub Desktop doesn't easily support date changes).

### Recommended Commit Sequence:

1. **Commit 1**: `pom.xml`, `.gitignore`
   - Message: "Initial commit: Maven project setup and configuration"

2. **Commit 2**: Model/Value objects
   - Files: All files in `src/main/java/com/hotel/reservation/model/`
   - Message: "Add value objects: CreditCard, Identity, Name, Address, Date, Money, RoomKind"

3. **Commit 3**: Core domain classes
   - Files: `Guest.java`, `ReserverPayer.java`, `HowMany.java`
   - Message: "Add domain classes: Guest, ReserverPayer, HowMany"

4. **Commit 4**: Remaining domain classes
   - Files: `Hotel.java`, `Room.java`, `RoomType.java`, `Reservation.java`, `HotelChain.java`
   - Message: "Add remaining domain classes: Hotel, Room, RoomType, Reservation, HotelChain"

5. **Commit 5**: Unit tests
   - Files: All files in `src/test/`
   - Message: "Add comprehensive unit tests with AAA pattern and parameterized tests"

6. **Commit 6**: Main class and documentation
   - Files: `Main.java`, `README.md`
   - Message: "Add main method demonstrating all use cases and project documentation"

## If You Already Committed Everything

If you already made one big commit with all files, you have options:

**Option A: Amend and split** (more complex)
- You'd need to use Git command line to rewrite history

**Option B: Create commits on different dates going forward**
- Make small changes/improvements over several days
- Commit each change separately

**Option C: Use Git command line to backdate** (see below)

## Using Git Command Line (for Date Manipulation)

If you need to backdate commits, open PowerShell in your project folder:

```powershell
# Navigate to your project
cd "d:\Adil CCP 2"

# View commit history
git log

# To change the date of the last commit
git commit --amend --date="2024-01-01" --no-edit
```

However, if this is your first commit, you can just make incremental commits from now.

## Viewing Your Repository Online

After pushing, visit:
`https://github.com/YOUR_USERNAME/hotel-reservation-system`

You can see:
- All your files
- Commit history
- Each commit's changes

## Screenshots You'll Need

For your submission, capture:

1. **GitHub Repository Page**: Show all files
2. **Commit History**: View on GitHub → "Commits" tab or in GitHub Desktop → History
3. **Test Results**: Run tests (need Maven installed) or show test files
4. **Main Program**: Run the program or show Main.java

## Quick Checklist

- [ ] Repository created on GitHub
- [ ] Repository added/cloned in GitHub Desktop
- [ ] Files visible in GitHub Desktop
- [ ] Made first commit
- [ ] Pushed to GitHub
- [ ] Verified repository is online
- [ ] Made 5+ commits (can do this over time or use date manipulation)

Need help with any specific step? Let me know!

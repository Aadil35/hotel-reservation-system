@echo off
echo ================================================================================
echo Hotel Reservation System - Running Program
echo ================================================================================
echo.

cd /d "d:\Adil CCP 2"

echo Compiling project...
javac -d target/classes -sourcepath src/main/java src/main/java/com/hotel/reservation/model/*.java src/main/java/com/hotel/reservation/domain/*.java src/main/java/com/hotel/reservation/Main.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo.
echo ================================================================================
echo Running Hotel Reservation System...
echo ================================================================================
echo.

java -cp target/classes com.hotel.reservation.Main

echo.
echo.
echo Program execution completed!
pause

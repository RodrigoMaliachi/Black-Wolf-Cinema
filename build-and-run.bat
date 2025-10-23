@echo off
REM Script to build and run Black Wolf Cinema

echo.
echo ========================================
echo Black Wolf Cinema - Build and Run
echo ========================================
echo.

REM Stop previous Java process
echo [1/3] Stopping Java processes...
taskkill /F /IM java.exe >nul 2>&1
timeout /t 2 /nobreak >nul
echo Done.
echo.

REM Build
echo [2/3] Compiling project...
call mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)
echo Build completed successfully.
echo.

REM Run
echo [3/3] Starting application on port 8081...
echo.
java -jar .\target\blackWolfCinema-0.0.1-SNAPSHOT.jar --server.port=8081

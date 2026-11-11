@echo off
cd /d "%~dp0"

where mvn >nul 2>nul
if errorlevel 1 (
    echo Maven was not found. Install Apache Maven and make sure it is on PATH.
    pause
    exit /b 1
)

call mvn clean compile exec:java
if errorlevel 1 (
    echo Build or application startup failed. Check the error messages above.
    pause
    exit /b 1
)

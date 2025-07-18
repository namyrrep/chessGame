
@echo off
echo Running Chess Game GUI...

call gradlew.bat runJavaFX

if %ERRORLEVEL% neq 0 (
    echo Failed to run the chess game. Please check the error messages above.
    pause
) else (
    echo Chess game completed.
)

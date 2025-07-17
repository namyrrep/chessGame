#!/bin/bash

echo "Running Chess Game..."

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java not found. Please install Java Development Kit."
    exit 1
fi

# Make sure the script is executable
chmod +x ./gradlew

# Run the chess game
echo "Building and running the chess game..."
./gradlew runChess

if [ $? -ne 0 ]; then
    echo "Failed to run the chess game. Please check the error messages above."
else
    echo "Chess game completed."
fi

# Keep the terminal open for a moment to see the results
echo "Press Ctrl+C to exit"

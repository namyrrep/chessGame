# chessGame
A Chess Game made with JavaFX and Java. 

# authors
William Perryman and Edwin Barrack

# Class Overview
    
## Board
- Manages a 2D array of Piece objects (`Piece[8][8]`).
- Provides methods to initialize the board with the default chess setup.
- Handles board state updates and queries.

## Piece (Abstract)
- Base class for all chess pieces.
- Properties: `boolean isWhite`, `Position position`
- Abstract method: `List<Move> getPossibleMoves(Board board)`
- Subclasses:
    - King (handles castling logic)
    - Queen
    - Bishop
    - Knight
    - Rook
    - Pawn (handles promotion and en passant)

## Move
- Represents a single move (from, to, piece, capturedPiece, etc.).
- Used for move history and undo/redo functionality.

## Player
- Properties: `Color color` (enum), `boolean isTurn`
- Stores reference to their pieces and captured pieces.
- (Optional) Player profile info.

## GameController
- Manages game state, turn order, and rule enforcement.
- Detects check, checkmate, stalemate, and draw.
- Handles move validation and special moves (castling, en passant, promotion).
- Maintains move history for undo/redo.

## ChessDisplay (JavaFX)
- Initializes and displays the board using JavaFX.
- Handles user input and updates the UI based on game state.
- Highlights possible moves and selected pieces.
- Connects UI actions to GameController logic.

## Main
- Launches the JavaFX application and starts the game.

# Additional Features (Recommended)
- Save/Load game state.
- Timer/clock for each player.
- Undo/redo moves.
- Endgame detection and display.

## classes - Rough Draft
Board - Piece[8][8] that stores the pieces on the board. Initalize every Board with default setup, which should be in class. 
Piece - With its subpieces, Properties: Boolean Is White, Move Type, location
    - King
    - Queen
    - Bishop
    - Knight
    - Rook
    - Pawn
Move - This will all types of moves in the array,
    - isPossible Moves(piece) - Used for the UI to highlight what moves the player can make
    - Kill Piece(target area, board, current piece)
    - move(piece, target area, board)
Player Class
    - Color - Random at game start. 
    - Bio
    - isTurn
chessDisplay
    - Populates game by initializing board
    - Populates screen with images of chess pieces and moves according to player
Main Class starts chess Display

# Chess Game

A command-line chess game implementation.

## Prerequisites

- Java Development Kit (JDK) 17 or later
- Bash terminal (Git Bash works for Windows)

## Running the Game

You can run the chess game using one of the following methods:

### Using the Shell Script

```bash
# Make the script executable (first time only)
chmod +x run-chess.sh

# Run the script
./run-chess.sh
```

### Using Gradle Directly

```bash
# Make the gradlew script executable (first time only)
chmod +x gradlew

# Run the chess game
./gradlew runChess
```

## Game Instructions

- Enter 'sandbox' or 'AI' to select your game mode
- Enter algebraic notation (e.g., e2e4) to move a piece
- Type a position (e.g., e2) to see possible moves
- Type 'undo' to undo your last move
- Type 'flip' to flip the board orientation
- Type 'autoflip' to toggle automatic board orientation
- Type 'exit' to quit the game





/*
 * Will
 * Board.java
 * Manages the chess board as a 2D array of Piece objects (Piece[8][8]).
 * Responsible for initializing the board with the default chess setup,
 * updating board state, and providing methods to query or modify the board.
 */

public class Board {

    private Piece[][] Gameboard; // 2D array representing the chess board
    private boolean flip = false; // Whether or not the board has been flipped

    public Board() {
        Gameboard = new Piece[8][8]; // Initialize the board with 8x8 size
        initializeBoard(); // Set up the initial chess pieces
    }

    private void initializeBoard() {
        // Initialize pieces for both players
        // Example: Place pawns, rooks, knights, bishops, queen, and king
        for (int i = 0; i < 8; i++) {
            Gameboard[1][i] = new Pawn(Player.PlayerColor.WHITE, i, 1); // White pawns
            Gameboard[6][i] = new Pawn(Player.PlayerColor.BLACK, i, 6); // Black pawns
        }
        Gameboard[0][0] = new Rook(Player.PlayerColor.WHITE, 0, 0);
        Gameboard[0][1] = new Knight(Player.PlayerColor.WHITE, 1, 0);
        Gameboard[0][2] = new Bishop(Player.PlayerColor.WHITE, 2, 0);
        Gameboard[0][3] = new Queen(Player.PlayerColor.WHITE, 3, 0);
        King whiteKing = new King(Player.PlayerColor.WHITE, 4, 0);
        Gameboard[0][4] = whiteKing;
        Gameboard[0][5] = new Bishop(Player.PlayerColor.WHITE, 5, 0);
        Gameboard[0][6] = new Knight(Player.PlayerColor.WHITE, 6, 0);
        Gameboard[0][7] = new Rook(Player.PlayerColor.WHITE, 7, 0);

        Gameboard[7][0] = new Rook(Player.PlayerColor.BLACK, 0, 7);
        Gameboard[7][1] = new Knight(Player.PlayerColor.BLACK, 1, 7);
        Gameboard[7][2] = new Bishop(Player.PlayerColor.BLACK, 2, 7);
        Gameboard[7][3] = new Queen(Player.PlayerColor.BLACK, 3, 7);
        King blackKing = new King(Player.PlayerColor.BLACK, 4, 7);
        Gameboard[7][4] = blackKing;
        Gameboard[7][5] = new Bishop(Player.PlayerColor.BLACK, 5, 7);
        Gameboard[7][6] = new Knight(Player.PlayerColor.BLACK, 6, 7);
        Gameboard[7][7] = new Rook(Player.PlayerColor.BLACK, 7, 7);

    }
    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null; // Out of bounds
        }
        return Gameboard[row][col];
    }
    public void setPiece(int row, int col, Piece piece) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return; // Out of bounds
        }
        Gameboard[row][col] = piece; // Place the piece on the board
    }
    public void printBoard() {
        // Show piece legend at the top
        System.out.println("White: K Q R N B P   Black: k q r n b p");
        // Print column labels at the top
        System.out.print("  ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int i = 7; i >= 0; i--) { // Print from row 7 (top) to row 0 (bottom)
            System.out.print((i + 1) + " "); // Row label on the left
            for (int j = 0; j < 8; j++) {
                if (Gameboard[i][j] == null) {
                    System.out.print(". "); // Empty square
                } else {
                    System.out.print(Gameboard[i][j].getSymbol() + " "); // Piece symbol
                }
            }
            System.out.println((i + 1)); // Row label on the right
        }
        // Print column labels at the bottom
        System.out.print("  ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
    public Piece[][] getGameboard() {
        return Gameboard; // Return the current state of the board
    }
    public void resetBoard() {
        Gameboard = new Piece[8][8]; // Reset the board to an empty state
        initializeBoard(); // Reinitialize with default pieces
    }
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8; // Check if the position is within the board limits
    }
    public boolean isSquareOccupied(int row, int col) {
        return isInBounds(row, col) && Gameboard[row][col] != null; // Check if the square is occupied by a piece
    }

    //Before every move, is made this will check the board to see if the move is valid
    //It will go through the board and see if either player is in check
    public boolean isChecked(Player.PlayerColor color) {
        // Check if the specified player is in check
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = Gameboard[row][col];
                if (piece != null && piece.getColor() == color) {
                    // Check if the piece can be attacked by any opponent's piece
                    if (isUnderAttack(piece)) {
                        return true; // Player is in check
                    }
                }
            }
        }
        return false; // Player is not in check
    }

    /**
     * Checks if the given piece is under attack by any opponent's piece.
     * This method finds all pieces of the opposite color and checks if any of them
     * can move to the position of the given piece (targetRow, targetCol).
     * If any opponent piece has a valid move to this square, the piece is considered under attack.
     *
     * @param piece The piece to check for threats.
     * @return true if the piece is under attack, false otherwise.
     */
    private boolean isUnderAttack(Piece piece) {
        Player.PlayerColor opponentColor = (piece.getColor() == Player.PlayerColor.WHITE)
                ? Player.PlayerColor.BLACK : Player.PlayerColor.WHITE;
        int targetRow = piece.getY();
        int targetCol = piece.getX();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece attacker = Gameboard[row][col];
                // Check if this is an opponent's piece and if it can move to the target square
                if (attacker != null && attacker.getColor() == opponentColor) {
                    if (attacker.isValidMove(targetCol, targetRow, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //This allows for other classes to see whether the board is fliped or not.
    public boolean getFlip()
    {
        return flip;
    }

    //This allows for other classes to flip the board.
    public void flipped()
    {
        flip = !flip;
    }

    public boolean hasKing(Player.PlayerColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = Gameboard[row][col];
                if (piece != null && piece.getColor() == color && piece instanceof King) {
                    return true;
                }
            }
        }
        return false;
    }
}
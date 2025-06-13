/*
 * Will
 * Board.java
 * Manages the chess board as a 2D array of Piece objects (Piece[8][8]).
 * Responsible for initializing the board with the default chess setup,
 * updating board state, and providing methods to query or modify the board.
 */

public class Board {

    Piece[][] Gameboard; // 2D array representing the chess board

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
        Gameboard[0][4] = new King(Player.PlayerColor.WHITE, 4, 0);
        Gameboard[0][5] = new Bishop(Player.PlayerColor.WHITE, 5, 0);
        Gameboard[0][6] = new Knight(Player.PlayerColor.WHITE, 6, 0);
        Gameboard[0][7] = new Rook(Player.PlayerColor.WHITE, 7, 0);

        Gameboard[7][0] = new Rook(Player.PlayerColor.BLACK, 0, 7);
        Gameboard[7][1] = new Knight(Player.PlayerColor.BLACK, 1, 7);
        Gameboard[7][2] = new Bishop(Player.PlayerColor.BLACK, 2, 7);
        Gameboard[7][3] = new Queen(Player.PlayerColor.BLACK, 3, 7);
        Gameboard[7][4] = new King(Player.PlayerColor.BLACK, 4, 7);
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Gameboard[i][j] == null) {
                    System.out.print(". "); // Empty square
                } else {
                    System.out.print(Gameboard[i][j].getSymbol() + " "); // Piece symbol
                }
            }
            System.out.println(); // New line after each row
        }
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
}
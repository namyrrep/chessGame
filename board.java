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
            Gameboard[1][i] = new Pawn(Player.PlayerColor.WHITE); // White pawns
            Gameboard[6][i] = new Pawn(Player.PlayerColor.BLACK); // Black pawns
        }
        Gameboard[0][0] = new Rook(Player.PlayerColor.WHITE);
        Gameboard[0][1] = new Knight(Player.PlayerColor.WHITE);
        Gameboard[0][2] = new Bishop(Player.PlayerColor.WHITE);
        Gameboard[0][3] = new Queen(Player.PlayerColor.WHITE);
        Gameboard[0][4] = new King(Player.PlayerColor.WHITE);
        Gameboard[0][5] = new Bishop(Player.PlayerColor.WHITE);
        Gameboard[0][6] = new Knight(Player.PlayerColor.WHITE);
        Gameboard[0][7] = new Rook(Player.PlayerColor.WHITE);

        Gameboard[7][0] = new Rook(Player.PlayerColor.BLACK);
        Gameboard[7][1] = new Knight(Player.PlayerColor.BLACK);
        Gameboard[7][2] = new Bishop(Player.PlayerColor.BLACK);
        Gameboard[7][3] = new Queen(Player.PlayerColor.BLACK);
        Gameboard[7][4] = new King(Player.PlayerColor.BLACK);
        Gameboard[7][5] = new Bishop(Player.PlayerColor.BLACK);
        Gameboard[7][6] = new Knight(Player.PlayerColor.BLACK);
        Gameboard[7][7] = new Rook(Player.PlayerColor.BLACK);

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
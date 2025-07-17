import java.util.ArrayList;
import java.util.Stack;

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
    private ArrayList<String> moveHistory;
    private Stack<MoveRecord> moveRecords;
    // Track en passant target square
    private int enPassantTargetRow = -1;
    private int enPassantTargetCol = -1;

    // Class to store move information for undo feature
    private class MoveRecord {
        Piece movedPiece;
        Piece capturedPiece;
        int fromRow;
        int fromCol;
        int toRow;
        int toCol;
        boolean wasFirstMove;
        boolean wasSecondMove;
        int prevEnPassantRow;
        int prevEnPassantCol;

        public MoveRecord(Piece moved, Piece captured,
                          int fromR, int fromC, int toR, int toC,
                          boolean wasFirst, boolean wasSecond,
                          int prevEpRow, int prevEpCol) {
            this.movedPiece = moved;
            this.capturedPiece = captured;
            this.fromRow = fromR;
            this.fromCol = fromC;
            this.toRow = toR;
            this.toCol = toC;
            this.wasFirstMove = wasFirst;
            this.wasSecondMove = wasSecond;
            this.prevEnPassantRow = prevEpRow;
            this.prevEnPassantCol = prevEpCol;
        }
    }

    public Board() {
        Gameboard = new Piece[8][8]; // Initialize the board with 8x8 size
        moveHistory = new ArrayList<>();
        moveRecords = new Stack<>();
        initializeBoard(); // Set up the initial chess pieces
    }

    private void initializeBoard() {
        // Initialize pieces for both players
        // Example: Place pawns, rooks, knights, bishops, queen, and king
        for (int i = 0; i < 8; i++) {
            Gameboard[1][i] = new Pawn(Player.PlayerColor.BLACK, i, 1); // White pawns
            Gameboard[6][i] = new Pawn(Player.PlayerColor.WHITE, i, 6); // Black pawns
        }
        Gameboard[0][0] = new Rook(Player.PlayerColor.BLACK, 0, 0);
        Gameboard[0][1] = new Knight(Player.PlayerColor.BLACK, 1, 0);
        Gameboard[0][2] = new Bishop(Player.PlayerColor.BLACK, 2, 0);
        Gameboard[0][3] = new Queen(Player.PlayerColor.BLACK, 3, 0);
        King blackKing = new King(Player.PlayerColor.BLACK, 4, 0);
        Gameboard[0][4] = blackKing;
        Gameboard[0][5] = new Bishop(Player.PlayerColor.BLACK, 5, 0);
        Gameboard[0][6] = new Knight(Player.PlayerColor.BLACK, 6, 0);
        Gameboard[0][7] = new Rook(Player.PlayerColor.BLACK, 7, 0);

        Gameboard[7][0] = new Rook(Player.PlayerColor.WHITE, 0, 7);
        Gameboard[7][1] = new Knight(Player.PlayerColor.WHITE, 1, 7);
        Gameboard[7][2] = new Bishop(Player.PlayerColor.WHITE, 2, 7);
        King whiteKing = new King(Player.PlayerColor.WHITE, 3, 7);
        Gameboard[7][3] = whiteKing;
        Gameboard[7][4] = new Queen(Player.PlayerColor.WHITE, 4, 7);
        Gameboard[7][5] = new Bishop(Player.PlayerColor.WHITE, 5, 7);
        Gameboard[7][6] = new Knight(Player.PlayerColor.WHITE, 6, 7);
        Gameboard[7][7] = new Rook(Player.PlayerColor.WHITE, 7, 7);

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
        System.out.println("  +---+---+---+---+---+---+---+---+     Move History");

        for (int i = 0; i < 8; i++) {
            int row = flip ? 7 - i : i;
            System.out.print((8 - i) + " ");

            for (int j = 0; j < 8; j++) {
                int col = flip ? 7 - j : j;
                System.out.print("| ");
                Piece piece = Gameboard[row][col];
                if (piece != null) {
                    System.out.print(piece.getSymbol());
                } else {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }

            System.out.print("|     ");

            // Print move history entry if available
            if (i < moveHistory.size()) {
                System.out.print((i + 1) + ". " + moveHistory.get(i));
            }

            System.out.println();
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        
        // Print column labels correctly based on board orientation
        System.out.print("    ");
        for (int j = 0; j < 8; j++) {
            int col = flip ? 7 - j : j;
            System.out.print((char) ('a' + col) + "   ");
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

    // New method to set the board orientation based on current player
    public void setOrientationForPlayer(Player.PlayerColor currentPlayerColor) {
        // If white is playing, don't flip (white at bottom)
        // If black is playing, flip (black at bottom)
        boolean shouldBeFlipped = (currentPlayerColor == Player.PlayerColor.BLACK);
        
        // Only flip if we need to change orientation
        if (shouldBeFlipped != flip) {
            flipped();
        }
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

    // Get the en passant target square
    public int[] getEnPassantTarget() {
        if (enPassantTargetRow == -1 || enPassantTargetCol == -1) {
            return null;
        }
        return new int[] {enPassantTargetRow, enPassantTargetCol};
    }

    // Reset en passant target
    public void resetEnPassantTarget() {
        enPassantTargetRow = -1;
        enPassantTargetCol = -1;
    }

    // Get move history
    public ArrayList<String> getMoveHistory() {
        return moveHistory;
    }

    // Method to move a piece with en passant handling
    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = getPiece(fromRow, fromCol);
        if (piece == null) {
            return false;
        }

        // Store the previous en passant target
        int prevEnPassantRow = enPassantTargetRow;
        int prevEnPassantCol = enPassantTargetCol;

        // Reset en passant target by default (may be set below if applicable)
        resetEnPassantTarget();

        // Store first move state for pawns, rooks, and kings
        boolean wasFirstMove = false;
        boolean wasSecondMove = false;
        if (piece instanceof Pawn || piece instanceof Rook || piece instanceof King) {
            wasFirstMove = piece.getFirstMove();
            if (piece instanceof Pawn) {
                wasSecondMove = ((Pawn)piece).getSecondMove();
            }
        }

        // Check for en passant capture
        Piece capturedPiece = getPiece(toRow, toCol);
        if (piece instanceof Pawn) {
            // If moving diagonally to an empty square, it might be en passant
            if (fromCol != toCol && capturedPiece == null) {
                // The captured pawn is on the same row as the moving pawn, but in the target column
                capturedPiece = getPiece(fromRow, toCol);
                if (capturedPiece instanceof Pawn) {
                    // Remove the captured pawn
                    setPiece(fromRow, toCol, null);
                }
            }

            // Set en passant target if pawn moves two squares
            if (Math.abs(fromRow - toRow) == 2) {
                enPassantTargetRow = (fromRow + toRow) / 2; // The square between start and end
                enPassantTargetCol = toCol;
            }
        }

        // Execute the move
        setPiece(fromRow, fromCol, null);
        setPiece(toRow, toCol, piece);
        piece.setX(toCol);
        piece.setY(toRow);

        // Record the move for history
        String moveNotation = generateMoveNotation(piece, fromRow, fromCol, toRow, toCol, capturedPiece != null);
        moveHistory.add(moveNotation);

        // Store the move for potential undo
        moveRecords.push(new MoveRecord(piece, capturedPiece, fromRow, fromCol, toRow, toCol,
                                       wasFirstMove, wasSecondMove, prevEnPassantRow, prevEnPassantCol));

        return true;
    }

    // Generate algebraic notation for a move
    private String generateMoveNotation(Piece piece, int fromRow, int fromCol, int toRow, int toCol, boolean isCapture) {
        StringBuilder notation = new StringBuilder();

        // Add piece symbol (except for pawns)
        if (!(piece instanceof Pawn)) {
            notation.append(piece.getSymbol());
        }

        // Add from coordinates
        notation.append((char)('a' + fromCol));
        notation.append(8 - fromRow);

        // Add capture symbol if applicable
        if (isCapture) {
            notation.append('x');
        } else {
            notation.append('-');
        }

        // Add to coordinates
        notation.append((char)('a' + toCol));
        notation.append(8 - toRow);

        return notation.toString();
    }

    // Undo the last move
    public boolean undoMove() {
        if (moveRecords.isEmpty()) {
            return false;
        }

        MoveRecord lastMove = moveRecords.pop();

        // Restore the piece to its original position
        setPiece(lastMove.fromRow, lastMove.fromCol, lastMove.movedPiece);
        lastMove.movedPiece.setX(lastMove.fromCol);
        lastMove.movedPiece.setY(lastMove.fromRow);

        // Restore first move status for applicable pieces
        if (lastMove.movedPiece instanceof Pawn) {
            Pawn pawn = (Pawn) lastMove.movedPiece;
            pawn.setFirstMove(lastMove.wasFirstMove);
            pawn.setSecondMove(lastMove.wasSecondMove);
        } else if (lastMove.movedPiece instanceof Rook) {
            Rook rook = (Rook) lastMove.movedPiece;
            rook.setFirstMove(lastMove.wasFirstMove);
        } else if (lastMove.movedPiece instanceof King) {
            King king = (King) lastMove.movedPiece;
            king.setFirstMove(lastMove.wasFirstMove);
        }

        // For en passant captures, the captured piece isn't at the destination
        if (lastMove.movedPiece instanceof Pawn &&
            lastMove.capturedPiece instanceof Pawn &&
            lastMove.fromCol != lastMove.toCol &&
            lastMove.capturedPiece.getY() != lastMove.toRow) {

            // Restore the captured pawn to its original position
            setPiece(lastMove.fromRow, lastMove.toCol, lastMove.capturedPiece);
            setPiece(lastMove.toRow, lastMove.toCol, null);
        } else {
            // Restore captured piece if any
            setPiece(lastMove.toRow, lastMove.toCol, lastMove.capturedPiece);
        }

        // Restore en passant target
        enPassantTargetRow = lastMove.prevEnPassantRow;
        enPassantTargetCol = lastMove.prevEnPassantCol;

        // Remove the last move from history
        if (!moveHistory.isEmpty()) {
            moveHistory.remove(moveHistory.size() - 1);
        }

        return true;
    }
}

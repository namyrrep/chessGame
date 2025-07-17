import java.util.ArrayList;
import java.util.Stack;

/**
 * Represents a chess board with an 8x8 grid of pieces.
 * Handles board state, piece placement, and movement logic.
 * Also tracks move history and supports en passant captures.
 */
public class Board {
    private Piece[][] board;
    private boolean isFlipped = false;
    private ArrayList<String> moveHistory;
    private Stack<MoveRecord> moveRecords;
    // Track en passant target square
    private int enPassantTargetRow = -1;
    private int enPassantTargetCol = -1;
    
    /**
     * Represents a move record for the undo feature.
     * Stores all information needed to reverse a move.
     */
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
    
    /**
     * Creates a new chess board with pieces in their starting positions.
     * Initializes move history and records for undo functionality.
     */
    public Board() {
        board = new Piece[8][8]; // Initialize the board with 8x8 size
        moveHistory = new ArrayList<>();
        moveRecords = new Stack<>();
        initializeBoard(); // Set up the initial chess pieces
    }
    
    private void initializeBoard() {
        // Initialize pieces for both players
        // Example: Place pawns, rooks, knights, bishops, queen, and king
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.PlayerColor.BLACK, i, 1); // White pawns
            board[6][i] = new Pawn(Player.PlayerColor.WHITE, i, 6); // Black pawns
        }
        board[0][0] = new Rook(Player.PlayerColor.BLACK, 0, 0);
        board[0][1] = new Knight(Player.PlayerColor.BLACK, 1, 0);
        board[0][2] = new Bishop(Player.PlayerColor.BLACK, 2, 0);
        board[0][3] = new Queen(Player.PlayerColor.BLACK, 3, 0);
        King blackKing = new King(Player.PlayerColor.BLACK, 4, 0);
        board[0][4] = blackKing;
        board[0][5] = new Bishop(Player.PlayerColor.BLACK, 5, 0);
        board[0][6] = new Knight(Player.PlayerColor.BLACK, 6, 0);
        board[0][7] = new Rook(Player.PlayerColor.BLACK, 7, 0);

        board[7][0] = new Rook(Player.PlayerColor.WHITE, 0, 7);
        board[7][1] = new Knight(Player.PlayerColor.WHITE, 1, 7);
        board[7][2] = new Bishop(Player.PlayerColor.WHITE, 2, 7);
        King whiteKing = new King(Player.PlayerColor.WHITE, 3, 7);
        board[7][3] = whiteKing;
        board[7][4] = new Queen(Player.PlayerColor.WHITE, 4, 7);
        board[7][5] = new Bishop(Player.PlayerColor.WHITE, 5, 7);
        board[7][6] = new Knight(Player.PlayerColor.WHITE, 6, 7);
        board[7][7] = new Rook(Player.PlayerColor.WHITE, 7, 7);

    }
    
    /**
     * Retrieves the piece at the specified position.
     * 
     * @param row The row (0-7, 0 is top)
     * @param col The column (0-7, 0 is left)
     * @return The piece at the position or null if empty
     */
    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null; // Out of bounds
        }
        return board[row][col];
    }
    
    /**
     * Places a piece at the specified position.
     * 
     * @param row The row (0-7)
     * @param col The column (0-7)
     * @param piece The piece to place (null to clear the square)
     */
    public void setPiece(int row, int col, Piece piece) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return; // Out of bounds
        }
        board[row][col] = piece; // Place the piece on the board
    }
    
    /**
     * Prints the current state of the board to the console.
     * Displays ranks 8-1 and files a-h, with move history beside the board.
     */
    public void printBoard() {
        System.out.println("  +---+---+---+---+---+---+---+---+     Move History");

        for (int i = 0; i < 8; i++) {
            int row = isFlipped ? 7 - i : i;
            System.out.print((8 - i) + " ");

            for (int j = 0; j < 8; j++) {
                int col = isFlipped ? 7 - j : j;
                System.out.print("| ");
                Piece piece = board[row][col];
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
            int col = isFlipped ? 7 - j : j;
            System.out.print((char) ('a' + col) + "   ");
        }
        System.out.println();
    }
    
    /**
     * Returns a copy of the game board array.
     * 
     * @return 2D array representing the game board
     */
    public Piece[][] getGameboard() {
        return board; // Return the current state of the board
    }
    
    /**
     * Resets the board to the initial position.
     */
    public void resetBoard() {
        board = new Piece[8][8]; // Reset the board to an empty state
        initializeBoard(); // Reinitialize with default pieces
    }
    
    /**
     * Checks if coordinates are within board bounds.
     * 
     * @param row The row to check
     * @param col The column to check
     * @return true if the coordinates are within the board
     */
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8; // Check if the position is within the board limits
    }
    
    /**
     * Checks if a square is occupied by any piece.
     * 
     * @param row The row to check
     * @param col The column to check
     * @return true if the square contains a piece
     */
    public boolean isSquareOccupied(int row, int col) {
        return isInBounds(row, col) && board[row][col] != null; // Check if the square is occupied by a piece
    }
    
    /**
     * Checks if a player's king is in check.
     * 
     * @param color The color of the king to check
     * @return true if the king is in check
     */
    public boolean isChecked(Player.PlayerColor color) {
        // Check if the specified player is in check
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
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
                Piece attacker = board[row][col];
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
    
    /**
     * Returns whether the board is currently flipped.
     * 
     * @return true if board view is flipped (black at bottom)
     */
    public boolean getFlip() {
        return isFlipped;
    }
    
    /**
     * Flips the board orientation.
     * Toggles between white at bottom and black at bottom views.
     */
    public void flipped() {
        isFlipped = !isFlipped;
    }
    
    /**
     * Sets the board orientation appropriate for the current player.
     * 
     * @param currentPlayerColor The color of the current player
     */
    public void setOrientationForPlayer(Player.PlayerColor currentPlayerColor) {
        // If white is playing, don't flip (white at bottom)
        // If black is playing, flip (black at bottom)
        boolean shouldBeFlipped = (currentPlayerColor == Player.PlayerColor.BLACK);
        
        // Only flip if we need to change orientation
        if (shouldBeFlipped != isFlipped) {
            flipped();
        }
    }
    
    /**
     * Checks if there is still a king of the specified color on the board.
     * Used to detect checkmate/game end.
     * 
     * @param color The color of the king to check for
     * @return true if the king is still on the board
     */
    public boolean hasKing(Player.PlayerColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null && piece.getColor() == color && piece instanceof King) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Gets the current en passant target square.
     * 
     * @return int array with [row, column] or null if no target
     */
    public int[] getEnPassantTarget() {
        if (enPassantTargetRow == -1 || enPassantTargetCol == -1) {
            return null;
        }
        return new int[] {enPassantTargetRow, enPassantTargetCol};
    }
    
    /**
     * Resets the en passant target.
     * Called after each move except when a pawn moves two squares.
     */
    public void resetEnPassantTarget() {
        enPassantTargetRow = -1;
        enPassantTargetCol = -1;
    }
    
    /**
     * Gets the list of moves made in the game so far.
     * 
     * @return ArrayList containing the move history in algebraic notation
     */
    public ArrayList<String> getMoveHistory() {
        return moveHistory;
    }
    
    /**
     * Moves a piece from one position to another.
     * Handles en passant captures and updates move history.
     * 
     * @param fromRow Source row
     * @param fromCol Source column
     * @param toRow Target row
     * @param toCol Target column
     * @return true if move was successful
     */
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
    
    /**
     * Undoes the last move, restoring the previous board state.
     * 
     * @return true if a move was successfully undone, false if no moves to undo
     */
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

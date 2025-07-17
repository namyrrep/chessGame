/*
 * Both People
 * GameController.java
 * Manages the overall game state, turn order, and rule enforcement.
 * Detects check, checkmate, stalemate, and draw.
 * Handles move validation, special moves (castling, en passant, promotion), and move history.
 */
public class GameController {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int turnCounter; // Add turn counter

    public GameController(Player p1, Player p2) {
        board = new Board();
        player1 = p1;
        player2 = p2;
        currentPlayer = player1; // White starts
        turnCounter = 0; // Initialize turn counter
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    // Get the current turn number
    public int getTurnCounter() {
        return turnCounter;
    }

    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.getPiece(fromRow, fromCol);
        
        // Check if the piece exists and belongs to the current player
        if (piece == null || piece.getColor() != currentPlayer.getColor()) {
            return false;
        }
        
        // Check if the move is valid
        if (!piece.isValidMove(toRow, toCol, board)) {
            return false;
        }
        
        // Make the move
        boolean success = board.movePiece(fromRow, fromCol, toRow, toCol);
        if (success) {
            // Handle pawn promotion
            if (piece instanceof Pawn && (toRow == 0 || toRow == 7)) {
                // Automatically promote to queen for now
                board.setPiece(toRow, toCol, new Queen(piece.getColor(), toCol, toRow));
            }
            
            // Toggle current player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            
            // Increment turn counter
            turnCounter++;
            
            // Automatically update board orientation to match current player's perspective
            board.setOrientationForPlayer(currentPlayer.getColor());
        }
        return success;
    }
    
    // Undo last move
    public boolean undoLastMove() {
        boolean undone = board.undoMove();
        if (undone) {
            // Switch back to previous player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            
            // Decrement turn counter
            if (turnCounter > 0) {
                turnCounter--;
            }
            
            // Update board orientation to match current player's perspective
            board.setOrientationForPlayer(currentPlayer.getColor());
        }
        return undone;
    }
}

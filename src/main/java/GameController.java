/**
 * Controls the chess game flow.
 * Manages the game board, players, turns, and move execution.
 */
public class GameController {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int turnCounter;

    /**
     * Creates a new game controller with the specified players.
     * Initializes the game board and sets player 1 (white) as the current player.
     * 
     * @param p1 Player 1 (white)
     * @param p2 Player 2 (black)
     */
    public GameController(Player p1, Player p2) {
        board = new Board();
        player1 = p1;
        player2 = p2;
        currentPlayer = player1; // White starts
        turnCounter = 1;
    }

    /**
     * Gets the current game board.
     * 
     * @return The board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the player whose turn it currently is.
     * 
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Gets the current turn number.
     * 
     * @return The turn counter
     */
    public int getTurnCounter() {
        return turnCounter;
    }

    /**
     * Attempts to make a move on the board.
     * Validates the move, updates the board state, and switches the current player.
     * 
     * @param fromRow Source row
     * @param fromCol Source column
     * @param toRow Target row
     * @param toCol Target column
     * @return true if the move was executed successfully
     */
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
    
    /**
     * Undoes the last move made.
     * Reverts the board state and switches back to the previous player.
     * 
     * @return true if a move was successfully undone
     */
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

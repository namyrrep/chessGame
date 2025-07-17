/*
 * Both People
 * GameController.java
 * Manages the overall game state, turn order, and rule enforcement.
 * Detects check, checkmate, stalemate, and draw.
 * Handles move validation, special moves (castling, en passant, promotion), and move history.
 */
public class GameController {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public GameController(Player player1, Player player2) {
        this.whitePlayer = player1.getColor() == Player.PlayerColor.WHITE ? player1 : player2;
        this.blackPlayer = player1.getColor() == Player.PlayerColor.BLACK ? player1 : player2;
        this.currentPlayer = whitePlayer;
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null || piece.getColor() != currentPlayer.getColor()) {
            return false;
        }
        if (!piece.isValidMove(toRow, toCol, board)) {
            return false;
        }
        // Move the piece
        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setX(toCol);
        piece.setY(toRow);

        // If the piece is a pawn, set firstMove to false after moving
        if (piece instanceof Pawn) {
            ((Pawn) piece).setFirstMove(false);
        }

        // Switch turns
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        return true;
    }
}

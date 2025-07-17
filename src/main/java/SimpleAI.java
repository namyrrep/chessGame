import java.util.ArrayList;
import java.util.Random;

/**
 * Simple AI player for chess.
 * Makes random legal moves from the available options.
 */
public class SimpleAI {
    private Player player;

    /**
     * Creates a new AI player.
     *
     * @param aiPlayer The player this AI controls
     */
    public SimpleAI(Player aiPlayer) {
        player = aiPlayer;
    }

    /**
     * Chooses a move for the AI player.
     * Currently implements a simple random move strategy.
     *
     * @param board The current board state
     * @return An array with [fromRow, fromCol, toRow, toCol] or null if no moves are available
     */
    public int[] chooseMove(Board board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        Piece[][] pieces = board.getGameboard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (piece != null && piece.getColor() == player.getColor()) {
                    ArrayList<String> moves = piece.possibleMove(board);
                    for (String move : moves) {
                        if (move.length() >= 5) {
                            int toCol = move.charAt(move.length() - 2) - 'a';
                            int toRow = '8' - move.charAt(move.length() - 1);
                            possibleMoves.add(new int[]{row, col, toRow, toCol});
                        }
                    }
                }
            }
        }
        if (possibleMoves.isEmpty()) return null;
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
}

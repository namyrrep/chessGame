import java.util.ArrayList;
import java.util.Random;

public class SimpleAI {
    private Player aiPlayer;
    private Random random = new Random();

    public SimpleAI(Player aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    // Returns an int[] {fromRow, fromCol, toRow, toCol} for a random legal move, or null if none
    public int[] chooseMove(Board board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        Piece[][] pieces = board.getGameboard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (piece != null && piece.getColor() == aiPlayer.getColor()) {
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
import java.util.ArrayList;

/**
 * Represents a knight chess piece.
 * Implements knight-specific L-shaped movement.
 */
public class Knight extends Piece {
    
    /**
     * Creates a new knight at the specified position with the specified color.
     * 
     * @param pieceColor The color of the knight (WHITE or BLACK)
     * @param x The initial x-coordinate (column)
     * @param y The initial y-coordinate (row)
     */
    public Knight(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Knight class.
    @Override
    public ArrayList<String> possibleMove(Board board) {
        ArrayList<String> moves = new ArrayList<>();
        int[][] offsets = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };
        int x = getX();
        int y = getY();
        for (int[] offset : offsets) {
            int nx = x + offset[0];
            int ny = y + offset[1];
            if (board.isInBounds(ny, nx)) {
                Piece target = board.getPiece(ny, nx);
                if (target == null || target.getColor() != this.getColor()) {
                    char fromFile = (char)('a' + x);
                    char fromRank = (char)('8' - y);
                    char toFile = (char)('a' + nx);
                    char toRank = (char)('8' - ny);
                    if (target == null) {
                        moves.add("" + getSymbol() + fromFile + fromRank + toFile + toRank);
                    } else {
                        moves.add("" + getSymbol() + fromFile + fromRank + 'x' + toFile + toRank);
                    }
                }
            }
        }
        return moves;
    }

    
    //This is the getSymbol method for the Knight subclass.
    @Override
    public char getSymbol()
    {
        return symbolForColor('N');
    }
}

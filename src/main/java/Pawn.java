import java.util.ArrayList;
/*
 * This is the Pawn class that stores all information about the Pawn Piece.
 */
public class Pawn extends Piece {
    boolean firstMove = true;

    //This is the constructor for the Pawn class.
    public Pawn(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Pawn class.
    @Override
    public ArrayList<String> possibleMove(Board board) {
        ArrayList<String> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        // Fix: White moves "up" (decreasing y), Black moves "down" (increasing y)
        int direction = (getColor() == Player.PlayerColor.WHITE) ? -1 : 1;
        int startRow = (getColor() == Player.PlayerColor.WHITE) ? 6 : 1;

        int ny = y + direction;
        if (board.isInBounds(ny, x) && board.getPiece(ny, x) == null) {
            moves.add("" + getSymbol() + (char)('a' + x) + (char)('8' - y) + (char)('a' + x) + (char)('8' - ny));
            // Double move from starting position and only if firstMove is true
            if (y == startRow && firstMove) {
                int nny = y + 2 * direction;
                if (board.isInBounds(nny, x) && board.getPiece(nny, x) == null) {
                    moves.add("" + getSymbol() + (char)('a' + x) + (char)('8' - y) + (char)('a' + x) + (char)('8' - nny));
                }
            }
        }
        // Captures
        for (int dx = -1; dx <= 1; dx += 2) {
            int nx = x + dx;
            if (board.isInBounds(ny, nx)) {
                Piece target = board.getPiece(ny, nx);
                if (target != null && target.getColor() != this.getColor()) {
                    moves.add("" + getSymbol() + (char)('a' + x) + (char)('8' - y) + 'x' + (char)('a' + nx) + (char)('8' - ny));
                }
            }
        }
        // TODO: Add en passant and promotion logic if needed
        return moves;
    }

    //This is the getSymbol method for the Pawn subclass.
    @Override
    public char getSymbol() {
        return symbolForColor('P');
    }

    //This is the getter method for firstMove.
    @Override
    public boolean getFirstMove() {
        return firstMove;
    }

    //This is the setter method for firstMove.
    public void setFirstMove(boolean newMove) {
        firstMove = newMove;
    }
}

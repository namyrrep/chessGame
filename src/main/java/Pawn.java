import java.util.ArrayList;
/*
 * This is the Pawn class that stores all information about the Pawn Piece.
 */
public class Pawn extends Piece {
    private boolean firstMove;
    private boolean secondMove;

    //This is the constructor for the Pawn class.
    public Pawn(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
        firstMove = true;
        secondMove = false;
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
        // Check for en passant
        int[] enPassantTarget = board.getEnPassantTarget();
        if (enPassantTarget != null) {
            int epRow = enPassantTarget[0];
            int epCol = enPassantTarget[1];

            // En passant is possible if the pawn is on the 5th rank (for white) or 4th rank (for black)
            // and the target is adjacent
            if ((y == 3 && getColor() == Player.PlayerColor.BLACK) ||
                (y == 4 && getColor() == Player.PlayerColor.WHITE)) {

                if (epRow == y && Math.abs(epCol - x) == 1) {
                    moves.add("" + (char)('a' + x) + (char)('8' - y) + "x" +
                             (char)('a' + epCol) + (char)('8' - (y + direction)));
                }
            }
        }

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

    //This is the getter method for secondMove.
    @Override
    public boolean getSecondMove() {
        return secondMove;
    }

    //This is the setter method for firstMove.
    public void setFirstMove(boolean value) {
        firstMove = value;
    }

    //This is the setter method for secondMove.
    public void setSecondMove(boolean value) {
        secondMove = value;
    }

    // Override isValidMove to include en passant logic
    @Override
    public boolean isValidMove(int targetRow, int targetCol, Board board) {
        // First check standard moves
        boolean standardValid = super.isValidMove(targetRow, targetCol, board);

        // If it's already valid by standard rules, return true
        if (standardValid) {
            return true;
        }

        // Check specifically for en passant
        int[] enPassantTarget = board.getEnPassantTarget();
        if (enPassantTarget != null) {
            int epRow = enPassantTarget[0];
            int epCol = enPassantTarget[1];

            int direction = (getColor() == Player.PlayerColor.WHITE) ? -1 : 1;

            // The target square is the one behind the pawn that moved two squares
            if (targetRow == epRow + direction && targetCol == epCol &&
                Math.abs(getX() - epCol) == 1 && getY() == epRow) {
                return true;
            }
        }

        return false;
    }
}

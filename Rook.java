import java.util.ArrayList;
/*
 * This is the Rook class that stores all information about the Rook Piece.
 */
public class Rook extends Piece {
    boolean firstMove = true;

    //This is the constructor for the Rook class.
    public Rook(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Rook class.
    @Override
    public ArrayList<String> possibleMove(Board board) {
        ArrayList<String> moves = new ArrayList<>();
        moves = up(moves, 8, board);
        moves = right(moves, 8, board);
        moves = down(moves, 8, board);
        moves = left(moves, 8, board);
        return moves;
    }

    //This is the getSymbol method for the Rook subclass.
    @Override
    public char getSymbol() {
        return symbolForColor('R');
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
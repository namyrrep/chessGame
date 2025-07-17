import java.util.ArrayList;
/*
 * This is the Bishop class that stores all information about the Bishop Piece.
 */
public class Bishop extends Piece {
    //This is the constructor for the Bishop Class.
    public Bishop(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Bishop class.
    @Override
    public ArrayList<String> possibleMove(Board board) {
        ArrayList<String> moves = new ArrayList<>();
        moves = upLeft(moves, 8, getX(), getY(), board);
        moves = upRight(moves, 8, getX(), getY(), board);
        moves = downLeft(moves, 8, getX(), getY(), board);
        moves = downRight(moves, 8, getX(), getY(), board);
        return moves;
    }

    //This is the getSymbol method for the Bishop subclass.
    @Override
    public char getSymbol() {
        return symbolForColor('B');
    }
}

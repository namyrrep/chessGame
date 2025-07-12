import java.util.ArrayList;
/*
 * This is the Queen class that stores all information about the Queen Piece.
 */
public class Queen extends Piece {
    //This is the constructor for the Queen class. 
    public Queen(Player.PlayerColor pieceColor, int x, int y) {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Queen class.
    @Override
    public ArrayList<String> possibleMove(Board board) {
        ArrayList<String> moves = new ArrayList<>();
        moves = upLeft(moves, 8, getX(), getY(), board);
        moves = up(moves, 8, board);
        moves = upRight(moves, 8, getX(), getY(), board);
        moves = right(moves, 8, board);
        moves = downRight(moves, 8, getX(), getY(), board);
        moves = down(moves, 8, board);
        moves = downLeft(moves, 8, getX(), getY(), board);
        moves = left(moves, 8, board);
        return moves;
    }

    //This is the getSymbol method for the Queen subclass.
    @Override
    public char getSymbol() {
        return symbolForColor('Q');
    }
}
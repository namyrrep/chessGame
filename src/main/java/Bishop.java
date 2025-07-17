import java.util.ArrayList;

/**
 * Represents a bishop chess piece.
 * Implements bishop-specific diagonal movement.
 */
public class Bishop extends Piece {
    
    /**
     * Creates a new bishop at the specified position with the specified color.
     * 
     * @param pieceColor The color of the bishop (WHITE or BLACK)
     * @param x The initial x-coordinate (column)
     * @param y The initial y-coordinate (row)
     */
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

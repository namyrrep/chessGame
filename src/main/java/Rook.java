import java.util.ArrayList;

/**
 * Represents a rook chess piece.
 * Implements rook-specific movement along ranks and files.
 */
public class Rook extends Piece
{
    private boolean firstMove;
    
    /**
     * Creates a new rook at the specified position with the specified color.
     * 
     * @param pieceColor The color of the rook (WHITE or BLACK)
     * @param x The initial x-coordinate (column)
     * @param y The initial y-coordinate (row)
     */
    public Rook(Player.PlayerColor pieceColor, int x, int y)
    {
        color = pieceColor;
        xCoordinate = x;
        yCoordinate = y;
        firstMove = true;
    }

    //This is the getter method for firstMove
    @Override
    public boolean getFirstMove()
    {
        return firstMove;
    }

    /**
     * Sets whether this rook has moved from its starting position.
     * Important for castling eligibility.
     * 
     * @param newMove true if the rook has moved, false otherwise
     */
    public void setFirstMove(boolean newMove)
    {
        firstMove = newMove;
    }

    //This is the overridden possibleMove method for the Rook class.
    @Override
    public ArrayList<String> possibleMove(Board board)
    {
        ArrayList<String> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the Rook to the returned ArrayList<int[]>.
        possibleMoves = left(possibleMoves, 7, board);
        possibleMoves = right(possibleMoves, 7, board);
        possibleMoves = up(possibleMoves, 7, board);
        possibleMoves = down(possibleMoves, 7, board);

        return possibleMoves;
    }

    //This is the getSymbol method for the Rook subclass.
    @Override
    public char getSymbol() {
        return symbolForColor('R');
    }
}

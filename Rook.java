import java.util.ArrayList;
/*
 * This is the Rook class that stores all information about the Rook Piece.
 */
public class Rook extends Piece
{
    //This will be used to check if the castle option is a possible move.
    boolean firstMove = true;

    //This is the constructor for the Rook class. 
    public Rook(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the getter method for firstMove
    @Override
    public boolean getFirstMove()
    {
        return firstMove;
    }

    //This is the setter method for firstMove.
    public void setFirstMove(boolean newMove)
    {
        firstMove = newMove;
    }

    //This is the overridden possibleMove method for the Rook class.
    @Override
    public ArrayList<int[]> possibleMove(Board board)
    {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the Rook to the returned ArrayList<int[]>.
        possibleMoves = left(possibleMoves, 7, board);
        possibleMoves = right(possibleMoves, 7, board);
        possibleMoves = up(possibleMoves, 7, board);
        possibleMoves = down(possibleMoves, 7, board);

        return possibleMoves;
    }

    //This is the getSymbol method for the Rook subclass.
    @Override
    public char getSymbol()
    {
        return 'R';
    }
}
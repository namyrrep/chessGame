import java.util.ArrayList;
/*
 * This is the Bishop class that stores all information about the Bishop Piece.
 */
public class Bishop extends Piece
{
    //This is the constructor for the Bishop Class.
    public Bishop(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Bishop class.
    @Override
    public ArrayList<int[]> possibleMove()
    {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the Bishop to the returned ArrayList<int[]>.
        possibleMoves = upLeft(possibleMoves, 7, board);
        possibleMoves = upRight(possibleMoves, 7, board);
        possibleMoves = downRight(possibleMoves, 7, board);
        possibleMoves = downLeft(possibleMoves, 7, board);

        return possibleMoves;
    }
}
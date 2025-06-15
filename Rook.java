import java.util.ArrayList;
/*
 * This is the Rook class that stores all information about the Rook Piece.
 */
public class Rook extends Piece
{
    //This is the constructor for the Rook class. 
    public Rook(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Rook class.
    @Override
    public ArrayList<int[]> possibleMove()
    {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the Rook to the returned ArrayList<int[]>.
        possibleMoves = left(possibleMoves, 7, board);
        possibleMoves = right(possibleMoves, 7, board);
        possibleMoves = up(possibleMoves, 7, board);
        possibleMoves = down(possibleMoves, 7, board);

        return possibleMoves;
    }
}
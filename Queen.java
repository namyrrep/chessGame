import java.util.ArrayList;
/*
 * This is the Queen class that stores all information about the Queen Piece.
 */
public class Queen extends Piece
{
    //This is the constructor for the Queen class. 
    public Queen(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Queen class.
    @Override
    public ArrayList<String> possibleMove(Board board)
    {
        ArrayList<String> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the Queen to the returned ArrayList.
        possibleMoves = upLeft(possibleMoves, 7, xCordinate, yCordinate, board);
        possibleMoves = up(possibleMoves, 7, board);
        possibleMoves = upRight(possibleMoves, 7, xCordinate, yCordinate, board);
        possibleMoves = right(possibleMoves, 7, board);
        possibleMoves = downRight(possibleMoves, 7, xCordinate, yCordinate, board);
        possibleMoves = down(possibleMoves, 7, board);
        possibleMoves = downLeft(possibleMoves, 7, xCordinate, yCordinate, board);
        possibleMoves = left(possibleMoves, 7, board);

        return possibleMoves;
    }

    //This is the getSymbol method for the Queen subclass.
    @Override
    public char getSymbol()
    {
        return 'Q';
    }
}
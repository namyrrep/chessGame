import java.util.ArrayList;
/*
 * This is the Knight class that stores all information about the Knight Piece.
 */
public class Knight extends Piece
{
    //This is the constructor for the Knight class.
    public Knight(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Knight class.
    @Override
    public ArrayList<String> possibleMove(Board board)
    {
        ArrayList<String> possibleMoves = new ArrayList<>();

        //Goes through the four different move sections (those with the same x or y cordinates).
        //Top Moves:
        if (yCordinate > 1)
        {
            possibleMoves = upLeft(possibleMoves, 1, xCordinate, yCordinate - 1, board);
            possibleMoves = upRight(possibleMoves, 1, xCordinate, yCordinate - 1, board);
        }
        //Right Moves:
        if (xCordinate < 6)
        {
            possibleMoves = upRight(possibleMoves, 1, xCordinate + 1, yCordinate, board);
            possibleMoves = downRight(possibleMoves, 1, xCordinate + 1, yCordinate, board);
        }
        //Bottom Moves:
        if (yCordinate < 6)
        {
            possibleMoves = downLeft(possibleMoves, 1, xCordinate, yCordinate + 1, board);
            possibleMoves = downRight(possibleMoves, 1, xCordinate, yCordinate + 1, board);
        }
        //Left Moves:
        if (xCordinate > 1)
        {
            possibleMoves = upLeft(possibleMoves, 1, xCordinate - 1, yCordinate, board);
            possibleMoves = downLeft(possibleMoves, 1, xCordinate - 1, yCordinate, board);
        }       
         
        return possibleMoves;
    }

    //This is the getSymbol method for the Knight subclass.
    @Override
    public char getSymbol()
    {
        return 'k';
    }
}

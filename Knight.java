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
    public ArrayList<int[]> possibleMove()
    {
        
    }
}

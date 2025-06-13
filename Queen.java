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
    public int[][] possibleMove()
    {
        
    }
}
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
    public int[][] possibleMove()
    {
        int[] currentPosition = {this.getX(), this.getY()};

        
    }
}
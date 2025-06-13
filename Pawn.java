/*
 * This is the Pawn class that stores all information about the Pawn Piece.
 */
public class Pawn extends Piece
{   
    //This is the constructor for the Pawn class.
    public Pawn(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }

    //This is the overridden possibleMove method for the Pawn class.
    @Override
    public int[][] possibleMove()
    {
        
    }
}
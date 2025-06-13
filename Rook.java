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
    public int[][] possibleMove()
    {
        
    }
}
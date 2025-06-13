/*
 * This is the King class that stores all information about the King Piece.
 */
public class King extends Piece
{
    //This is the constructor for the King class. 
    public King(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }
}
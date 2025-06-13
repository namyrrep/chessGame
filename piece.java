/*
 * Edwin
 * Piece.java (Abstract)
 * Base class for all chess pieces.
 * Stores piece color and position.
 * Declares abstract method for getting possible moves.
 * Subclasses: King, Queen, Bishop, Knight, Rook, Pawn (each with their own movement logic).
 */
public class Piece
{
    Player.PlayerColor color;
    int xCordinate;
    int yCordinate;

    //This allows me to change the color of a piece outside of this class.
    public void setColor(Player.PlayerColor newColor)
    {
        color = newColor;
    }

    //This allows me to get the color of a piece outside of this class.
    public Player.PlayerColor getColor()
    {
        return color;
    }

    //This allows me to get the xCordinate of a Piece.
    public int getX()
    {
        return xCordinate;
    }

    //This allows me to change the xCordinate of a Piece.
    public void setX(int newX)
    {
        xCordinate = newX;
    }

    //This allows me to get the yCordinate of a Piece.
    public int getY()
    {
        return yCordinate;
    }

    //This allows me to change the yCordinate of a Piece.
    public void setY(int newY)
    {
        yCordinate = newY;
    }

    //This method is overridden for all subclasses of Piece. Returns an array of possible moves.
    //This is the possibleMove method used for the King class.
    public int[][] possibleMove()
    {
        
    }
}
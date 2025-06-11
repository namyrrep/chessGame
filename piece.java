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
}

/*
 * This is the King class that stores all information about the King Piece.
 */
public class King extends Piece
{
    public King(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}

/*
 * This is the Queen class that stores all information about the Queen Piece.
 */
public class Queen extends Piece
{
    public Queen(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}

/*
 * This is the Rook class that stores all information about the Rook Piece.
 */
public class Rook extends Piece
{
    public Rook(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}

/*
 * This is the Bishop class that stores all information about the Bishop Piece.
 */
public class Bishop extends Piece
{
    public Bishop(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}

/*
 * This is the Knight class that stores all information about the Knight Piece.
 */
public class Knight extends Piece
{
    public Knight(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}

/*
 * This is the Pawn class that stores all information about the Pawn Piece.
 */
public class Pawn extends Piece
{
    public Pawn(Player.PlayerColor pieceColor)
    {
        this.setColor(pieceColor);
    }
}


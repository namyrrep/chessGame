/*
 * Both
 * Move.java
 * Represents a single chess move (from, to, piece moved, captured piece, etc.).
 * Used for move history, undo/redo functionality, and move validation.
 */
public class Move
{
    //All initialized fields
    Piece piece;
    int row;
    int col;

    //Sets the piece being moved (holds new position) and stores old position
    public Move(Piece movedPiece, int previousRow, int previousCol)
    {
        piece = movedPiece;
        row = previousRow;
        col = previousCol;
    }

    //This is the getter method for row
    public int getRow()
    {
        return row;
    }

    //This is the getter method for col
    public int getCol()
    {
        return col;
    }
}

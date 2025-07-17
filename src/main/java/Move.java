/**
 * Represents a chess move with a piece and its previous position.
 * Used for tracking moves in the game history.
 */
public class Move
{
    private int row;
    private int col;
    private Piece movedPiece;
    
    /**
     * Creates a new move record.
     * 
     * @param movedPiece The piece that was moved
     * @param previousRow The row the piece moved from
     * @param previousCol The column the piece moved from
     */
    public Move(Piece movedPiece, int previousRow, int previousCol)
    {
        this.movedPiece = movedPiece;
        this.row = previousRow;
        this.col = previousCol;
    }
    
    /**
     * Gets the original row the piece moved from.
     * 
     * @return The source row
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Gets the original column the piece moved from.
     * 
     * @return The source column
     */
    public int getCol()
    {
        return col;
    }
}

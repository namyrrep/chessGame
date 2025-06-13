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
        //Initialize variables
        int[] currentPosition = {this.getX(), this.getY()};
        int[][] possibleMoves;
        int possibleMovesIndex = 0;
        int changingX = currentPosition[0];
        int changingY = currentPosition[1];

        //Adds possible moves in the left and up direction
        while(changingX > 0 && changingY > 0)
        {
            changingX++;
            changingY++;

            //Checks if the Piece at position is the same color.

            //Checks if the Piece at position is the oposite color.

            //If there is nothing at position
            
        }
    }
}
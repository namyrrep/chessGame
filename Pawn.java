import java.util.ArrayList;
/*
 * This is the Pawn class that stores all information about the Pawn Piece.
 */
public class Pawn extends Piece
{   
    //true if the pawn has not moved yet.
    boolean firstMove = true;
    boolean secondMove = false;

    //This is the constructor for the Pawn class.
    public Pawn(Player.PlayerColor pieceColor, int x, int y)
    {
        this.setColor(pieceColor);
        this.setX(x);
        this.setY(y);
    }
    
    //This is the getter method for firstMove.
    @Override
    public boolean getFirstMove()
    {
        return firstMove;
    }

    //This is the setter method for firstMove.
    public void setFirstMove(boolean newMove)
    {
        firstMove = newMove;
    }

    //This is the getter method for secondMove;
    @Override
    public boolean getSecondMove()
    {
        return secondMove;
    }

    //This is the setter method for secondMove.
    public void setSecondMove(boolean newMove)
    {
        secondMove = newMove;
    }

    //This is the overridden possibleMove method for the Pawn class.
    @Override
    public ArrayList<int[]> possibleMove(Board board)
    {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        
        //Allows the pawn to go forward 2 spaces if it has not moved yet, 1 space otherwise.
        if (firstMove)
        {
            if (this.getColor() == Player.PlayerColor.WHITE || (this.getColor() == Player.PlayerColor.BLACK && board.getFlip()))
            {
                possibleMoves = up(possibleMoves, 2, board);
            }
            else
            {
                possibleMoves = down(possibleMoves, 2, board);
            }
        }
        else
        {
            if (this.getColor() == Player.PlayerColor.WHITE || (this.getColor() == Player.PlayerColor.BLACK && board.getFlip()))
            {
                possibleMoves = up(possibleMoves, 1, board);
            }
            else
            {
                possibleMoves = down(possibleMoves, 1, board);
            }
        }
        
        //Checks if there is a piece it the Pawns take range upLeft, or upRight.
        //Also checks if enpassent applies
        if (this.getColor() == Player.PlayerColor.WHITE || (this.getColor() == Player.PlayerColor.BLACK && board.getFlip()))
        {
            if (board.getPiece((this.getX()-1), (this.getY()-1)).getColor() != this.getColor() || (board.getPiece((this.getX()-1), this.getY()).getColor() != this.getColor() 
                                                                            && this.getY() == 3 && board.getPiece((this.getX()-1), this.getY()).getSecondMove() == true))
            {
                possibleMoves = upLeft(possibleMoves, 1, xCordinate, yCordinate, board);
            }
            if (board.getPiece((this.getX()+1), (this.getY()-1)).getColor() != this.getColor() || (board.getPiece((this.getX()+1), this.getY()).getColor() != this.getColor() 
                                                                            && this.getY() == 3 && board.getPiece((this.getX()+1), this.getY()).getSecondMove() == true))
            {
                possibleMoves = upRight(possibleMoves, 1, xCordinate, yCordinate, board);
            }
        }
        else
        {
            if (board.getPiece((this.getX()-1), (this.getY()+1)).getColor() != this.getColor() || (board.getPiece((this.getX()-1), this.getY()).getColor() != this.getColor() 
                                                                            && this.getY() == 4 && board.getPiece((this.getX()-1), this.getY()).getSecondMove() == true))
            {
                possibleMoves = downLeft(possibleMoves, 1, xCordinate, yCordinate, board);
            }
            if (board.getPiece((this.getX()+1), (this.getY()+1)).getColor() != this.getColor() || (board.getPiece((this.getX()+1), this.getY()).getColor() != this.getColor() 
                                                                            && this.getY() == 4 && board.getPiece((this.getX()+1), this.getY()).getSecondMove() == true))
            {
                possibleMoves = downRight(possibleMoves, 1, xCordinate, yCordinate, board);
            }
        }
        return possibleMoves;
    }

    //This is the getSymbol method for the Pawn subclass.
    @Override
    public char getSymbol()
    {
        return 'P';
    }
}
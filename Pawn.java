import java.awt.Color;
import java.lang.classfile.attribute.BootstrapMethodsAttribute;
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
    public ArrayList<int[]> possibleMove()
    {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        
        //Allows the pawn to go forward 2 spaces if it has not moved yet, 1 space otherwise.
        if (firstMove)
        {
            up(possibleMoves, 2, board);
        }
        else
        {
            up(possibleMoves, 1, board);
        }
        
        //Checks if there is a piece it the Pawns take range upLeft, or upRight.
        //Also checks if enpassent applies
        if (board.getPiece((this.getX()-1), (this.getY()-1)) || (board.getPiece((this.getX()-1), this.getY()).getColor().equals(Player.PlayerColor.BLACK) 
                                                             && this.getY() == 3 && board.getPiece((this.getX()-1), this.getY()).getSecondMove().isTrue()))
        {
            upLeft(possibleMoves, 1, xCordinate, yCordinate, board);
        }
        if (board.getPiece((this.getX()+1), (this.getY()-1)) || (board.getPiece((this.getX()+1), this.getY()).getColor().equals(Player.PlayerColor.BLACK) 
                                                             && this.getY() == 3 && board.getPiece((this.getX()+1), this.getY()).getSecondMove().isTrue()))
        {
            upRight(possibleMoves, 1, xCordinate, yCordinate, board);
        }
    }
}
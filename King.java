import java.util.ArrayList;
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

    //This will be used to check if the castle option is a possible move.
    boolean firstMove = true;

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

    //This is the possibleMove method used for the King class.
    @Override
    public ArrayList<String> possibleMove(Board board)
    {
        ArrayList<String> possibleMoves = new ArrayList<>();

        //Adds all possibleMoves for the King to the returned ArrayList.
        possibleMoves = upLeft(possibleMoves, 1, xCordinate, yCordinate, board);
        possibleMoves = up(possibleMoves, 1, board);
        possibleMoves = upRight(possibleMoves, 1, xCordinate, yCordinate, board);
        possibleMoves = right(possibleMoves, 1, board);
        possibleMoves = downRight(possibleMoves, 1, xCordinate, yCordinate, board);
        possibleMoves = down(possibleMoves, 1, board);
        possibleMoves = downLeft(possibleMoves, 1, xCordinate, yCordinate, board);
        possibleMoves = left(possibleMoves, 1, board);

        //Checks for both castles on top side of board.
        if (yCordinate == 0)
        {
            if (firstMove)
            {
                //Queen side castle (left)
                if (board.getPiece(0, 0).getFirstMove())
                {
                    //Checks if there are any Pieces between King and Rook
                    if (board.getPiece(0, 1) == null && board.getPiece(0, 2) == null && board.getPiece(0, 3) == null)
                    {
                        //Adds possible move
                        possibleMoves.add("0-0-0");
                    }
                }
                //King side castle (right)
                if (board.getPiece(0, 7).getFirstMove())
                {
                    //Checks if there are any Pieces between King and Rook
                    if (board.getPiece(0, 5) == null && board.getPiece(0, 6) == null)
                    {
                        //Adds possible move
                        possibleMoves.add("0-0");
                    }
                }

            }
        }
        //Checks for both castles on bottom side of board.
        if (yCordinate == 7)
        {
            if (firstMove)
            {
                //Queen side castle (right)
                if (board.getPiece(7, 7).getFirstMove())
                {
                    //Checks if there are any Pieces between King and Rook
                    if (board.getPiece(7, 6) == null && board.getPiece(7, 5) == null && board.getPiece(7, 4) == null)
                    {
                        //Adds possible move
                        possibleMoves.add("0-0-0");
                    }
                }
                //King side castle (left)
                if (board.getPiece(7, 0).getFirstMove())
                {
                    //Checks if there are any Pieces between King and Rook
                    if (board.getPiece(7, 1) == null && board.getPiece(7, 2) == null)
                    {
                        //Adds possible move
                        possibleMoves.add("0-0");
                    }
                }
            }
        }

        return possibleMoves;
    }

    //This is the getSymbol method for the King subclass.
    @Override
    public char getSymbol()
    {
        return 'K';
    }
}
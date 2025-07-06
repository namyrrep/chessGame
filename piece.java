import java.util.ArrayList;
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
    //Initialize variables
    Player.PlayerColor color;
    int xCoordinate;
    int yCoordinate;

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
        xCoordinate = newX;
    }

    //This allows me to get the yCordinate of a Piece.
    public int getY()
    {
        return yCoordinate;
    }

    //This allows me to change the yCordinate of a Piece.
    public void setY(int newY)
    {
        yCoordinate = newY;
    }

    //Adds possible moves in the up and left direction to the possibleMoves ArrayList.
    public ArrayList<String> upLeft(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX > 0 && changingX > changingX - maxDistance) && (changingY > 0 && changingY > changingY - maxDistance))
        {
            //This will not affect the outside variables
            changingX--;+
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up and right direction to the possibleMoves ArrayList.
    public ArrayList<String> upRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX < 7 && changingX < changingX + maxDistance) && (changingY > 0 && changingY > changingY - maxDistance))
        {
            //This will not affect the outside variables
            changingX++;
            changingY--;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and right direction to the possibleMoves ArrayList.
    public ArrayList<String> downRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX < 7 && changingX < changingX + maxDistance) && (changingY < 7 && changingY < changingY + maxDistance))
        {
            //This will not affect the outside variables
            changingX++;
            changingY++;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and left direction to the possibleMoves ArrayList.
    public ArrayList<String> downLeft(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX > 0 && changingX > changingX - maxDistance) && (changingY < 7 && changingY < changingY + maxDistance))
        {
            //This will not affect the outside variables
            changingX--;
            changingY++;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the right direction to the possibleMoves ArrayList.
    public ArrayList<String> left(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = xCordinate;

        while(changingX > 0 && changingX > changingX - maxDistance)
        {
            //This will not affect the outside variables
            changingX--;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(yCordinate, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the right direction to the possibleMoves ArrayList.
    public ArrayList<String> right(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = xCordinate;

        while(changingX < 7 && changingX < changingX + maxDistance)
        {
            //This will not affect the outside variables
            changingX--;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(yCordinate, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up direction to the possibleMoves ArrayList.
    public ArrayList<String> up(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingY = yCordinate;

        while(changingY > 0 && changingY > changingY - maxDistance)
        {
            //This will not affect the outside variables
            changingY--;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, xCordinate);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down direction to the possibleMoves ArrayList.
    public ArrayList<String> down(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingY = yCordinate;

        while(changingY < 7 && changingY < changingY + maxDistance)
        {
            //This will not affect the outside variables
            changingY--;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color))
            {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, xCordinate);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //This method is overridden for classes that use the firstMove field.
    //Returns falso if not overridden (not used within that class).
    public boolean getFirstMove()
    {
        return false;
    }

    //This method is overridden for all subclasses of Piece. Returns an array of possible moves.
    public ArrayList<String> possibleMove(Board board)
    {
        return null;
    }

    //This is the getter method for the symbol field. It is overridden for all subclasses of Piece.
    public char getSymbol()
    {
        return ' ';
    }

    //This is the getter method for the second move (Only used in Pawn Class, false if not a Pawn))
    public boolean getSecondMove()
    {
        return false;
    }

     /**
     * Checks if moving this piece to (targetRow, targetCol) is valid according to chess rules.
     * This checks if the move is in the piece's possible moves.
     * @param targetRow The row to move to.
     * @param targetCol The column to move to.
     * @param board The current board state.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValidMove(int targetRow, int targetCol, Board board) {
        ArrayList<String> moves = this.possibleMove(board);
        for (char[] move : moves) {
            if (move[0] == targetRow && move[1] == targetCol) {
                return true;
            }
        }
        return false;
    }
}
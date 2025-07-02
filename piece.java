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

    //Adds possible moves in the up and left direction to the possibleMoves ArrayList.
    public ArrayList<int[]> upLeft(ArrayList<int[]> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX > 0 && changingX > changingX - maxDistance) && (changingY > 0 && changingY > changingY - maxDistance))
        {
            //This will not affect the outside variables
            changingX--;
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
                possibleMoves.add(new int[]{changingX, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, changingY});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up and right direction to the possibleMoves ArrayList.
    public ArrayList<int[]> upRight(ArrayList<int[]> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{changingX, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, changingY});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and right direction to the possibleMoves ArrayList.
    public ArrayList<int[]> downRight(ArrayList<int[]> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{changingX, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, changingY});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and left direction to the possibleMoves ArrayList.
    public ArrayList<int[]> downLeft(ArrayList<int[]> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{changingX, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, changingY});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the right direction to the possibleMoves ArrayList.
    public ArrayList<int[]> left(ArrayList<int[]> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{changingX, yCordinate});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, yCordinate});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the right direction to the possibleMoves ArrayList.
    public ArrayList<int[]> right(ArrayList<int[]> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{changingX, yCordinate});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{changingX, yCordinate});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up direction to the possibleMoves ArrayList.
    public ArrayList<int[]> up(ArrayList<int[]> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{xCordinate, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{xCordinate, changingY});
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down direction to the possibleMoves ArrayList.
    public ArrayList<int[]> down(ArrayList<int[]> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<int[]> possibleMoves = currentMoves;
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
                possibleMoves.add(new int[]{xCordinate, changingY});
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor().equals(this.getColor()))
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add(new int[]{xCordinate, changingY});
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
    public ArrayList<int[]> possibleMove(Board board)
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
}
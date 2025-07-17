import java.util.ArrayList;
/*
 * Edwin
 * Piece.java (Abstract)
 * Base class for all chess pieces.
 * Stores piece color and position.
 * Declares abstract method for getting possible moves.
 * Subclasses: King, Queen, Bishop, Knight, Rook, Pawn (each with their own movement logic).
 */

/**
 * Base class for all chess pieces.
 * Provides common functionality and properties for all chess pieces.
 * Contains methods for movement pattern calculation and piece identification.
 */
public class Piece
{
    //Initialize variables
    /** The color of this piece (WHITE or BLACK) */
    Player.PlayerColor color;
    
    /** The x-coordinate (column) of this piece on the board */
    int xCoordinate;
    
    /** The y-coordinate (row) of this piece on the board */
    int yCoordinate;

    /**
     * Changes the color of this piece.
     * 
     * @param newColor The new color to set
     */
    public void setColor(Player.PlayerColor newColor)
    {
        color = newColor;
    }

    /**
     * Gets the color of this piece.
     * 
     * @return The piece color (WHITE or BLACK)
     */
    public Player.PlayerColor getColor()
    {
        return color;
    }

    /**
     * Gets the x-coordinate (column) of this piece.
     * 
     * @return The x-coordinate (0-7)
     */
    public int getX()
    {
        return xCoordinate;
    }

    /**
     * Sets the x-coordinate (column) of this piece.
     * 
     * @param newX The new x-coordinate value
     */
    public void setX(int newX)
    {
        xCoordinate = newX;
    }

    /**
     * Gets the y-coordinate (row) of this piece.
     * 
     * @return The y-coordinate (0-7)
     */
    public int getY()
    {
        return yCoordinate;
    }

    /**
     * Sets the y-coordinate (row) of this piece.
     * 
     * @param newY The new y-coordinate value
     */
    public void setY(int newY)
    {
        yCoordinate = newY;
    }

    /**
     * Calculates possible moves in the up-left diagonal direction.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> upLeft(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX > 0 && changingX > changingX - maxDistance) && (changingY > 0 && changingY > changingY - maxDistance))
        {
            changingX--;
            changingY--;
            // Remove the recursive board.isChecked() call here to prevent stack overflow
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
            //If the Piece at position is the opposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    /**
     * Calculates possible moves in the up-right diagonal direction.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> upRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX < 7 && changingX < changingX + maxDistance) && (changingY > 0 && changingY > changingY - maxDistance))
        {
            changingX++;
            changingY--;
            // Remove the recursive check
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

    /**
     * Calculates possible moves in the down-right diagonal direction.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> downRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX < 7 && changingX < changingX + maxDistance) && (changingY < 7 && changingY < changingY + maxDistance))
        {
            changingX++;
            changingY++;
            // Remove the recursive check here
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

    /**
     * Calculates possible moves in the down-left diagonal direction.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> downLeft(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        while((changingX > 0 && changingX > changingX - maxDistance) && (changingY < 7 && changingY < changingY + maxDistance))
        {
            changingX--;
            changingY++;
            // Remove the recursive check here
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

    /**
     * Calculates possible moves to the left.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> left(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = xCoordinate;

        while(changingX > 0 && changingX > changingX - maxDistance)
        {
            //This will not affect the outside variables
            changingX--;
            // Remove the recursive check here
            Piece checkPiece = board.getPiece(yCoordinate, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - yCoordinate));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - yCoordinate));
            break;
        }
        return possibleMoves;
    }

    /**
     * Calculates possible moves to the right.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> right(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = xCoordinate;

        while(changingX < 7 && changingX < changingX + maxDistance)
        {
            //This will not affect the outside variables
            changingX++;
            // Remove the recursive check here
            Piece checkPiece = board.getPiece(yCoordinate, changingX);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - yCoordinate));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - yCoordinate));
            break;
        }
        return possibleMoves;
    }

    /**
     * Calculates possible moves upward.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> up(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingY = yCoordinate;

        while(changingY > 0 && changingY > changingY - maxDistance)
        {
            //This will not affect the outside variables
            changingY--;
            // Remove the recursive check here
            Piece checkPiece = board.getPiece(changingY, xCoordinate);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + xCoordinate) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + xCoordinate) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    /**
     * Calculates possible moves downward.
     * 
     * @param currentMoves List to add moves to
     * @param maxDistance Maximum distance to check
     * @param board The current board state
     * @return Updated list of possible moves
     */
    public ArrayList<String> down(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        //Sets the return List to the parameterized list, 
        //this allows me to add arrays to list outside the method. 
        ArrayList<String> possibleMoves = currentMoves;
        int changingY = yCoordinate;

        while(changingY < 7 && changingY < changingY + maxDistance)
        {
            //This will not affect the outside variables
            changingY++;
            // Remove the recursive check here
            Piece checkPiece = board.getPiece(changingY, xCoordinate);
            
            //If there is nothing at position
            if (checkPiece == null)
            {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + xCoordinate) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor())
            {
                break;
            }
            //If the Piece at position is the oposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + xCoordinate) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    /**
     * Checks if this piece has moved from its starting position.
     * Primarily used by King, Rook, and Pawn.
     * 
     * @return false by default, overridden in subclasses
     */
    public boolean getFirstMove()
    {
        return false;
    }

    /**
     * Gets all possible moves for this piece.
     * Each piece type implements its own movement logic.
     * 
     * @param board The current board state
     * @return List of possible moves in algebraic notation
     */
    public ArrayList<String> possibleMove(Board board) {
        return new ArrayList<>();
    }

    /**
     * Converts a character symbol to the appropriate case based on piece color.
     * 
     * @param baseSymbol The character symbol to convert
     * @return Uppercase for white pieces, lowercase for black pieces
     */
    protected char symbolForColor(char baseSymbol) {
        return (getColor() == Player.PlayerColor.WHITE) ? Character.toUpperCase(baseSymbol) : Character.toLowerCase(baseSymbol);
    }

    /**
     * Gets the character symbol representing this piece.
     * 
     * @return Character symbol for the piece
     */
    public char getSymbol()
    {
        return ' ';
    }

    /**
     * Checks if this is the second move of a pawn.
     * Only applicable for Pawn class.
     * 
     * @return false by default, overridden in Pawn class
     */
    public boolean getSecondMove()
    {
        return false;
    }

    /**
     * Checks if moving this piece to (targetRow, targetCol) is valid according to chess rules.
     * This checks if the move is in the piece's possible moves.
     * 
     * @param targetRow The row to move to
     * @param targetCol The column to move to
     * @param board The current board state
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int targetRow, int targetCol, Board board) {
        ArrayList<String> moves = this.possibleMove(board);
        for (String move : moves) {
            if (move.length() >= 5) {
                int toCol = move.charAt(move.length() - 2) - 'a';
                int toRow = '8' - move.charAt(move.length() - 1);
                // Fix: targetRow is the row (y), targetCol is the col (x)
                if (toRow == targetRow && toCol == targetCol) {
                    return true;
                }
            }
        }
        return false;
    }
}

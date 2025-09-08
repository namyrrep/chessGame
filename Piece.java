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

    //This allows me to get the xCoordinate of a Piece.
    public int getX()
    {
        return xCoordinate;
    }

    //This allows me to change the xCoordinate of a Piece.
    public void setX(int newX)
    {
        xCoordinate = newX;
    }

    //This allows me to get the yCoordinate of a Piece.
    public int getY()
    {
        return yCoordinate;
    }

    //This allows me to change the yCoordinate of a Piece.
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

        for (int i = 1; i <= maxDistance; i++) {
            changingX = x - i;
            changingY = y - i;
            
            if (changingX < 0 || changingY < 0) break;
            
            //Runs isChecked() in case the King is already in check,
            //or if the current move would put the King in check. 
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            //If there is nothing at position
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            //Checks if the Piece at position is the same color.
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            //If the Piece at position is the opposite color.
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up and right direction to the possibleMoves ArrayList.
    public ArrayList<String> upRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        for (int i = 1; i <= maxDistance; i++) {
            changingX = x + i;
            changingY = y - i;
            
            if (changingX > 7 || changingY < 0) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and right direction to the possibleMoves ArrayList.
    public ArrayList<String> downRight(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        for (int i = 1; i <= maxDistance; i++) {
            changingX = x + i;
            changingY = y + i;
            
            if (changingX > 7 || changingY > 7) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down and left direction to the possibleMoves ArrayList.
    public ArrayList<String> downLeft(ArrayList<String> currentMoves, int maxDistance, int x, int y, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;
        int changingX = x;
        int changingY = y;

        for (int i = 1; i <= maxDistance; i++) {
            changingX = x - i;
            changingY = y + i;
            
            if (changingX < 0 || changingY > 7) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, changingX);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - changingY));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the left direction to the possibleMoves ArrayList.
    public ArrayList<String> left(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;

        for (int i = 1; i <= maxDistance; i++) {
            int changingX = xCoordinate - i;
            
            if (changingX < 0) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(yCoordinate, changingX);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - yCoordinate));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - yCoordinate));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the right direction to the possibleMoves ArrayList.
    public ArrayList<String> right(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;

        for (int i = 1; i <= maxDistance; i++) {
            int changingX = xCoordinate + i;
            
            if (changingX > 7) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(yCoordinate, changingX);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + changingX) + (char)('8' - yCoordinate));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + changingX) + (char)('8' - yCoordinate));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the up direction to the possibleMoves ArrayList.
    public ArrayList<String> up(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;

        for (int i = 1; i <= maxDistance; i++) {
            int changingY = yCoordinate - i;
            
            if (changingY < 0) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, xCoordinate);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + xCoordinate) + (char)('8' - changingY));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + xCoordinate) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //Adds possible moves in the down direction to the possibleMoves ArrayList.
    public ArrayList<String> down(ArrayList<String> currentMoves, int maxDistance, Board board)
    {
        ArrayList<String> possibleMoves = currentMoves;

        for (int i = 1; i <= maxDistance; i++) {
            int changingY = yCoordinate + i;
            
            if (changingY > 7) break;
            
            if (board.isChecked(color)) {
                continue;
            }

            Piece checkPiece = board.getPiece(changingY, xCoordinate);
            
            if (checkPiece == null) {
                possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + (char)('a' + xCoordinate) + (char)('8' - changingY));
                continue;
            }
            else if (checkPiece.getColor() == this.getColor()) {
                break;
            }
            possibleMoves.add("" + this.getSymbol() + (char)('a' + xCoordinate) + (char)('8' - yCoordinate) + 'x' + (char)('a' + xCoordinate) + (char)('8' - changingY));
            break;
        }
        return possibleMoves;
    }

    //This method is overridden for classes that use the firstMove field.
    //Returns false if not overridden (not used within that class).
    public boolean getFirstMove()
    {
        return false;
    }

    //This method is overridden for all subclasses of Piece. Returns an array of possible moves.
    public ArrayList<String> possibleMove(Board board) {
        return new ArrayList<>();
    }

    // Helper to return correct symbol for color
    protected char symbolForColor(char baseSymbol) {
        return (getColor() == Player.PlayerColor.WHITE) ? Character.toUpperCase(baseSymbol) : Character.toLowerCase(baseSymbol);
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


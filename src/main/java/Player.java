/**
 * Represents a chess player.
 * Tracks player attributes like color, name, and turn status.
 */
public class Player {
    private PlayerColor color;
    private String name;
    private boolean isTurn;
    
    /**
     * Enum representing the two player colors in chess.
     */
    public enum PlayerColor {
        /** White player color */
        WHITE,
        /** Black player color */
        BLACK
    }
    
    /**
     * Creates a new player with the specified color and name.
     * 
     * @param color The player's color (WHITE or BLACK)
     * @param name The player's name
     */
    public Player(PlayerColor color, String name) {
        this.color = color;
        this.name = name;
        this.isTurn = false;
    }
    
    /**
     * Sets the player's color.
     * 
     * @param color The new color to set
     */
    public void setColor(PlayerColor color) { this.color = color; }
    
    /**
     * Gets the player's color.
     * 
     * @return The player's color (WHITE or BLACK)
     */
    public PlayerColor getColor() { return color; }
    
    /**
     * Checks if it's this player's turn.
     * 
     * @return true if it's this player's turn
     */
    public boolean isTurn() { return isTurn; }
    
    /**
     * Sets whether it's this player's turn.
     * 
     * @param isTurn true to set as the current player, false otherwise
     */
    public void setTurn(boolean isTurn) { this.isTurn = isTurn; }
    
    /**
     * Gets the player's name.
     * 
     * @return The player's name
     */
    public String getName() { return name; }
    
    /**
     * Sets the player's name.
     * 
     * @param name The new name to set
     */
    public void setName(String name) { this.name = name; }
}
                "color=" + color +
                ", isTurn=" + isTurn +
                // ", activePieces=" + activePieces.size() +
                // ", capturedPieces=" + capturedPieces.size() +
                ", name='" + name + '\'' +
                '}';
    }


 }

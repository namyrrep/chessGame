/*
 * Will
 * Player.java
 * Represents a player in the game.
 * Stores player color (enum), turn status, and references to their pieces and captured pieces.
 * (Optional) Can include player profile information.
 */
    // import java.util.ArrayList;
    // import java.util.List;
    // import java.io.BufferedReader;
    // import java.io.FileReader;
    // import java.io.IOException;


 public class Player {
    //Check to see if there is a text file in a folder called "players.txt"
    // If there is, read the file and create a player object for each line in the file
    // If there isn't, create a player object with default values
    // If there is a player object already created, use that object instead of creating a new one

    // public static final String PLAYERS_FILE = "players.txt"; // File to store player data
    // public static final String DEFAULT_PLAYER_NAME = "Player"; // Default player name if not specified


    //Enumerator for player color, basically just a way to keep track of which player is which
    public enum PlayerColor {
        WHITE, BLACK
    }
    
    private PlayerColor color; // Player's color
    private boolean isTurn; // Indicates if it's this player's turn
    // private List<Piece> activePieces;      // Pieces currently on the board
    // private List<Piece> capturedPieces;    // Pieces this player has captured
    private String name;                   // (Optional) Player name
    public Player(PlayerColor color, String name) {
        this.color = color;
        this.isTurn = false;
        // this.activePieces = new ArrayList<>();
        // this.capturedPieces = new ArrayList<>();
        this.name = name;
    }

    // Static method to load players from a file

    
    public PlayerColor getColor() { return color; }
    public void setColor(PlayerColor color) { this.color = color; }

    public boolean isTurn() { return isTurn; }
    public void setTurn(boolean isTurn) { this.isTurn = isTurn; }

    // public List<Piece> getActivePieces() { return activePieces; }
    // public List<Piece> getCapturedPieces() { return capturedPieces; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // public void addActivePiece(Piece piece) { activePieces.add(piece); }
    // public void removeActivePiece(Piece piece) { activePieces.remove(piece); }
    // public void addCapturedPiece(Piece piece) { capturedPieces.add(piece); }

    // // Example: Get all possible moves for this player
    // public List<Move> getAllPossibleMoves(Board board) {
    //     List<Move> moves = new ArrayList<>();
    //     for (Piece piece : activePieces) {
    //         moves.addAll(piece.getPossibleMoves(board));
    //     }
    //     return moves;
    // }
    // Overrides toString method and prints out
    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                ", isTurn=" + isTurn +
                // ", activePieces=" + activePieces.size() +
                // ", capturedPieces=" + capturedPieces.size() +
                ", name='" + name + '\'' +
                '}';
    }


 }

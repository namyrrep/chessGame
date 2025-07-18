package com.chess.game;

/**
 * Launcher class for the Chess Game application.
 * This is a simple wrapper around the main entry points to help with module system.
 */
public class ChessGameLauncher {
    /**
     * Main method for launching the GUI version of the chess game.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        ChessApp.main(args);
    }
    
    /**
     * Method for launching the terminal version of the chess game.
     * 
     * @param args Command line arguments
     */
    public static void runTerminal(String[] args) {
        Main.main(args);
    }
}

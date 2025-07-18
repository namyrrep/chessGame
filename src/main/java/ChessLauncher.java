/**
 * A simple launcher class for the Chess application.
 * This class helps with launching the application in both terminal and GUI modes.
 */
public class ChessLauncher {
    
    /**
     * Main method that determines which mode to launch.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-gui")) {
            launchGui(args);
        } else {
            launchTerminal(args);
        }
    }
    
    /**
     * Launches the GUI version of the chess game.
     * 
     * @param args Command line arguments
     */
    private static void launchGui(String[] args) {
        try {
            System.out.println("Launching GUI version...");
            ChessApp.main(args);
        } catch (Exception e) {
            System.err.println("Failed to launch GUI: " + e.getMessage());
            e.printStackTrace();
            System.err.println("Falling back to terminal mode...");
            launchTerminal(args);
        }
    }
    
    /**
     * Launches the terminal version of the chess game.
     * 
     * @param args Command line arguments
     */
    private static void launchTerminal(String[] args) {
        System.out.println("Launching terminal version...");
        Main.main(args);
    }
}

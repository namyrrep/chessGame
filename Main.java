/*
 * Both People
 * Main.java
 * Entry point for the chess game application.
 * Launches the JavaFX application and starts the game.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize players (could prompt for names or use defaults)
        Player player1 = new Player(Player.PlayerColor.WHITE, "Player 1");
        Player player2 = new Player(Player.PlayerColor.BLACK, "Player 2");

        // Initialize game controller
        GameController controller = new GameController(player1, player2);

        // Initialize and show the display, passing the controller and stage
        chessDisplay display = new chessDisplay(controller, primaryStage);
        // chessDisplay should set up the scene and show the stage
    }

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}
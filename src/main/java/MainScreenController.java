import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the main screen where players select game options.
 */
public class MainScreenController {

    @FXML
    private RadioButton sandboxRadio;
    
    @FXML
    private RadioButton aiRadio;
    
    private Stage primaryStage;
    
    /**
     * Sets the primary stage for this controller.
     * 
     * @param primaryStage The primary stage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    /**
     * Handles the "New Game" button click event.
     * 
     * @param event The action event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        try {
            boolean vsAI = aiRadio.isSelected();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChessBoard.fxml"));
            Parent root = loader.load();
            
            ChessBoardController controller = loader.getController();
            controller.initializeGame(vsAI);
            
            Scene scene = new Scene(root);
            primaryStage.setTitle("Chess Game");
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handles the "Play in Terminal" button click event.
     * Launches the terminal version of the game.
     * 
     * @param event The action event
     */
    @FXML
    private void handleTerminal(ActionEvent event) {
        boolean vsAI = aiRadio.isSelected();
        primaryStage.close();
        
        // Launch the terminal version in a separate thread
        new Thread(() -> {
            String[] args = vsAI ? new String[]{"ai"} : new String[]{"sandbox"};
            Main.main(args);
        }).start();
    }
    
    /**
     * Handles the "Exit" button click event.
     * 
     * @param event The action event
     */
    @FXML
    private void handleExit(ActionEvent event) {
        primaryStage.close();
    }
}

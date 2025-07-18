import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX Application class for the Chess Game.
 * This is the entry point for the GUI version of the application.
 */
public class ChessApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Try to load the FXML from the expected location
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
            if (loader.getLocation() == null) {
                // If not found, try alternative locations
                loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                if (loader.getLocation() == null) {
                    loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
                }
            }
            
            if (loader.getLocation() == null) {
                throw new Exception("Could not find FXML file. Please ensure it's in the correct location.");
            }
            
            Parent root = loader.load();
            
            MainScreenController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            
            Scene scene = new Scene(root);
            primaryStage.setTitle("Chess Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error starting JavaFX application: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Main method that launches the JavaFX application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

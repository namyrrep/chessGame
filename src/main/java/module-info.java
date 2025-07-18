module chessGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    // Since all our classes are in the default package
    exports to javafx.fxml;
    opens to javafx.graphics, javafx.fxml;
}
    opens com.chess.game to javafx.fxml;
    opens com.chess.game.controller to javafx.fxml;
    
    // Also allow direct access from default package
    opens com.chess.game.view to javafx.fxml;
    opens com.chess.game.model to javafx.fxml;
    
    // Since we're using the default package, we need to open it
    opens . to javafx.fxml;
    exports .;
}

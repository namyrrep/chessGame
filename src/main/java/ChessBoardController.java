import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the chess board UI.
 * Handles the game interaction through the GUI.
 */
public class ChessBoardController {

    @FXML
    private GridPane boardGrid;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private TextArea moveHistoryText;
    
    @FXML
    private CheckBox autoFlipCheckbox;
    
    private GameController gameController;
    private StackPane[][] squares = new StackPane[8][8];
    private StackPane selectedSquare = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    
    /**
     * Initializes the game with the selected mode.
     * 
     * @param vsAI True if playing against AI, false for sandbox mode
     */
    public void initializeGame(boolean vsAI) {
        Player player1 = new Player(Player.PlayerColor.WHITE, "White");
        Player player2 = new Player(Player.PlayerColor.BLACK, "Black");
        gameController = new GameController(player1, player2);
        
        createChessBoard();
        updateBoard();
        updateStatus();
        
        if (vsAI) {
            // Setup AI player
            SimpleAI ai = new SimpleAI(player2);
            
            // If AI moves, we need to update the board
            gameController.setAIPlayer(ai);
        }
    }
    
    /**
     * Creates the visual chess board with squares and piece symbols.
     */
    private void createChessBoard() {
        boardGrid.getChildren().clear();
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane square = createSquare(row, col);
                squares[row][col] = square;
                boardGrid.add(square, col, row);
                
                final int r = row;
                final int c = col;
                square.setOnMouseClicked(e -> handleSquareClick(r, c, e));
            }
        }
        
        // Add row and column labels
        for (int i = 0; i < 8; i++) {
            Label rankLabel = new Label(String.valueOf(8 - i));
            rankLabel.setFont(new Font(14));
            boardGrid.add(rankLabel, 8, i);
            
            Label fileLabel = new Label(String.valueOf((char)('a' + i)));
            fileLabel.setFont(new Font(14));
            boardGrid.add(fileLabel, i, 8);
        }
    }
    
    /**
     * Creates a single square for the chess board.
     * 
     * @param row The row index
     * @param col The column index
     * @return A StackPane representing the square
     */
    private StackPane createSquare(int row, int col) {
        StackPane square = new StackPane();
        Rectangle bg = new Rectangle(60, 60);
        
        // Set alternating colors for the checkerboard pattern
        bg.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.SADDLEBROWN);
        
        square.getChildren().add(bg);
        return square;
    }
    
    /**
     * Updates the entire board based on the current game state.
     */
    private void updateBoard() {
        Board board = gameController.getBoard();
        boolean isFlipped = board.getFlip();
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int displayRow = isFlipped ? 7 - row : row;
                int displayCol = isFlipped ? 7 - col : col;
                
                Piece piece = board.getPiece(row, col);
                StackPane square = squares[displayRow][displayCol];
                
                // Remove any existing piece symbol
                if (square.getChildren().size() > 1) {
                    square.getChildren().remove(1);
                }
                
                // Add piece symbol if there's a piece on this square
                if (piece != null) {
                    Text pieceText = new Text(String.valueOf(piece.getSymbol()));
                    pieceText.setFont(new Font(32));
                    
                    // Set color based on piece's color
                    pieceText.setFill(piece.getColor() == Player.PlayerColor.WHITE ? 
                                     Color.WHITE : Color.BLACK);
                    pieceText.setStroke(piece.getColor() == Player.PlayerColor.WHITE ? 
                                       Color.BLACK : Color.WHITE);
                    pieceText.setStrokeWidth(0.5);
                    
                    square.getChildren().add(pieceText);
                }
            }
        }
        
        // Update move history
        moveHistoryText.setText(formatMoveHistory(board.getMoveHistory()));
    }
    
    /**
     * Formats the move history for display.
     * 
     * @param moves List of moves in algebraic notation
     * @return Formatted string of moves
     */
    private String formatMoveHistory(ArrayList<String> moves) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                sb.append((i/2 + 1)).append(". ");
            }
            sb.append(moves.get(i)).append(" ");
            if (i % 2 == 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * Updates the status label with the current player's turn.
     */
    private void updateStatus() {
        Player current = gameController.getCurrentPlayer();
        statusLabel.setText(current.getName() + "'s turn");
    }
    
    /**
     * Handles a click on a chess square.
     * 
     * @param row The row of the clicked square
     * @param col The column of the clicked square
     * @param event The mouse event
     */
    private void handleSquareClick(int row, int col, MouseEvent event) {
        // Fix the implementation to handle null pieces properly
        try {
            Board board = gameController.getBoard();
            boolean isFlipped = board.getFlip();
            
            // Convert display coordinates to board coordinates
            int boardRow = isFlipped ? 7 - row : row;
            int boardCol = isFlipped ? 7 - col : col;
            
            // If no square is selected yet
            if (selectedSquare == null) {
                Piece piece = board.getPiece(boardRow, boardCol);
                
                // Check if the clicked square has a piece of the current player
                if (piece != null && piece.getColor() == gameController.getCurrentPlayer().getColor()) {
                    // Highlight the selected square
                    selectedSquare = squares[row][col];
                    Rectangle bg = (Rectangle) selectedSquare.getChildren().get(0);
                    bg.setStroke(Color.BLUE);
                    bg.setStrokeWidth(3);
                    
                    selectedRow = boardRow;
                    selectedCol = boardCol;
                    
                    // Show possible moves
                    ArrayList<String> moves = piece.possibleMove(board);
                    messageLabel.setText("Selected " + piece.getSymbol() + " at " + 
                                        (char)('a' + boardCol) + (8 - boardRow));
                }
            } else {
                // A square is already selected, try to move
                Rectangle bg = (Rectangle) selectedSquare.getChildren().get(0);
                bg.setStroke(null);
                
                // Try to make the move
                if (gameController.makeMove(selectedRow, selectedCol, boardRow, boardCol)) {
                    messageLabel.setText("Moved from " + (char)('a' + selectedCol) + (8 - selectedRow) + 
                                        " to " + (char)('a' + boardCol) + (8 - boardRow));
                    
                    // Auto-flip if enabled
                    if (autoFlipCheckbox.isSelected()) {
                        gameController.getBoard().setOrientationForPlayer(gameController.getCurrentPlayer().getColor());
                    }
                    
                    // If playing against AI, make the AI move
                    if (gameController.hasAI() && 
                        gameController.getCurrentPlayer().getColor() == Player.PlayerColor.BLACK) {
                        makeAIMove();
                    }
                } else {
                    messageLabel.setText("Invalid move. Try again.");
                }
                
                // Update the board and reset selection
                updateBoard();
                updateStatus();
                selectedSquare = null;
                selectedRow = -1;
                selectedCol = -1;
                
                // Check for game over
                checkGameOver();
            }
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Makes the AI move.
     */
    private void makeAIMove() {
        new Thread(() -> {
            try {
                // Add a small delay to make the AI move visible
                Thread.sleep(500);
                
                int[] move = gameController.makeAIMove();
                
                if (move != null) {
                    final String moveText = "AI moved from " + (char)('a' + move[1]) + (8 - move[0]) +
                                          " to " + (char)('a' + move[3]) + (8 - move[2]);
                    
                    Platform.runLater(() -> {
                        updateBoard();
                        updateStatus();
                        messageLabel.setText(moveText);
                        
                        // Auto-flip if enabled
                        if (autoFlipCheckbox.isSelected()) {
                            gameController.getBoard().setOrientationForPlayer(gameController.getCurrentPlayer().getColor());
                            updateBoard();
                        }
                        
                        // Check for game over
                        checkGameOver();
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    /**
     * Checks if the game is over (king captured).
     */
    private void checkGameOver() {
        if (!gameController.getBoard().hasKing(Player.PlayerColor.WHITE)) {
            messageLabel.setText("Game over: Black wins!");
            disableBoard();
        } else if (!gameController.getBoard().hasKing(Player.PlayerColor.BLACK)) {
            messageLabel.setText("Game over: White wins!");
            disableBoard();
        }
    }
    
    /**
     * Disables interaction with the board at the end of the game.
     */
    private void disableBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final StackPane square = squares[row][col];
                square.setOnMouseClicked(null);
            }
        }
    }
    
    /**
     * Handles the "Undo Move" button click event.
     * 
     * @param event The action event
     */
    @FXML
    private void handleUndo(ActionEvent event) {
        if (gameController.undoLastMove()) {
            messageLabel.setText("Move undone.");
            
            // If playing against AI and it's now the AI's turn, undo the human move too
            if (gameController.hasAI() && 
                gameController.getCurrentPlayer().getColor() == Player.PlayerColor.BLACK) {
                gameController.undoLastMove();
            }
            
            // Auto-flip if enabled
            if (autoFlipCheckbox.isSelected()) {
                gameController.getBoard().setOrientationForPlayer(gameController.getCurrentPlayer().getColor());
            }
            
            updateBoard();
            updateStatus();
            selectedSquare = null;
            selectedRow = -1;
            selectedCol = -1;
        } else {
            messageLabel.setText("No moves to undo.");
        }
    }
    
    /**
     * Handles the "Flip Board" button click event.
     * 
     * @param event The action event
     */
    @FXML
    private void handleFlipBoard(ActionEvent event) {
        gameController.getBoard().flipped();
        updateBoard();
        messageLabel.setText("Board flipped.");
    }
    
    /**
     * Handles the "Exit Game" button click event.
     * 
     * @param event The action event
     */
    @FXML
    private void handleExitGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) boardGrid.getScene().getWindow();
            
            MainScreenController controller = loader.getController();
            controller.setPrimaryStage(stage);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

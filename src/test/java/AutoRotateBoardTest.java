import org.junit.Test;
import static org.junit.Assert.*;

public class AutoRotateBoardTest {

    @Test
    public void testBoardAutoRotation() {
        // Create players and controller
        Player white = new Player(Player.PlayerColor.WHITE, "White");
        Player black = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(white, black);
        
        // Board should start with white at bottom (not flipped)
        assertFalse("Board should start with white at bottom", controller.getBoard().getFlip());
        
        // Make a move as white
        controller.makeMove(6, 4, 4, 4); // e2 to e4
        
        // Now it's black's turn, board should be flipped
        assertTrue("Board should be flipped for black's turn", controller.getBoard().getFlip());
        
        // Make a move as black
        controller.makeMove(1, 4, 3, 4); // e7 to e5
        
        // Now it's white's turn again, board should not be flipped
        assertFalse("Board should not be flipped for white's turn", controller.getBoard().getFlip());
        
        // Test the turn counter
        assertEquals("Turn counter should be 2 after two moves", 2, controller.getTurnCounter());
    }
    
    @Test
    public void testUndoWithAutoRotation() {
        // Create players and controller
        Player white = new Player(Player.PlayerColor.WHITE, "White");
        Player black = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(white, black);
        
        // Make two moves
        controller.makeMove(6, 4, 4, 4); // e2 to e4
        controller.makeMove(1, 4, 3, 4); // e7 to e5
        
        // Board should not be flipped (white's turn)
        assertFalse("Board should not be flipped for white's turn", controller.getBoard().getFlip());
        
        // Undo last move (black's move)
        controller.undoLastMove();
        
        // Now it should be black's turn, board should be flipped
        assertTrue("Board should be flipped after undo to black's turn", controller.getBoard().getFlip());
        assertEquals("Current player should be black after undo", Player.PlayerColor.BLACK, 
                    controller.getCurrentPlayer().getColor());
    }
}

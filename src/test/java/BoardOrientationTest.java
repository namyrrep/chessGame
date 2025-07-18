import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the board orientation functionality.
 * Verifies that the board correctly handles flipping and displays proper orientations.
 */
public class BoardOrientationTest {
    
    @Test
    public void testBoardFlipping() {
        Board board = new Board();
        assertFalse("Board should start unflipped", board.getFlip());
        
        board.flipped();
        assertTrue("Board should be flipped after calling flipped()", board.getFlip());
        
        board.flipped();
        assertFalse("Board should be unflipped after calling flipped() again", board.getFlip());
    }
    
    @Test
    public void testPlayerOrientation() {
        Board board = new Board();
        board.setOrientationForPlayer(Player.PlayerColor.WHITE);
        assertFalse("Board should be unflipped for WHITE", board.getFlip());
        
        board.setOrientationForPlayer(Player.PlayerColor.BLACK);
        assertTrue("Board should be flipped for BLACK", board.getFlip());
    }
    
    @Test
    public void testOrientationDescription() {
        Board board = new Board();
        assertTrue("White's perspective description should mention white", 
                   board.getOrientationDescription().toLowerCase().contains("white"));
        
        board.flipped();
        assertTrue("Black's perspective description should mention black", 
                   board.getOrientationDescription().toLowerCase().contains("black"));
    }
}

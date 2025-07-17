import org.junit.Test;
import static org.junit.Assert.*;

public class BoardFlipTest {
    
    @Test
    public void testBoardFlipping() {
        Board board = new Board();
        
        // Get reference to a piece before flipping
        Piece whiteRook = board.getPiece(7, 0); // a1
        assertEquals('R', whiteRook.getSymbol());
        assertEquals(7, whiteRook.getY());
        assertEquals(0, whiteRook.getX());
        
        // Flip the board
        board.flipped();
        
        // After flipping, the rook should be at h8 (0,7)
        Piece flippedRook = board.getPiece(0, 7);
        assertEquals('R', flippedRook.getSymbol());
        assertEquals(0, flippedRook.getY());
        assertEquals(7, flippedRook.getX());
        
        // Make sure a1 is now empty
        assertNull(board.getPiece(7, 0));
        
        // Check that black rook is now at a1
        Piece blackRook = board.getPiece(7, 0);
        // The black rook should now be null since it moved
        assertNull(blackRook);
        
        // Flip the board back
        board.flipped();
        
        // Check that white rook is back at a1
        Piece originalRook = board.getPiece(7, 0);
        assertEquals('R', originalRook.getSymbol());
        assertEquals(7, originalRook.getY());
        assertEquals(0, originalRook.getX());
    }
    
    @Test
    public void testMoveAfterFlipping() {
        Board board = new Board();
        
        // Flip the board
        board.flipped();
        
        // Try moving a piece after flipping (black pawn from a2 to a3)
        boolean moved = board.movePiece(6, 0, 5, 0);
        assertTrue(moved);
        
        // Verify the pawn moved correctly
        assertNull(board.getPiece(6, 0));  
        assertNotNull(board.getPiece(5, 0));
        assertTrue(board.getPiece(5, 0) instanceof Pawn);
        
        // Flip the board back
        board.flipped();
        
        // Verify the pawn is now in the correct position after flipping back
        assertNull(board.getPiece(6, 7));
        assertNotNull(board.getPiece(2, 7));
        assertTrue(board.getPiece(2, 7) instanceof Pawn);
    }
    
    @Test
    public void testAutoFlipForPlayer() {
        Board board = new Board();
        
        // Test setting orientation for White player (should not flip)
        board.setOrientationForPlayer(Player.PlayerColor.WHITE);
        assertFalse("Board should not be flipped for white", board.getFlip());
        
        // White pieces should be at the bottom (rows 6-7)
        Piece whitePawn = board.getPiece(6, 0);
        assertEquals('P', whitePawn.getSymbol());
        
        // Test setting orientation for Black player (should flip)
        board.setOrientationForPlayer(Player.PlayerColor.BLACK);
        assertTrue("Board should be flipped for black", board.getFlip());
        
        // After flipping, black pieces should now be at the bottom
        Piece blackPawn = board.getPiece(1, 7); // Coordinate system is still unflipped internally
        assertEquals('p', blackPawn.getSymbol());
    }
    
    @Test
    public void testGameControllerFlipping() {
        Player white = new Player(Player.PlayerColor.WHITE, "White");
        Player black = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(white, black);
        
        // Should start with white player's view (unflipped)
        assertFalse("Board should start unflipped", controller.getBoard().getFlip());
        
        // Make a move to switch to black player
        controller.makeMove(6, 4, 4, 4); // e2 to e4
        
        // Board should now be flipped for black player
        assertTrue("Board should be flipped for black player's turn", controller.getBoard().getFlip());
        
        // Make another move to switch back to white
        controller.makeMove(1, 4, 3, 4); // e7 to e5
        
        // Board should be unflipped again for white
        assertFalse("Board should be unflipped for white player's turn", controller.getBoard().getFlip());
    }
}

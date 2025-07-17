import org.junit.Test;
import static org.junit.Assert.*;

public class EnPassantTest {

    @Test
    public void testEnPassantCapture() {
        Board board = new Board();
        
        // Setup test scenario: white pawn at e5, black pawn at d7
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.setPiece(i, j, null);
            }
        }
        
        Pawn whitePawn = new Pawn(Player.PlayerColor.WHITE, 4, 3); // e5
        Pawn blackPawn = new Pawn(Player.PlayerColor.BLACK, 3, 1); // d7
        
        board.setPiece(3, 4, whitePawn); // e5
        board.setPiece(1, 3, blackPawn); // d7
        
        // Move black pawn two squares forward (triggering en passant condition)
        board.movePiece(1, 3, 3, 3); // d7 to d5
        
        // Check if en passant capture is available to white pawn
        assertTrue("En passant should be available", whitePawn.isValidMove(2, 3, board));
        
        // Execute en passant capture
        board.movePiece(3, 4, 2, 3); // e5 captures d6 (en passant)
        
        // Check that black pawn was captured
        assertNull("Black pawn should be captured", board.getPiece(3, 3));
        assertNotNull("White pawn should be at d6", board.getPiece(2, 3));
    }
    
    @Test
    public void testUndoMove() {
        Board board = new Board();
        Player white = new Player(Player.PlayerColor.WHITE, "White");
        Player black = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(white, black);
        
        // Make a move
        boolean moved = controller.makeMove(6, 4, 4, 4); // e2 to e4
        assertTrue("Pawn should move", moved);
        assertNull("e2 should be empty", controller.getBoard().getPiece(6, 4));
        assertNotNull("e4 should have pawn", controller.getBoard().getPiece(4, 4));
        
        // Undo the move
        boolean undone = controller.undoLastMove();
        assertTrue("Move should be undone", undone);
        assertNotNull("e2 should have pawn again", controller.getBoard().getPiece(6, 4));
        assertNull("e4 should be empty again", controller.getBoard().getPiece(4, 4));
    }
    
    @Test
    public void testEnPassantUndoMove() {
        Board board = new Board();
        
        // Setup a clean board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.setPiece(i, j, null);
            }
        }
        
        // Setup pawns for en passant
        Pawn whitePawn = new Pawn(Player.PlayerColor.WHITE, 4, 3); // e5
        Pawn blackPawn = new Pawn(Player.PlayerColor.BLACK, 3, 1); // d7
        
        board.setPiece(3, 4, whitePawn); // e5
        board.setPiece(1, 3, blackPawn); // d7
        
        // Move black pawn two squares forward
        board.movePiece(1, 3, 3, 3); // d7 to d5
        
        // Execute en passant capture
        board.movePiece(3, 4, 2, 3); // e5 captures d6 (en passant)
        
        // Undo the en passant capture
        board.undoMove();
        
        // Check that white pawn is back at e5
        assertNotNull("White pawn should be back at e5", board.getPiece(3, 4));
        assertEquals('P', board.getPiece(3, 4).getSymbol());
        
        // Check that black pawn is back at d5
        assertNotNull("Black pawn should be back at d5", board.getPiece(3, 3));
        assertEquals('p', board.getPiece(3, 3).getSymbol());
        
        // Undo the black pawn move
        board.undoMove();
        
        // Check that black pawn is now back at d7
        assertNotNull("Black pawn should be back at d7", board.getPiece(1, 3));
        assertEquals('p', board.getPiece(1, 3).getSymbol());
        assertNull("d5 should be empty", board.getPiece(3, 3));
    }
}

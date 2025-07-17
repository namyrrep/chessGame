import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ChessGameTest {

    @Test
    public void testBoardInitialization() {
        Board board = new Board();
        assertNotNull("a8 should have a black rook", board.getPiece(0,0));
        assertTrue("a7 should have a black pawn", board.getPiece(1,0) instanceof Pawn);
        assertTrue("e1 should have white queen", board.getPiece(7,4) instanceof Queen);
    }

    @Test
    public void testPawnInitialMoves() {
        Board board = new Board();
        Piece wPawn = board.getPiece(6,0);  // a2
        ArrayList<String> moves = wPawn.possibleMove(board);
        assertTrue(moves.contains("Pa2a3"));
        assertTrue(moves.contains("Pa2a4"));
        assertTrue(wPawn.isValidMove(5,0, board)); // a3
        assertTrue(wPawn.isValidMove(4,0, board)); // a4
    }

    @Test
    public void testKnightMoves() {
        Board board = new Board();
        Piece knight = board.getPiece(7,1); // b1
        ArrayList<String> moves = knight.possibleMove(board);
        assertTrue(moves.contains("Nb1a3"));
        assertTrue(moves.contains("Nb1c3"));
    }

    @Test
    public void testRookMoves() {
        Board board = new Board();
        board.setPiece(6,0, null);          // clear pawn at a2
        Piece rook = board.getPiece(7,0);   // a1
        ArrayList<String> moves = rook.possibleMove(board);
        assertTrue(moves.contains("Ra1a2"));
    }

    @Test
    public void testMakeMove() {
        Player white = new Player(Player.PlayerColor.WHITE, "W");
        Player black = new Player(Player.PlayerColor.BLACK, "B");
        GameController ctrl = new GameController(white, black);
        boolean moved = ctrl.makeMove(6,0, 4,0);
        assertTrue("Pawn should move two squares", moved);
        assertNull("a2 should be empty", ctrl.getBoard().getPiece(6,0));
        assertNotNull("a4 should have pawn", ctrl.getBoard().getPiece(4,0));
    }
}

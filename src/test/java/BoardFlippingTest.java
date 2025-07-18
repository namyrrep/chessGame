import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BoardFlippingTest {
    
    @Test
    public void testBoardFlipping() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            // Test normal orientation (white at bottom)
            Board board = new Board();
            board.printBoard();
            String normalOutput = outContent.toString();
            
            // Check that '8' appears at the top and '1' at the bottom
            assertTrue("Normal board should have '8' at the top", normalOutput.contains("8 |"));
            
            // Clear the output buffer
            outContent.reset();
            
            // Test flipped orientation (black at bottom)
            board.flipped();
            board.printBoard();
            String flippedOutput = outContent.toString();
            
            // Check that '1' appears at the top and '8' at the bottom
            assertTrue("Flipped board should have '1' at the top", flippedOutput.contains("1 |"));
            
            // Check file letters
            assertTrue("Normal board should have 'a' on the left", normalOutput.contains("    a"));
            assertTrue("Flipped board should have 'h' on the left", flippedOutput.contains("    h"));
            
        } finally {
            // Restore the standard output
            System.setOut(originalOut);
        }
    }
}

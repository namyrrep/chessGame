/*
 * Both People
 * Main.java
 * Entry point for the chess game application.
 * Launches the application and starts the game.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Thank you for playing Edwin Barrack and William Perryman's Chess game.");
        System.out.println("Please select a mode to start:");
        System.out.println("1. sandbox - Two players at the same location, switching turns.");
        System.out.println("2. AI      - Play against an AI of random strength (not yet implemented).");

        Scanner scanner = new Scanner(System.in);
        String mode = "";
        while (true) {
            System.out.print("Enter 'sandbox' or 'AI': ");
            mode = scanner.nextLine().trim().toLowerCase();
            if (mode.equals("sandbox") || mode.equals("ai")) {
                break;
            }
            System.out.println("Invalid option. Please enter 'sandbox' or 'AI'.");
        }

        Player player1 = new Player(Player.PlayerColor.WHITE, "White");
        Player player2 = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(player1, player2);
        
        // Initial orientation - white at bottom
        controller.getBoard().setOrientationForPlayer(Player.PlayerColor.WHITE);

        SimpleAI ai = null;
        boolean vsAI = mode.equals("ai");
        if (vsAI) {
            ai = new SimpleAI(player2); // AI is Black
        }

        // Board auto-rotation enabled by default
        boolean autoRotateBoard = true;
        
        while (true) {
            controller.getBoard().printBoard();
            Player current = controller.getCurrentPlayer();
            
            // Print whose turn it is and the current orientation
            System.out.println(current.getName() + "'s turn (" + current.getColor() + ")");
            System.out.println("Board orientation: " + 
                (controller.getBoard().getFlip() ? "Black at bottom" : "White at bottom"));

            if (vsAI && current.getColor() == Player.PlayerColor.BLACK) {
                // AI's turn
                int[] move = ai.chooseMove(controller.getBoard());
                if (move == null) {
                    System.out.println("AI has no legal moves. Game over.");
                    break;
                }
                System.out.println("AI moves from " + (char)('a' + move[1]) + (8 - move[0]) +
                                   " to " + (char)('a' + move[3]) + (8 - move[2]));
                controller.makeMove(move[0], move[1], move[2], move[3]);
            } else {
                // Human's turn
                String input = "";
                boolean helper = true;
                while (helper)
                {
                    System.out.print("Enter your move (e.g., e2e4), get possible moves (e.g., e2), 'flip', 'autoflip', 'undo', or 'exit': ");
                    input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("flip")) 
                    {
                        controller.getBoard().flipped();
                        System.out.println("Board has been manually flipped.");
                        controller.getBoard().printBoard();
                        continue;
                    }
                    
                    if (input.equalsIgnoreCase("autoflip")) 
                    {
                        autoRotateBoard = !autoRotateBoard;
                        System.out.println("Auto board rotation is now: " + 
                            (autoRotateBoard ? "ON" : "OFF"));
                        if (autoRotateBoard) {
                            // Immediately set orientation for current player
                            controller.getBoard().setOrientationForPlayer(current.getColor());
                            controller.getBoard().printBoard();
                        }
                        continue;
                    }
                    
                    if (input.equalsIgnoreCase("undo")) {
                        if (controller.undoLastMove()) {
                            System.out.println("Move undone.");
                            // Undo AI's move too in AI mode
                            if (vsAI && controller.getCurrentPlayer().getColor() == Player.PlayerColor.BLACK) {
                                controller.undoLastMove();
                            }
                            if (autoRotateBoard) {
                                controller.getBoard().setOrientationForPlayer(controller.getCurrentPlayer().getColor());
                            }
                        } else {
                            System.out.println("No moves to undo.");
                        }
                        break;
                    }

                    if (input.length() != 2)
                    {
                        break;
                    }

                    int possibleCol = input.charAt(0) - 'a';
                    int possibleRow = 8 - (input.charAt(1) - '0');

                    Piece selectedPiece = controller.getBoard().getPiece(possibleRow, possibleCol);
                    if (selectedPiece == null) {
                        System.out.println("No piece at that position.");
                        continue;
                    }

                    ArrayList<String> possibleMoves = selectedPiece.possibleMove(controller.getBoard());

                    if (possibleMoves.isEmpty())
                    {
                        System.out.println("No possible moves.");
                        continue;
                    }

                    for (int i = 0; i < possibleMoves.size(); i++)
                    {
                        System.out.println("" + possibleMoves.get(i));
                    }
                }

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Game exited.");
                    break;
                }

                if (input.length() != 4) {
                    System.out.println("Invalid input format. Try again.");
                    continue;
                }

                int fromCol = input.charAt(0) - 'a';
                int fromRow = 8 - (input.charAt(1) - '0');
                int toCol = input.charAt(2) - 'a';
                int toRow = 8 - (input.charAt(3) - '0');

                if (!controller.makeMove(fromRow, fromCol, toRow, toCol)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
                
                // If auto-rotation is off, restore the board orientation after the controller changes it
                if (!autoRotateBoard) {
                    // Reset the orientation to what the user prefers (default: white at bottom)
                    if (controller.getBoard().getFlip()) { 
                        controller.getBoard().flipped(); // Flip back to white at bottom
                    }
                }
            }

            // Check for king capture (simple end condition)
            if (!controller.getBoard().hasKing(Player.PlayerColor.WHITE)) {
                System.out.println("Black wins!");
                break;
            }
            if (!controller.getBoard().hasKing(Player.PlayerColor.BLACK)) {
                System.out.println("White wins!");
                break;
            }
        }
        scanner.close();
    }
}

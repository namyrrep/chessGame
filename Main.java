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
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           CHESS GAME                              ║");
        System.out.println("║              by Edwin Barrack and William Perryman                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Please select a game mode:");
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. SANDBOX - Two players, local multiplayer                     │");
        System.out.println("│ 2. AI      - Play against computer opponent                     │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");

        Scanner scanner = new Scanner(System.in);
        String mode = "";
        while (true) {
            System.out.print("\n» Enter 'sandbox' or 'AI': ");
            mode = scanner.nextLine().trim().toLowerCase();
            if (mode.equals("sandbox") || mode.equals("ai")) {
                break;
            }
            System.out.println("✗ Invalid option. Please enter 'sandbox' or 'AI'.");
        }

        if (mode.equals("ai")) {
            // Remove the exit so AI mode is playable
            // System.out.println("AI mode is not yet implemented. Exiting.");
            // scanner.close();
            // return;
        }

        Player player1 = new Player(Player.PlayerColor.WHITE, "White");
        Player player2 = new Player(Player.PlayerColor.BLACK, "Black");
        GameController controller = new GameController(player1, player2);

        SimpleAI ai = null;
        boolean vsAI = mode.equals("ai");
        if (vsAI) {
            ai = new SimpleAI(player2); // AI is Black
        }

        while (true) {
            System.out.println("\n" + "═".repeat(70));
            controller.getBoard().printBoard();
            System.out.println("═".repeat(70));
            
            Player current = controller.getCurrentPlayer();
            System.out.println("🎯 " + current.getName() + "'s turn (" + current.getColor() + ")");

            if (vsAI && current.getColor() == Player.PlayerColor.BLACK) {
                // AI's turn
                System.out.println("🤖 AI is thinking...");
                int[] move = ai.chooseMove(controller.getBoard());
                if (move == null) {
                    System.out.println("🏁 AI has no legal moves. Game over.");
                    break;
                }
                System.out.println("🤖 AI moves: " + (char)('a' + move[1]) + (8 - move[0]) +
                                   " → " + (char)('a' + move[3]) + (8 - move[2]));
                
                // Execute AI move
                if (!controller.makeMove(move[0], move[1], move[2], move[3])) {
                    System.out.println("AI attempted invalid move. Game error.");
                    break;
                }
            } else {
                // Human player's turn
                System.out.println("📝 Enter move (e.g., 'e2e4'), 'flip' to flip board, 'exit' to quit:");
                System.out.println("💡 Or enter piece position (e.g., 'e2') to see possible moves");
                System.out.print("» ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("flip")) {
                    controller.getBoard().flipped();
                    System.out.println("Board has been flipped.");
                    continue;
                }

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Game exited.");
                    break;
                }

                if (input.length() == 2) {
                    // Show possible moves for a piece
                    int possibleCol = input.charAt(0) - 'a';
                    int possibleRow = 8 - (input.charAt(1) - '0');

                    if (!controller.getBoard().isInBounds(possibleRow, possibleCol)) {
                        System.out.println("Invalid position.");
                        continue;
                    }

                    Piece piece = controller.getBoard().getPiece(possibleRow, possibleCol);
                    if (piece == null) {
                        System.out.println("No piece at that position.");
                        continue;
                    }

                    if (piece.getColor() != current.getColor()) {
                        System.out.println("That's not your piece.");
                        continue;
                    }

                    ArrayList<String> possibleMoves = piece.possibleMove(controller.getBoard());

                    if (possibleMoves.isEmpty()) {
                        System.out.println("No possible moves.");
                        continue;
                    }

                    System.out.println("Possible moves:");
                    for (String move : possibleMoves) {
                        System.out.println("  " + move);
                    }
                    continue;
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
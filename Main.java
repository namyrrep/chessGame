/*
 * Both People
 * Main.java
 * Entry point for the chess game application.
 * Launches the application and starts the game.
 */

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
            controller.getBoard().printBoard();
            Player current = controller.getCurrentPlayer();
            System.out.println(current.getName() + "'s turn (" + current.getColor() + ")");

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
                System.out.print("Enter your move (e.g., e2e4) or 'exit': ");
                String input = scanner.nextLine().trim();

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
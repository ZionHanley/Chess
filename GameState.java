
import ChessPieces.Pieces;
import java.io.IOException;

public class GameState {

    Board myObjBoard = new Board();
    Pieces activePiece;
    String command;
    boolean colorFlag = true;
    boolean highlightFlag = false;
    int[] selection;
    int[] move;

    public GameState() {
        myObjBoard.setBoard();
    }

    public void update() {

        clearScreen();

        if (highlightFlag) {
            myObjBoard.printHighlight(selection[0], selection[1], activePiece, colorFlag);
            highlightFlag = false;
        } else {
            printBoard(colorFlag);
        }

        System.out.print("Enter command | select | move | highlight | quit | ... "); command = System.console().readLine();

        switch (command.toLowerCase()) {

            case "highlight" -> {
                if (activePiece == null) {
                    System.err.println("No Piece selected");
                } else {
                    highlightFlag = true;
                }
                return;
            }

            case "move" -> {
                if (activePiece == null) {
                    System.err.println("No Piece selected");
                    return;
                } else {
                    while (true) {
                        System.out.print("Enter your move: "); // Use spaced out numbers for the format
                        getMoveInput();
                        if (myObjBoard.validateMove(selection, move, colorFlag, activePiece)) {
                            myObjBoard.movePiece(selection[0], selection[1], move[0], move[1]);
                            break; // Exit the loop if the move is valid
                        } else {
                            System.err.println("Invalid move. Please try again.");
                        }
                    }
                }
            }

            case "select" -> {
                System.out.print("Enter your selection: "); // Use spaced out numbers for the format
                getSelectInput();
                return;
            }

            case "quit" -> {
                System.out.print("Are you sure you want to exit? (y/n) ");
                String exitConfirm = System.console().readLine();
                if (exitConfirm.equalsIgnoreCase("y")) {
                    System.out.println("Exiting the game...");
                    System.exit(0);
                } else {
                    System.out.println("Exit canceled.");
                    return;
                }
            }

            default -> { 
                System.err.println("Invaliad command");
                return;
            }
        }

        switchTurns();
    }

    private void getSelectInput() { // Ranks are letters and Files are numbers
        while (true) {
            String moveTerminal = System.console().readLine();
            String[] parts = moveTerminal.split(" ");

            if (parts.length == 2) {
                try {
                    selection = new int[] { // Adjust for 0-based indexing
                        Character.toUpperCase(parts[0].charAt(0)) - 'A',
                        Integer.parseInt(parts[1]) - 1
                    };
                    activePiece = myObjBoard.getPiece(selection[0], selection[1]);
                    break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                }
            } else {
                System.out.println("Invalid input format.  Rank and File ie A 1.");
            }
        }
    }
    private void getMoveInput() {
        while (true) {
            String moveTerminal = System.console().readLine();
            String[] parts = moveTerminal.split(" ");

            if (parts.length == 2) {
                try {
                    move = new int[] {
                        Character.toUpperCase(parts[0].charAt(0)) - 'A',
                        Integer.parseInt(parts[1]) - 1
                    }; break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                }
            } else {
                System.out.println("Invalid input format. Rank and File ie A 1");
            }
        }
    }
    private void switchTurns() {
        colorFlag = !colorFlag;
    }
    private void clearScreen() { 
        try {
            new ProcessBuilder("clear")
            .inheritIO()
            .start()
            .waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printBoard(boolean colorFlag) {
        if (colorFlag) {
            myObjBoard.print(); System.out.println("White's turn");
        } else {
            myObjBoard.printReverse(); System.out.println("Black's turn");
        }
    }
}

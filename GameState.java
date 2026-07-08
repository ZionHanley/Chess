
import ChessPieces.Pieces;
import java.util.ArrayList;

public class GameState {
    Board myObjBoard = new Board();

    boolean colorFlag = true;
    String command;

    int[] selection;
    int[] move;

    Pieces activePiece;

    int num = Character.toUpperCase('a') - 64;

    @SuppressWarnings("unused")
    ArrayList<Pieces> WhiteCaptured = new ArrayList<>();
    @SuppressWarnings("unused")
    ArrayList<Pieces> BlackCaptured = new ArrayList<>();

    //Files are numbers and Ranks are letters.

    public GameState() {
        myObjBoard.setBoard(); // TODO: Figure out how to set the board with pieces in their starting positions.

    }

    //TODO: Make it so after a successful comand it clears the console and prints the successful message only untill a key is pressed

    public void update() {

        clearScreen(); // Clear the console for a fresh display.

        printBoard(colorFlag); // Print the board and indicate whose turn it is.

        System.out.print("Enter command | select | move | highlight | ... "); command = System.console().readLine();

        switch (command) {
            case "highlight" -> {
                
                if (activePiece == null) {
                    System.err.println("No Piece selected");
                } else {
                    highlightMoves(); // TODO: Implement logic to highlight possible moves for the selected piece.
                    System.out.println("Posibble moves shown");
                }
                return;
            }
            case "move" -> { // TODO: Add logic to check for check, checkmate, and draw conditions here.

                if (activePiece == null) {
                    System.err.println("No Piece selected");
                    return;
                } else {
                    // Need to implement check and checkmate detection
                    System.out.print("Enter your move: "); // Use spaced out numbers for the format
                    validateMove(); // Input -> checkMove() -> validateMove()
                }
            }
            case "select" -> { // TODO: Fix switching turns when selecting a piece. Currently, it switches turns even if the selection is invalid.
                System.out.print("Enter your selection: "); // Use spaced out numbers for the format
                getSelectInput();
                return;
            }
            default -> { 
                System.err.println("Invaliad command");
                return;
            }
        }

        switchTurns();
    }

    // TODO: decide if I should move all these methods to the board class

    public static void clearScreen() {  
        System.out.println();
    }

    private void getSelectInput() {
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

    private void checkMove() {

        while(true) {

            getMoveInput();

            // Check if the selected piece belongs to the current player
            if (myObjBoard.getPiece(selection[0],selection[1]) != null) {
                
                if (myObjBoard.getPiece(selection[0], selection[1]).isWhite() == colorFlag) {
                    break;
                } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
                if (! myObjBoard.getPiece(selection[0], selection[1]).isWhite() == ! colorFlag) {
                    System.out.println("validation passed"); break;
                } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
                
            } else System.out.println("No piece at the selected tile. Please try again.");
        }
    }

    private void validateMove() {

        while (true) {
            checkMove();

            // TODO: Implement all logic checks from board class
            if (activePiece.isTileValid(selection[0], selection[1], move[0], move[1]) && myObjBoard.isInBounds(move[0], move[1])) {
                myObjBoard.movePiece(selection[0], selection[1], move[0], move[1]); break;
            } else { System.out.println("Invalid move. Please try again.");}
        }
    }

    private void switchTurns() {
        colorFlag = !colorFlag;
    }

    private void printBoard(boolean colorFlag) {
        if (colorFlag) {
            myObjBoard.print(); System.out.println("White's turn");
        } else {
            myObjBoard.printReverse(); System.out.println("Black's turn");
        }
    }

    private void highlightMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

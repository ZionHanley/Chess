
import ChessPieces.Pieces;
import java.util.ArrayList;

public class GameState {
    Board myObjBoard = new Board();

    boolean colorFlag = true;

    int[] move;

    Pieces activePiece;

    @SuppressWarnings("unused")
    ArrayList<Pieces> WhiteCaptured = new ArrayList<>();
    @SuppressWarnings("unused")
    ArrayList<Pieces> BlackCaptured = new ArrayList<>();

    public GameState() {

        myObjBoard.setBoard(); // Figure out how to set the board with pieces in their starting positions.
    }

    public void update() {

        printBoard(colorFlag); // Print the board and indicate whose turn it is.
        
        validateMove(); // Input -> checkMove() -> validateMove() 
        // Need to implement check and checkmate detection

        switchTurns(); // Switch turns

        // TODO: Add logic to check for check, checkmate, and draw conditions here.

    }

    private void getUserInput() {

        while (true) {
            System.out.print("Enter your move: "); // Use spaced out numbers for the format
            String moveTerminal = System.console().readLine();
            String[] parts = moveTerminal.split(" ");

            if (parts.length == 4) {
                try {
                    move = new int[] {
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])
                    };
                    break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                }
            } else {
                System.out.println("Invalid input format. Please enter exactly four numbers.");
            }
        }
    }

    private void checkMove() {

        while(true) {

            getUserInput();

            // Check if the selected piece belongs to the current player
            if (myObjBoard.getPiece(move[0], move[1]) != null) {
                
                if (myObjBoard.getPiece(move[0], move[1]).isWhite() == colorFlag) {
                    System.out.println("validation passed"); break;
                } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
                if (! myObjBoard.getPiece(move[0], move[1]).isWhite() == ! colorFlag) {
                    System.out.println("validation passed"); break;
                } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
                
            } else System.out.println("No piece at the selected tile. Please try again.");
        }
    }

    private void validateMove() {

        while (true) {
            checkMove();

            activePiece = myObjBoard.getPiece(move[0], move[1]);

        if (activePiece.isTileValid(move[0], move[1], move[2], move[3]) && myObjBoard.isInBounds(move[2], move[3])) {
            myObjBoard.movePiece(move[0], move[1], move[2], move[3]); break;
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

}

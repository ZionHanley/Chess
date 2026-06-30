
import ChessPieces.King;
import ChessPieces.Pieces;
import java.util.ArrayList;

public class GameState {
    Board myObjBoard = new Board();

    boolean isWhiteTurn = true;

    King myObjKing = new King(true);
    King myObjKing2 = new King(false);

    ArrayList<int[]> History = new ArrayList<>();
    ArrayList<Pieces> WhiteCaptured = new ArrayList<>();
    ArrayList<Pieces> BlackCaptured = new ArrayList<>();

    public GameState() {
        myObjBoard.setBoard();
        myObjBoard.setPiece(0, 0, myObjKing);
        myObjBoard.setPiece(7, 7, myObjKing2);
    }

    public void update() {
        

        if (isWhiteTurn) {
            myObjBoard.print();
            System.out.println("White's turn");
            
        } else {
            myObjBoard.printReverse();
            System.out.println("Black's turn");

        }

        int[] move = getUserInput();

        Pieces activePiece = Board.getPiece(move[0], move[1]);

        // Validate move
        if (activePiece.isTileValid(move[0], move[1], move[2], move[3]) && myObjBoard.isInBounds(move[2], move[3])) {

            activePiece.setTile(move[2], move[3]);
                myObjBoard.setPiece(move[2], move[3], activePiece);
                myObjBoard.setPiece(move[0], move[1], null);
                System.out.println(activePiece.getSymbol() + " moved to " + move[2] + move[3]);

            History.add(move);
        } else {
            System.out.println("Invalid move. Please try again.");
        }

        // Check for check/checkmate



        // Switch turns

        
    }

    private static int[] getUserInput() {
        System.out.println("Enter your move : ");
        String move = System.console().readLine();
        int[] moveArray = new int[] {move.charAt(0) - '0', move.charAt(1) - '0', move.charAt(2) - '0', move.charAt(3) - '0'};
        return moveArray;
    }

    @SuppressWarnings("unused")
    private void switchTurns() {
        isWhiteTurn = !isWhiteTurn;
    }

    @SuppressWarnings("unused")
    private void checkForCheck() {
        // Implement check detection logic here
    }

    @SuppressWarnings("unused")
    private void checkForCheckmate() {
        // Implement checkmate detection logic here
    }

    @SuppressWarnings("unused")
    private void checkForDraw() {
        // Implement draw detection logic here
    }

    @SuppressWarnings("unused")
    private void printAllPieces() {
        for (int i = 0; i < Board.GameBoard.length; i++) {
            for (int j = 0; j < Board.GameBoard[i].length; j++) {
                Pieces piece = Board.getPiece(i, j);
                if (piece != null) {
                    System.out.println("Piece at (" + i + ", " + j + "): " + piece.getSymbol());
                }
            }
        }
    }

}

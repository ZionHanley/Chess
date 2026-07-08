import ChessPieces.King;
import ChessPieces.Pieces;
import java.util.ArrayList;

public class Board {

    public static int ID = 0;
    public final Pieces[][] GameBoard = new Pieces[8][8];
    public final ArrayList<Integer> MoveHistory = new ArrayList<>();
    int size = GameBoard.length; // Added this so that the size of the board can be dynamic in the future for what ever reason.

    public boolean isCheck = false;
    public boolean isCheckmate = false;

    King k1 = new King(true);
    King k2 = new King(false);

    public Board() {
        ID++;
    }

    //TODO: Implement methods for setting up the board and checking game state (check, checkmate, draw).

    // Getters

    public Pieces getPiece(int rank, int file) {
            return GameBoard[file][rank];
    }

    public int getLastMove() {
        if (MoveHistory.isEmpty()) {
            return -1; // or throw an exception, depending on your needs
        }
        return MoveHistory.get(MoveHistory.size() - 1);
    }

    public ArrayList<Integer> getAllMoves() {
        return MoveHistory;
    }

    public void printTile(int rank, int file) {
        System.out.println(this.getPiece(rank, file));
    }   

    public void print() {
        System.out.println("( GameBoard " + ID + " ) " + "\u2500".repeat(50));
        
        // This gives the board the first letter to print out the files.
        Character x = 65;
        int num = size;

        for (int i = 0; i < size; i++) {
            System.out.print("| " + x++ + " |");
        }

        System.out.println("");

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                Pieces value = this.getPiece(j,i);

                if (value == null) {System.out.print("[ " + " " + " ]");}
                else {System.out.print("[ " + value.getSymbol() + " ]");}
            }
            System.out.println("| " + num-- + " |");
        }

        System.out.println("\u2500".repeat(66));
    }

    public void printReverse() { // Prints the board in the alternate view so i can switch perspectives for different players
        System.out.println("( GameBoard " + ID + " ) " + "\u2500".repeat(50));
        
        // This gives the board the first letter to print out the files.
        Character x = 65;
        int num = 1;

        for (int i = 0; i < size; i++) {
            System.out.print("| " + x++ + " |");
        }

        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pieces value = this.getPiece(j,i);

                if (value == null) {System.out.print("[ " + " " + " ]");}
                else {System.out.print("[ " + value.getSymbol() + " ]");}
            }
            System.out.println("| " + num++ + " |");
        }

        System.out.println("\u2500".repeat(66));
    }

    public void printPieces() {
        for (int i = 0; i < this.GameBoard.length; i++) {
            for (int j = 0; j < this.GameBoard[i].length; j++) {
                Pieces piece = this.getPiece(j,i);
                if (piece != null) {
                    System.out.println("Piece at (" + i + ", " + j + "): " + piece.getSymbol());
                }
            }
        }
    }

    // Setters

    public void setPiece(int rank, int file, Pieces piece) {
        GameBoard[file][rank] = piece;
    }

    public void movePiece(int startRank, int startFile, int endRank, int endFile) {
        Pieces piece = getPiece(startRank, startFile);
        if (piece != null && isInBounds(endRank, endFile) && piece.isTileValid(startRank, startFile, endRank, endFile)) {
            setPiece(endRank, endFile, piece);
            setPiece(startRank, startFile, null);
        } else {
            System.err.println("Invalid move.");
        }
    }

    public boolean isInBounds(int rank, int file) {
        return file >= 0 && file < size && rank >= 0 && rank < size;
    }

    public boolean isCheck() {
        // TODO: Implement check detection logic
        return false;
    }

    public boolean isCheckmate() {
        // TODO: Implement checkmate detection logic
        return false;
    }

    public boolean  isDraw() {
        // TODO: Implement draw detection logic
        return false;
    }

    public boolean  isStalemate() {
        // TODO: Implement draw detection logic
        return false;
    }

    public boolean validateMove(int[] move, Pieces activPieces) { // TODO: Implement move validation logic

        //TODO: combine these two checks and add the isTileValid method from the Pieces class
    while(true) {

        // Check if the selected piece belongs to the current player
        if (myObjBoard.getPiece(selection[0],selection[1]) != null) {
            
            if (myObjBoard.getPiece(selection[0], selection[1]).isWhite() == colorFlag) {
                break;
            } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
            if (! myObjBoard.getPiece(selection[0], selection[1]).isWhite() == ! colorFlag) {
                System.out.println("validation passed"); break;
            } else {System.out.println("You cannot move your opponent's piece. Please try again.");}
            
        } else System.out.println("No piece at the selected tile. Please try again.");

        // TODO: Implement all logic checks from board class
        if (activePiece.isTileValid(selection[0], selection[1], move[0], move[1]) && myObjBoard.isInBounds(move[0], move[1])) {
            myObjBoard.movePiece(selection[0], selection[1], move[0], move[1]); break;
        } else { System.out.println("Invalid move. Please try again.");}
        }

        return true;
    }

    public void setBoard() {
        // White Pieces
        this.setPiece(3, 0, k1); // White King

        // Black Pieces
        this.setPiece(3, 7, k2); // Black King
    }

    public void setMoveHistory(int move) {
        MoveHistory.add(move);
    }
}

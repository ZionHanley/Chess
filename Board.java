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

    public Pieces getPiece(int file, int rank) {
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

    public void printTile(int file, int rank) {
        System.out.println(this.getPiece(file, rank));
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
                Pieces value = this.getPiece(i,j);

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

        System.out.println("");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pieces value = this.getPiece(i,j);

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
                Pieces piece = this.getPiece(i, j);
                if (piece != null) {
                    System.out.println("Piece at (" + i + ", " + j + "): " + piece.getSymbol());
                }
            }
        }
    }

    // Setters

    public void setPiece(int file, int rank, Pieces piece) {
        GameBoard[file][rank] = piece;
    }

    public void movePiece(int startFile, int startRank, int endFile, int endRank) {
        Pieces piece = getPiece(startFile, startRank);
        if (piece != null && isInBounds(endFile, endRank)) {
            setPiece(endFile, endRank, piece);
            setPiece(startFile, startRank, null);
        } else {
            System.err.println("Invalid move.");
        }
    }

    public boolean isInBounds(int file, int rank) {
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

    public void setBoard() {
        GameBoard[0][3] = k1; // White King
        GameBoard[7][3] = k2; // Black King
    }

    public void setMoveHistory(int move) {
        MoveHistory.add(move);
    }
}

import ChessPieces.Pieces;

public class Board {

    public static int ID = 0;
    public static final Pieces[][] GameBoard = new Pieces[8][8];
    // Add this so that the size of the board can be dynamic in the future for what ever reason.
    int size = GameBoard.length;

    public Board() {
        ID++;
    }

    //getters

    public static Pieces getPiece(int file, int rank) {
            return GameBoard[file][rank];
    }

    //issers

    public boolean isInBounds(int file, int rank) {
        return file >= 0 && file < size && rank >= 0 && rank < size;
    }

    //setters

    public void setPiece(int file, int rank, Pieces piece) {
        GameBoard[file][rank] = piece;
    }

    public void setBoard() {

    }

    public void printTile(int file, int rank) {
        System.out.println(Board.getPiece(file, rank));
    }

    public void printArray() {
        for (Pieces[] row : GameBoard) {
            for (Pieces piece : row) {
                System.out.print(piece + " ");
            }
            System.out.println();
        }
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
                Pieces value = Board.getPiece(i,j);

                if (value == null) {System.out.print("[ " + " " + " ]");}
                else {System.out.print("[ " + value.getSymbol() + " ]");}
            }
            System.out.println("| " + num-- + " |");
        }

        System.out.println("\u2500".repeat(66));
    }

    // Prints the board in the alternate view so i can switch perspectives for different players
    public void printReverse() {
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
                Pieces value = Board.getPiece(i,j);

                if (value == null) {System.out.print("[ " + " " + " ]");}
                else {System.out.print("[ " + value.getSymbol() + " ]");}
            }
            System.out.println("| " + num++ + " |");
        }

        System.out.println("\u2500".repeat(66));
    }

}

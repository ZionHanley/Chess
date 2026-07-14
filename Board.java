import ChessPieces.King;
import ChessPieces.Pieces;
import java.util.ArrayList;

public class Board {

    public static int ID = 0;
    public final Pieces[][] GameBoard = new Pieces[8][8];
    public final ArrayList<Integer> MoveHistory = new ArrayList<>();
    public final ArrayList<Pieces> WhiteCaptured = new ArrayList<>();
    public final ArrayList<Pieces> BlackCaptured = new ArrayList<>();
    int size = GameBoard.length; // Added this so that the size of the board can be dynamic in the future for what ever reason.

    public boolean isCheck = false;
    public boolean isCheckmate = false;
    public boolean whiteCastle = true;
    public boolean blackCastle = true;

    King k1 = new King(true);
    King k2 = new King(false);

    public Board() {
        ID++;
    }

    // Getters

    public Pieces getPiece(int rank, int file) {
            return GameBoard[file][rank];
    }

    public ArrayList<Integer> getPossibleMove() {
        return MoveHistory;
    }

    public int getLastMove() {
        if (MoveHistory.isEmpty()) {
            return -1; // or throw an exception, depending on your needs
        }
        return MoveHistory.get(MoveHistory.size() - 1);
    }

    public ArrayList<Integer> getMoveHistory() {
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

    public void printHighlight(int rank, int file, Pieces activePiece, boolean colorFlag) {
        // TODO: run all tiles through the isTileValiad method of the piece and where there is a valid move, highlight it on the board. This will be done by changing the color of the tile to a different color.
        
        if (colorFlag) {
            System.out.println("( GameBoard " + ID + " ) " + "\u2500".repeat(50));
            
            Character x = 65;
            int num = size;

            for (int i = 0; i < size; i++) {
                System.out.print("| " + x++ + " |");
            }
            System.out.println("");

            for (int i = size - 1; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    Pieces value = this.getPiece(j,i);
                    if (activePiece.isTileValid(rank, file, j, i)) {{System.out.print("[ " + "*" + " ]");}
                    } else {
                        if (value == null) {System.out.print("[ " + " " + " ]");}
                        else {System.out.print("[ " + value.getSymbol() + " ]");}
                    }
                }
                System.out.println("| " + num-- + " |");
            }

            System.out.println("\u2500".repeat(66));
        } else {
            System.out.println("( GameBoard " + ID + " ) " + "\u2500".repeat(50));
            
            Character x = 65;
            int num = 1;

            for (int i = 0; i < size; i++) {
                System.out.print("| " + x++ + " |");
            }
            System.out.println();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Pieces value = this.getPiece(j,i);
                    if (activePiece.isTileValid(rank, file, j, i)) {{System.out.print("[ " + "*" + " ]");}
                    } else {
                        if (value == null) {System.out.print("[ " + " " + " ]");}
                        else {System.out.print("[ " + value.getSymbol() + " ]");}
                    }
                }
                System.out.println("| " + num++ + " |");
            }
            System.out.println("\u2500".repeat(66));
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

    private boolean isInBounds(int rank, int file) {
        return file >= 0 && file < size && rank >= 0 && rank < size;
    }

    private boolean isCheck() {
        // TODO: Implement check detection logic
        return false;
    }

    private boolean isCheckmate() {
        // TODO: Implement checkmate detection logic
        return false;
    }

    private boolean  isDraw() {
        // TODO: Implement draw detection logic
        return false;
    }

    private boolean  isStalemate() {
        // TODO: Implement draw detection logic
        return false;
    }

    public boolean validateMove(int[] selection, int[] move, boolean colorFlag, Pieces activePieces) {
        // TODO: add all private methods
        if (activePieces != null) {
            
            if (activePieces.isWhite() == colorFlag) {
                
                if (activePieces.isTileValid(selection[0], selection[1], move[0], move[1]) && isInBounds(move[0], move[1])) {
                    return true;
                } return false;
            } System.err.println("Wrong color"); return false;
        } System.err.println("No piece at the selected tile"); return false;
    }

    public void setBoard() {
        // White Pieces
        this.setPiece(4, 0, new ChessPieces.King(true));
        this.setPiece(3, 0, new ChessPieces.Queen(true));

        // Black Pieces
        this.setPiece(4, 7, new ChessPieces.King(false));
        this.setPiece(3, 7, new ChessPieces.Queen(false));
    }

    public void setMoveHistory(int move) {
        MoveHistory.add(move);
    }
}

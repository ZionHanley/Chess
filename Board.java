import ChessPieces.Pieces;
import java.util.ArrayList;

public class Board {

    public final Pieces[][] GameBoard = new Pieces[8][8];
    public final ArrayList<Integer> MoveHistory = new ArrayList<>();
    public final ArrayList<Pieces> WhiteCaptured = new ArrayList<>();
    public final ArrayList<Pieces> BlackCaptured = new ArrayList<>();
    int size = GameBoard.length; // Added this so that the size of the board can be dynamic in the future for what ever reason.

    public boolean isCheck = false;
    public boolean isCheckmate = false;
    public boolean whiteCastle = true;
    public boolean blackCastle = true;

    public Board() {}

    public void move(Move x){
        
    }

    public void move(int start_rank, int start_file, int end_rank, int end_file){
        
    }

    public void promote() {

    }

    public Pieces getPiece(int rank, int file) {
            return GameBoard[file][rank];
    }
    public ArrayList<Integer> getPossibleMove() {
        return ;
    }
    public int getLastMove() {
        if (MoveHistory.isEmpty()) {
            return -1;
        }
        return MoveHistory.get(MoveHistory.size() - 1);
    }
    public ArrayList<Integer> getMoveHistory() {
        return MoveHistory;
    }
    public void print() { // Full Board
        System.out.println("( GameBoard ) " + "\u2500".repeat(50));
        
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

        System.out.println("\u2500".repeat(64));
    }
    public void printReverse() { // Alternate Full Board perspectives
        System.out.println("( GameBoard ) " + "\u2500".repeat(50));
        
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
    public void printPieces() { // Prints a list of board pieces
        for (int i = 0; i < this.GameBoard.length; i++) {
            for (int j = 0; j < this.GameBoard[i].length; j++) {
                Pieces piece = this.getPiece(j,i);
                if (piece != null) {
                    System.out.println("Piece at (" + i + ", " + j + "): " + piece.getSymbol());
                }
            }
        }
    }
    public void printHighlight(int rank, int file, Pieces activePiece, boolean colorFlag) { //Board with selected piece avalaible move
        // TODO: run all tiles through the isTileValiad method of the piece and where there is a valid move, highlight it on the board. This will be done by changing the color of the tile to a different color.
        // TODO: add move validation to this method
        Character x = 65;
        if (colorFlag) {
            System.out.println("( GameBoard ) " + "\u2500".repeat(50));
            
            int num = size;

            for (int i = 0; i < size; i++) {
                System.out.print("| " + x++ + " |");
            }
            System.out.println("");

            for (int i = size - 1; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    Pieces value = this.getPiece(j,i);
                    if (activePiece.isTileValid(rank, file, j, i) && validateMove( new int[] {rank,file}, new int[] {j,i}, colorFlag, activePiece)) {{
                        System.out.print("[ " + "*" + " ]");
                    }
                    } else {
                        if (value == null) {System.out.print("[ " + " " + " ]");}
                        else {System.out.print("[ " + value.getSymbol() + " ]");}
                    }
                }
                System.out.println("| " + num-- + " |");
            }

            System.out.println("\u2500".repeat(66));
        } else {
            System.out.println("( GameBoard ) " + "\u2500".repeat(50));
            
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
    public void setPiece(int rank, int file, Pieces piece) {
        GameBoard[file][rank] = piece;
    }
    public void movePiece(int startRank, int startFile, int endRank, int endFile) {
        Pieces piece = getPiece(startRank, startFile);
        if (piece != null && isInBounds(endRank, endFile) && piece.isTileValid(startRank, startFile, endRank, endFile)) {
            captor(endFile, endRank, getPiece(endRank,endFile));
            setPiece(endRank, endFile, piece);
            setPiece(startRank, startFile, null);
        } else {
            System.err.println("Invalid move.");
        }
    }
    public boolean validateMove(int[] selection, int[] move, boolean colorFlag, Pieces activePieces) {
        if (activePieces == null) return false;
        if (selection == null || selection.length != 2 || move == null || move.length != 2
                || !isInBounds(selection[0], selection[1]) || !isInBounds(move[0], move[1])) {
            System.err.println("Invalid move");
            return false;
        }
        if (activePieces.isWhite() != colorFlag) return false;
        Pieces destinationPiece = getPiece(move[0], move[1]);
        if (destinationPiece != null && destinationPiece.isWhite() == activePieces.isWhite()) return false;
        if (!activePieces.isTileValid(selection[0], selection[1], move[0], move[1])) return false;
        return !(!(activePieces instanceof ChessPieces.Knight) && !isPathClear(selection[0], selection[1], move[0], move[1]));
    }
    private boolean isPathClear(int startRank, int startFile, int endRank, int endFile) {
        int rankStep = Integer.compare(endRank, startRank);
        int fileStep = Integer.compare(endFile, startFile);

        int rank = startRank + rankStep;
        int file = startFile + fileStep;

        while (rank != endRank || file != endFile) {
            if (!isTileEmpty(rank, file)) {
                return false;
            }
            rank += rankStep;
            file += fileStep;
        }

        return true;
    }
    public void setBoard() {
        // White Pieces
        {
        this.setPiece(4, 0, new ChessPieces.King(true));
        this.setPiece(3, 0, new ChessPieces.Queen(true));
        this.setPiece(0, 0, new ChessPieces.Rook(true));
        this.setPiece(7, 0, new ChessPieces.Rook(true));
        this.setPiece(2, 0, new ChessPieces.Bishop(true));
        this.setPiece(5, 0, new ChessPieces.Bishop(true));
        this.setPiece(1, 0, new ChessPieces.Knight(true));
        this.setPiece(6, 0, new ChessPieces.Knight(true));
        this.setPiece(0, 1, new ChessPieces.Pawn(true));
        this.setPiece(1, 1, new ChessPieces.Pawn(true));
        this.setPiece(2, 1, new ChessPieces.Pawn(true));
        this.setPiece(3, 1, new ChessPieces.Pawn(true));
        this.setPiece(4, 1, new ChessPieces.Pawn(true));
        this.setPiece(5, 1, new ChessPieces.Pawn(true));
        this.setPiece(6, 1, new ChessPieces.Pawn(true));
        this.setPiece(7, 1, new ChessPieces.Pawn(true));
        }

        // Black Pieces
        {
        this.setPiece(4, 7, new ChessPieces.King(false));
        this.setPiece(3, 7, new ChessPieces.Queen(false));
        this.setPiece(0, 7, new ChessPieces.Rook(false));
        this.setPiece(7, 7, new ChessPieces.Rook(false));
        this.setPiece(2, 7, new ChessPieces.Bishop(false));
        this.setPiece(5, 7, new ChessPieces.Bishop(false));
        this.setPiece(1, 7, new ChessPieces.Knight(false));
        this.setPiece(6, 7, new ChessPieces.Knight(false));
        this.setPiece(7, 6, new ChessPieces.Pawn(false));
        this.setPiece(6, 6, new ChessPieces.Pawn(false));
        this.setPiece(5, 6, new ChessPieces.Pawn(false));
        this.setPiece(4, 6, new ChessPieces.Pawn(false));
        this.setPiece(3, 6, new ChessPieces.Pawn(false));
        this.setPiece(2, 6, new ChessPieces.Pawn(false));
        this.setPiece(1, 6, new ChessPieces.Pawn(false));
        this.setPiece(0, 6, new ChessPieces.Pawn(false));
        }
    }
    public void setMoveHistory(int move) {
        MoveHistory.add(move);
    }
    private boolean isInBounds(int rank, int file) {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }
    public boolean isTileEmpty(int rank, int file) {
        return GameBoard[file][rank] == null;
    }
    public void captor(int rank, int file, Pieces captor) {
        System.out.println("Captured White pieces: " + WhiteCaptured);
        System.out.println("Captured Black pieces: " + BlackCaptured);
        
        if (captor == null) return;
        if (captor.isWhite()) {
            WhiteCaptured.add(captor);
            System.out.println("White Pieces");
            for (Pieces sym : WhiteCaptured) {
                System.out.print(sym.getSymbol());
            }
        } else {
            BlackCaptured.add(captor);
            System.out.println("Blacksele Pieces");
            for (Pieces sym : BlackCaptured) {
                System.out.print(sym.getSymbol());
            }
        }
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

}

package ChessPieces;

public class Bishop extends Pieces {

    public Bishop(Boolean white) {
        super(white, (char) (white ? 9815 : 9821));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }
}

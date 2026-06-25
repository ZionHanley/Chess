package ChessPieces;

public class Rook extends Pieces{

    public Rook(Boolean white) {
        super(white, (char) (white ? 9814 : 9820));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }
}

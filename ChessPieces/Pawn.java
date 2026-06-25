package ChessPieces;

public class Pawn extends Pieces{

    public Pawn(Boolean white) {
        super(white, (char) (white ? 9817 : 9823));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }
}

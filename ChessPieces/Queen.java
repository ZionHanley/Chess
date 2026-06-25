package ChessPieces;

public class Queen extends Pieces{

    public Queen(Boolean white) {
        super(white, (char) (white ? 9813 : 9819));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }
}

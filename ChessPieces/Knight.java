package ChessPieces;

public class Knight extends Pieces{

    public Knight(Boolean white) {
        super(white, (char) (white ? 9816 : 9822));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }
}

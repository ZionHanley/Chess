package ChessPieces;

public class King extends Pieces{

    public King(Boolean white) {
        super(white, (char) (white ? 9812 : 9818));
    }

    @Override
    public Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank) {
        return Math.abs(endrank - startrank) <= 1 && Math.abs(endfile - startfile) <= 1;
    }

}

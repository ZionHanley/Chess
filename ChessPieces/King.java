package ChessPieces;

public class King extends Pieces{

    public King(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9812 : 9818);
    }

    @Override
    public void getValidTiles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValidTiles'");
    }
}

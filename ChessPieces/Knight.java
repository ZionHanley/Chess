package ChessPieces;

public class Knight extends Pieces {

    public Knight(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9816 : 9822);
    }

    @Override
    public boolean isTileValid(int startRank, int startFile, int endRank, int endFile) {
        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        return (fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2);
    }
    
}

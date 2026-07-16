package ChessPieces;

public class Rook extends Pieces {

    public Rook(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9814 : 9820);
    }

    @Override
    public boolean isTileValid(int startRank, int startFile, int endRank, int endFile) {
        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        return (fileDiff == 0 || rankDiff == 0) && !(fileDiff == 0 && rankDiff == 0) && isPathClear(startRank, startFile, endRank, endFile);
    }
    
}

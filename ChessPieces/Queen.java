package ChessPieces;

public class Queen extends Pieces {

    public Queen(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9813 : 9819);
    }

    @Override
    public boolean isTileValid(int startRank, int startFile, int endRank, int endFile) {
        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        return (fileDiff == rankDiff || fileDiff == 0 || rankDiff == 0) && !(fileDiff == 0 && rankDiff == 0) && isPathClear(startRank, startFile, endRank, endFile);
    }
    
}

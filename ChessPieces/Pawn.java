package ChessPieces;

public class Pawn extends Pieces {

    public Pawn(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9817 : 9823);
    }

    @Override
    public boolean isTileValid(int startRank, int startFile, int endRank, int endFile) {
        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        // TODO: Have to fix
        if (fileDiff != 0) {
            if (this.isWhite()) {
                return (startRank == 1 && fileDiff <= 2 && endRank > startRank) || (fileDiff == 1 && endRank > startRank);
            } else {
                return (startRank == 6 && fileDiff <= 2 && endRank < startRank) || (fileDiff == 1 && endRank < startRank);
            }
        }

        return false;
    }
    
}

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
        if ((startFile == 1) && (this.isWhite()) || (startFile == 7) && !(this.isWhite())) {
            if (this.isWhite()) {
                return ((fileDiff == 1) || (fileDiff == 2)) && ((rankDiff == 0) && endFile > startFile);
            } else {
                return ((fileDiff == 1) || (fileDiff == 2)) && ((rankDiff == 0) && startFile > endFile);
            }
        } else {
            if (this.isWhite()) {
                return (fileDiff == 1) && ((rankDiff == 0) && endFile > startFile);
            } else {
                return (fileDiff == 1) && ((rankDiff == 0) && startFile > endFile);
            }
        }
        
    }
    
}

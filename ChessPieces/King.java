package ChessPieces;

public class King extends Pieces{

    public King(boolean white) {
        super(white);
        this.symbol = (char) (white ? 9812 : 9818);
    }

    public boolean checkCastle(int[][] board) {
        // Implement castling logic here
        return false;
    }
    @Override
    public boolean isTileValid(int startRank, int startFile, int endRank, int endFile) {
        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        // King moves 1 tile in any direction
        return (fileDiff <= 1 && rankDiff <= 1) && !(fileDiff == 0 && rankDiff == 0);
    }
}

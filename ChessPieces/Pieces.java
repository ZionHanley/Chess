package ChessPieces;

public abstract class Pieces {

    //White Chess pieces
    char Kingw = 9812;
    char Queenw = 9813;
    char Rookw = 9814;
    char Bishopw = 9815;
    char Knightw = 9816;
    char Pawnw = 9817;

    //Black Chess pieces
    char Kingb = 9818;
    char Queenb = 9819;
    char Rookb = 9820;
    char Bishopb = 9821;
    char Knightb = 9822;
    char Pawnb = 9823;

    protected char symbol;
    protected Boolean white;

    public Pieces(Boolean white, char symbol) {
        this.white = white;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public Boolean isWhite() {
        return this.white;
    }

    public abstract Boolean isMoveValid(int startfile, int startrank, int endfile, int endrank);
    
}

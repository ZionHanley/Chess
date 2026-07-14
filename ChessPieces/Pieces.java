package ChessPieces;


public abstract class Pieces {

    public char symbol;
    public boolean white;

    public Pieces(boolean white) {
        this.white = white;
        this.symbol = 'D';
    }

    //getters

    public char getSymbol() {
        return this.symbol;
    }

    //issers

    public boolean isWhite() {
        return this.white;
    }

    public abstract boolean isTileValid(int starRank, int startFile, int endRank, int endFile);

    //setters

    //TODO: Make a method for changing symbol incase of the chess haracters not being supported by the console.

    public void setSymbol(char symbol) {
        
        if (white) {
            switch (symbol) {
                case 'K':
                    this.symbol = 9812;
                    break;
                case 'Q':
                    this.symbol = 9813;
                    break;
                case 'R':
                    this.symbol = 9814;
                    break;
                case 'B':
                    this.symbol = 9815;
                    break;
                case 'N':
                    this.symbol = 9816;
                    break;
                case 'P':
                    this.symbol = 9817;
                    break;
                default:
                    this.symbol = 'P';
            }
        } else {
            switch (symbol) {
                case 'K':
                    this.symbol = 9818;
                    break;
                case 'Q':
                    this.symbol = 9819;
                    break;
                case 'R':
                    this.symbol = 9820;
                    break;
                case 'B':
                    this.symbol = 9821;
                    break;
                case 'N':
                    this.symbol = 9822;
                    break;
                case 'P':
                    this.symbol = 9823;
                    break;
                default:
                    this.symbol = 'P';
            }
        }
    }
    
}

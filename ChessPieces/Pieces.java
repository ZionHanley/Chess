package ChessPieces;


public abstract class Pieces {

    public int file;
    public int rank;
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

    public void getTile() {
        System.out.println("File: " + this.file + " Rank: " + this.rank);
    }

    public abstract void getValidTiles();

    //issers

    public boolean isWhite() {
        return this.white;
    }

    public boolean isTileValid(int startfile, int startrank, int endfile, int endrank) {
        // Default implementation - can be overridden by subclasses
        return true;
    }

    //setters

    public void setTile(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

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

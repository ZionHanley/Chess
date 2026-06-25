public class Board {

    private static int ID = 0;
    @SuppressWarnings("FieldMayBeFinal")
    private static char[][] GameBoard = new char[8][8];
    int size = GameBoard.length;

    public Board() {
        ID++;

    }

    // Methods To-Do
    //Get, Set, Print, printAll, printallIndex

    //The file internally will be an intger but when ever displyed I want to be as a character

    public static char getValue(int file, int rank) {
        return GameBoard[file][rank];
    }

    public void setValue(int file, int rank, char piece) {

    }

    // This will be that method to set the boards begining position
    public void setBoard() {

    }

    public void printValue(int file, int rank) {
        System.out.println(Board.getValue(file, rank));
    }

    public void printBoard() {
        System.out.println("( GameBoard " + ID + " ) " + "\u2500".repeat(40));
        
        Character x = 65;
        int num = size;

        for (int i = 0; i < size; i++) {
            System.out.print("| " + x++ + " |");
        }

        System.out.println("");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char value = Board.getValue(i,j);

                if (value == 0) {
                    System.out.print("[ " + " " + " ]");
                } else {
                    System.out.print("[ " + value + " ]");
                }
            }
            System.out.println("| " + num-- + " |");
        }

        System.out.println("\u2500".repeat(56));
    }

    // Prints the board in the alternate view so i can switch perspectives for different players
    public void printBoardR() {

    }

}

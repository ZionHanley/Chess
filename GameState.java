
import ChessPieces.Pieces;
import java.io.IOException;

public class GameState {

    Board myObjBoard = new Board();
    Pieces activePiece;
    String command;
    boolean colorFlag = true;
    boolean highlightFlag = false;
    boolean exitflag = false;
    int[] selection;
    int[] move;

    public GameState() {
        myObjBoard.setBoard();
    }

    public void update() {

        //clearScreen();

        {
        if (highlightFlag) {myObjBoard.printHighlight(selection[0], selection[1], activePiece, colorFlag); highlightFlag = false;}
        else printBoard(colorFlag);}

        System.out.print("Enter command | select | move | highlight | pass | quit | ... "); command = System.console().readLine();

        // TODO: if ("a1h8".equals(command) ) {command == "select and move";}

        switch (command.toLowerCase()) {

            case "highlight" -> {
                if (activePiece == null) {
                    System.err.println("No Piece selected");
                } else {
                    highlightFlag = true;
                }
                return;
            }

            case "move" -> {
                if (activePiece == null) {
                    System.err.println("No Piece selected");
                    return;
                } else {
                    while (true) {
                        System.out.print("Enter your move: ");

                        if (exitflag == true) {
                            exitflag = false;
                            return;
                        }

                        move = getInput();
                        if (myObjBoard.validateMove(selection, move, colorFlag, activePiece)) {
                            myObjBoard.movePiece(selection[0], selection[1], move[0], move[1]);
                            break;
                        } else {
                            System.err.println("Invalid move. Please try again.");
                        }
                    }
                }
            }

            case "select" -> {
                System.out.print("Enter your selection: ");
                selection = getInput();
                activePiece = myObjBoard.getPiece(selection[0], selection[1]);
                return;
            }

            case "quit" -> {
                System.out.print("Are you sure you want to exit? (y/n) ");
                String exitConfirm = System.console().readLine();
                if (exitConfirm.equalsIgnoreCase("y")) {
                    System.out.println("Exiting the game...");
                    System.exit(0);
                } else {
                    System.out.println("Exit canceled.");
                    return;
                }
            }

            case "pass" -> {
                switchTurns();
                return;
            }

            default -> { 
                System.err.println("Invaliad command");
                return;
            }
        }

        switchTurns();

    }

    private int[] getInput() {

        int[] outkast = null;

        while (true) {
            String moveTerminal = System.console().readLine();
            
            if ("quit".equals(moveTerminal) || "exit".equals(moveTerminal)) {
                exitflag = true;
                break;
            }

            int size = moveTerminal.length();

            if (size == 2) {

                try {
                    outkast = new int[] {
                        Character.toUpperCase(moveTerminal.charAt(0)) - 'A',
                        moveTerminal.charAt(1) - '1'
                    };
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } if (size == 4) { // This code is for the event of select and move in a single action
                try {
                    outkast = new int[] {
                        Character.toUpperCase(moveTerminal.charAt(0)) - 'A',
                        moveTerminal.charAt(1) - 1,
                        Character.toUpperCase(moveTerminal.charAt(0)) - 'A',
                        moveTerminal.charAt(1) - 1
                    };
                        {
                            
                        }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else {
                System.out.println("Invalid input format. Rank and File ie \"a1\" or \"a1h8\"");
            }
        }
        return outkast;
    }
    private void switchTurns() {
        colorFlag = !colorFlag;
    }
    private void clearScreen() { 
        try {
            new ProcessBuilder("clear")
            .inheritIO()
            .start()
            .waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printBoard(boolean colorFlag) {
        if (colorFlag) {
            myObjBoard.print(); System.out.println("White's turn");
        } else {
            myObjBoard.printReverse(); System.out.println("Black's turn");
        }
    }
}

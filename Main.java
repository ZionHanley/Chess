public class Main {
    public static void main(String[] args) {

        GameState gameState = new GameState();
        
        while (true) {
            gameState.update();
        }
        // TODO: Make it so a piece cant pass through another piece.
        // TODO: Make it so a piece cant move to a tile with a piece of the same color.
        // TODO: Make it so a piece can capture a piece of the opposite color.
    }
}
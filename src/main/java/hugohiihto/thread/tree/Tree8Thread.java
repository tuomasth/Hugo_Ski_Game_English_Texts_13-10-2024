package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree8Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree8Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 8 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree8_x_position -= 2;
                gameDisplay.possibleTree8_y_position++;
            }
            if (gameDisplay.possibleTree8_x_position < -574) {
                gameDisplay.possibleTree8_x_position = (GameDisplay.d.width / 9);
                gameDisplay.possibleTree8_y_position = (GameDisplay.d.height / 7) + 32;
            }
        }
    }
}

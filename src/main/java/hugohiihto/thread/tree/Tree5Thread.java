package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree5Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree5Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 5 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree5_x_position += 2;
                gameDisplay.possibleTree5_y_position++;
            }
            if (gameDisplay.possibleTree5_x_position > 760) {
                gameDisplay.possibleTree5_x_position = (int) ((int) GameDisplay.d.getWidth() / 2) + 40;
                gameDisplay.possibleTree5_y_position = (int) ((int) GameDisplay.d.getHeight() / 3) - 80;
            }
        }
    }
}

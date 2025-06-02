package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree4Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree4Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 4 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree4_x_position += 2;
                gameDisplay.possibleTree4_y_position++;
            }
            if (gameDisplay.possibleTree4_x_position > 640) {
                gameDisplay.possibleTree4_x_position = (int) ((int) GameDisplay.d.getWidth() / 2) + 40;
                gameDisplay.possibleTree4_y_position = (int) ((int) GameDisplay.d.getHeight() / 3) - 100;
            }
        }
    }
}

package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree3Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree3Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 3 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree3_x_position -= 2;
                gameDisplay.possibleTree3_y_position++;
            }
            if (gameDisplay.possibleTree3_x_position < -500) {
                gameDisplay.possibleTree3_x_position = (GameDisplay.d.width / 5) - 4;
                gameDisplay.possibleTree3_y_position = (GameDisplay.d.height / 17) + 20;
            }
        }
    }
}

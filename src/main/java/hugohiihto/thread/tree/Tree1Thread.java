package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree1Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree1Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 1 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree1_x_position -= 2;
                gameDisplay.possibleTree1_y_position++;
            }
            if (gameDisplay.possibleTree1_x_position < -340) {
                gameDisplay.possibleTree1_x_position = (GameDisplay.d.width / 8) - 7;
                gameDisplay.possibleTree1_y_position = (GameDisplay.d.height / 12) + 20;
            }
        }
    }
}

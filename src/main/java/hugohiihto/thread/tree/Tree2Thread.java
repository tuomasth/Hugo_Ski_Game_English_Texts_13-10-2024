package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree2Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree2Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 2 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree2_x_position -= 2;
                gameDisplay.possibleTree2_y_position++;
            }
            if (gameDisplay.possibleTree2_x_position < -400) {
                gameDisplay.possibleTree2_x_position = (GameDisplay.d.width / 4) - 4;
                gameDisplay.possibleTree2_y_position = (GameDisplay.d.height / 8) + 20;
            }
        }
    }
}

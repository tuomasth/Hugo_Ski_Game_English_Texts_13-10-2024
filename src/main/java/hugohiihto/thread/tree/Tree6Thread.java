package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree6Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree6Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 6 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree6_x_position += 2;
                gameDisplay.possibleTree6_y_position++;
            }
            if (gameDisplay.possibleTree6_x_position > 800) {
                gameDisplay.possibleTree6_x_position = (int) ((int) GameDisplay.d.getWidth() / 2) + 40;
                gameDisplay.possibleTree6_y_position = (int) ((int) GameDisplay.d.getHeight() / 3) - 80;
            }
        }
    }
}

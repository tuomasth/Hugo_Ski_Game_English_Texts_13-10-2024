package hugohiihto.thread.tree;

import hugohiihto.GameDisplay;

public class Tree7Thread extends Thread {
    private final GameDisplay gameDisplay;

    public Tree7Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run object 7 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 2 == 0) {
                gameDisplay.possibleTree7_x_position += 2;
                gameDisplay.possibleTree7_y_position++;
            }
            if (gameDisplay.possibleTree7_x_position > 753) {
                gameDisplay.possibleTree7_x_position = (int) ((int) GameDisplay.d.getWidth() / 2) + 42;
                gameDisplay.possibleTree7_y_position = (int) ((int) GameDisplay.d.getHeight() / 3) - 82;
            }
        }
    }
}

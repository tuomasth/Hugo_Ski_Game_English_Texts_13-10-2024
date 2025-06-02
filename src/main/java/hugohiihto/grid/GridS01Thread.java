package hugohiihto.grid;

import hugohiihto.GameDisplay;

public class GridS01Thread extends Thread {
    private final GameDisplay gameDisplay;

    public GridS01Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run Hugo L thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        int target = 55;
        if (gameDisplay.currentGrid == 0) {
            target = 55;
        }
        if (gameDisplay.currentGrid == 1) {
            target = 55 + (gameDisplay.maxW / 3);
        }
        if (gameDisplay.x > target) {
            gameDisplay.x -= 6;
            gameDisplay.repaint();
        }
        if (gameDisplay.x < target) {
            gameDisplay.x += 6;
            gameDisplay.repaint();
        }
    }
}

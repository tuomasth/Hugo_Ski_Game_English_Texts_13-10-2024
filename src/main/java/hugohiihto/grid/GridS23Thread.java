package hugohiihto.grid;

import hugohiihto.GameDisplay;

public class GridS23Thread extends Thread {
    private final GameDisplay gameDisplay;

    public GridS23Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run Hugo R thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        int target = 55;
        if (gameDisplay.currentGrid == 2) {
            target = 55 + (gameDisplay.maxW / 3) * 2;
        }
        if (gameDisplay.currentGrid == 3) {
            target = 55 + (gameDisplay.maxW / 3) * 3;
        }
        if (gameDisplay.x > target) {
            gameDisplay.x -= 6;
            gameDisplay.repaint();
        }
        if (gameDisplay.x < target) {
            gameDisplay.x += 6; // 6 is the speed of changing grid
            gameDisplay.repaint();
        }
    }
}

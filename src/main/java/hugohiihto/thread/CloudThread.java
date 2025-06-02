package hugohiihto.thread;

import hugohiihto.GameDisplay;

public class CloudThread extends Thread {
    private final GameDisplay gameDisplay;

    public CloudThread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run (cloud) object 0 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (System.currentTimeMillis() % 19 == 0) {
            if (gameDisplay.leftWind) {
                gameDisplay.cloud_x_position--;
            } else {
                gameDisplay.cloud_x_position++;
            }
        }
        if (gameDisplay.cloud_x_position < -300 && gameDisplay.leftWind) {
            gameDisplay.cloud_x_position = GameDisplay.d.width;
        }
        if (gameDisplay.cloud_x_position > 700 && !gameDisplay.leftWind) {
            gameDisplay.cloud_x_position -= 1000;
        }
    }
}

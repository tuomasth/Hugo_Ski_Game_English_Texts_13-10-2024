package hugohiihto.thread.hazard;

import hugohiihto.GameDisplay;

public class HAZ3Thread extends Thread {
    private final GameDisplay gameDisplay;

    public HAZ3Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run ski track 3 thread.
     */
    public void run() {
        gameDisplay.repaint();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 3 == 0) {
                gameDisplay.currentHazardOrMoney3_y_position++;
                gameDisplay.currentHazardOrMoney3w++;
                gameDisplay.currentHazardOrMoney3h += 2;
            }
            //
            if (gameDisplay.currentHazardOrMoney3_y_position < 175) {
                gameDisplay.currentHazardOrMoney3_y_position++;
            }
            if (System.currentTimeMillis() % 3 == 0 && gameDisplay.currentHazardOrMoney3_x_position < 340) {
                gameDisplay.currentHazardOrMoney3_x_position++;
            }
            if (gameDisplay.currentHazardOrMoney3_y_position > GameDisplay.d.getHeight() - 230) {
                gameDisplay.currentHazardOrMoney3_x_position = (int) (GameDisplay.d.getWidth() + 1000);
                gameDisplay.currentHazardOrMoney3w = 1;
                gameDisplay.currentHazardOrMoney3h = 1;
            }
            if (gameDisplay.currentHazardOrMoney3_y_position > GameDisplay.d.getHeight() - 230 ||
                    !gameDisplay.hugoSkiing.tic) {
                //System.out.println("Off screen 3");
                gameDisplay.currentHazardOrMoney3_y_position = 8000;
                gameDisplay.currentHazardOrMoney3w = 1;
                gameDisplay.currentHazardOrMoney3h = 1;
                gameDisplay.repaint();
            }
            if (gameDisplay.currentHazardOrMoney3_y_position > gameDisplay.y + 30 && System.currentTimeMillis() % 5 == 0) {
                gameDisplay.currentHazardOrMoney3_y_position++;
            }
            if (gameDisplay.vanish4Faster) {
                gameDisplay.currentHazardOrMoney3_y_position += 3;
                gameDisplay.currentHazardOrMoney3w += 3;
                gameDisplay.currentHazardOrMoney3h += 3;
            }
        }
    }
}

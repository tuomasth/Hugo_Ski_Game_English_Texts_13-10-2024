package hugohiihto.thread.hazard;

import hugohiihto.GameDisplay;

public class HAZ2Thread extends Thread {
    private final GameDisplay gameDisplay;

    public HAZ2Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run ski track 2 thread.
     */
    public void run() {
        gameDisplay.repaint();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 3 == 0) {
                gameDisplay.currentHazardOrMoney2_y_position++;
                gameDisplay.currentHazardOrMoney2w++;
                gameDisplay.currentHazardOrMoney2h += 2;
            }
            //
            if (gameDisplay.currentHazardOrMoney2_y_position < 175) {
                gameDisplay.currentHazardOrMoney2_y_position++;
            }
            if (System.currentTimeMillis() % 3 == 0 && gameDisplay.currentHazardOrMoney2_x_position > 192) {
                gameDisplay.currentHazardOrMoney2_x_position--;
            }
            if (gameDisplay.currentHazardOrMoney2_y_position > GameDisplay.d.getHeight() - 230) {
                gameDisplay.currentHazardOrMoney2_x_position = (int) (GameDisplay.d.getWidth() + 1000);
                gameDisplay.currentHazardOrMoney2w = 1;
                gameDisplay.currentHazardOrMoney2h = 1;
            }
            if (gameDisplay.currentHazardOrMoney2_y_position > GameDisplay.d.getHeight() - 230 ||
                    !gameDisplay.hugoSkiing.tic) {
                //System.out.println("Off screen 2");
                gameDisplay.currentHazardOrMoney2_y_position = 8000;
                gameDisplay.currentHazardOrMoney2w = 1;
                gameDisplay.currentHazardOrMoney2h = 1;
                gameDisplay.repaint();
            }
            if (gameDisplay.currentHazardOrMoney2_y_position > gameDisplay.y + 30 && System.currentTimeMillis() % 5 == 0) {
                gameDisplay.currentHazardOrMoney2_y_position++;
            }
            if (gameDisplay.vanish4Faster) {
                gameDisplay.currentHazardOrMoney2_y_position += 3;
                gameDisplay.currentHazardOrMoney2_x_position--;
                gameDisplay.currentHazardOrMoney2w += 3;
                gameDisplay.currentHazardOrMoney2h += 3;
            }
        }
    }
}

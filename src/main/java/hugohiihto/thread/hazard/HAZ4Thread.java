package hugohiihto.thread.hazard;

import hugohiihto.GameDisplay;

public class HAZ4Thread extends Thread {
    private final GameDisplay gameDisplay;

    public HAZ4Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run ski track 4 thread.
     */
    public void run() {
        gameDisplay.repaint();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (System.currentTimeMillis() % 3 == 0) {
                gameDisplay.currentHazardOrMoney4_x_position += 2;
                gameDisplay.currentHazardOrMoney4_y_position++;
                gameDisplay.currentHazardOrMoney4w++;
                gameDisplay.currentHazardOrMoney4h += 2;
            }
            //
            if (gameDisplay.currentHazardOrMoney4_y_position < 165) {
                gameDisplay.currentHazardOrMoney4_y_position++;
            }
            if (gameDisplay.currentHazardOrMoney4_y_position > GameDisplay.d.getHeight() - 230) {
                gameDisplay.currentHazardOrMoney4_x_position = (int) (GameDisplay.d.getWidth() + 1000);
                gameDisplay.currentHazardOrMoney4w = 1;
                gameDisplay.currentHazardOrMoney4h = 1;
            }
            if (gameDisplay.currentHazardOrMoney4_y_position > GameDisplay.d.getHeight() - 230 ||
                    !gameDisplay.hugoSkiing.tic) {
                //System.out.println("Off screen 4");
                gameDisplay.currentHazardOrMoney4_y_position = 8000;
                gameDisplay.currentHazardOrMoney4w = 1;
                gameDisplay.currentHazardOrMoney4h = 1;
                gameDisplay.repaint();
            }
            if (gameDisplay.currentHazardOrMoney4_y_position > gameDisplay.y + 30 && System.currentTimeMillis() % 5 == 0) {
                gameDisplay.currentHazardOrMoney4_y_position++;
            }
            if (gameDisplay.vanish4Faster) {
                gameDisplay.currentHazardOrMoney4_y_position += 3;
                gameDisplay.currentHazardOrMoney4_x_position += 3;
                gameDisplay.currentHazardOrMoney4w += 3;
                gameDisplay.currentHazardOrMoney4h += 3;
            }
        }
    }
}

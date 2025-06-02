package hugohiihto.thread.hazard;

import hugohiihto.GameDisplay;

public class HAZ1Thread extends Thread {
    private final GameDisplay gameDisplay;

    public HAZ1Thread(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Run ski track 1 thread.
     */
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (!gameDisplay.gamePaused) {
            if (gameDisplay.hugoSkiing.currentStateAtTheLevel == 15 || gameDisplay.hugoSkiing.currentStateAtTheLevel == 26) {
                gameDisplay.currentHazardOrMoney1w = 1;
                gameDisplay.currentHazardOrMoney1h = 1;
                gameDisplay.currentHazardOrMoney1_y_position = 8000;
                gameDisplay.currentHazardOrMoney1_x_position = 8000;
            }
            if (gameDisplay.vanish4Faster && (gameDisplay.hugoSkiing.currentStateAtTheLevel == 14 || gameDisplay.hugoSkiing.currentStateAtTheLevel == 25)) {
                gameDisplay.currentHazardOrMoney1_y_position += 4;
            }
            if (gameDisplay.hugoSkiing.currentStateAtTheLevel == 14 || gameDisplay.hugoSkiing.currentStateAtTheLevel == 25) {
                if (gameDisplay.currentHazardOrMoney1_x_position < 310) {
                    gameDisplay.currentHazardOrMoney1_x_position++;
                } else {
                    gameDisplay.currentHazardOrMoney1_y_position += 3;
                }
                gameDisplay.currentHazardOrMoney1w = 120;
                gameDisplay.currentHazardOrMoney1h = 120;
                if (gameDisplay.currentHazardOrMoney1_x_position < 180 || gameDisplay.vanish4Faster) {
                    gameDisplay.currentHazardOrMoney1_x_position++;
                }
            } else {
                if (System.currentTimeMillis() % 3 == 0) {
                    gameDisplay.currentHazardOrMoney1_x_position -= 2;
                    gameDisplay.currentHazardOrMoney1_y_position -= 2;
                    gameDisplay.currentHazardOrMoney1w++;
                    gameDisplay.currentHazardOrMoney1h += 2;
                }
                //
                if (gameDisplay.currentHazardOrMoney1_y_position < 185) {
                    gameDisplay.currentHazardOrMoney1_y_position += 2;
                } else if (gameDisplay.currentHazardOrMoney1_y_position > gameDisplay.y - 25 && System.currentTimeMillis() % 5 == 0) {
                    gameDisplay.currentHazardOrMoney1_x_position--;
                    gameDisplay.currentHazardOrMoney1_y_position++;
                }
                if (gameDisplay.currentHazardOrMoney1_y_position > GameDisplay.d.getHeight() - 230) {
                    gameDisplay.currentHazardOrMoney1_x_position = (int) (GameDisplay.d.getWidth() + 1000);
                    gameDisplay.currentHazardOrMoney1w = 1;
                    gameDisplay.currentHazardOrMoney1h = 1;
                }
                if (gameDisplay.currentHazardOrMoney1_y_position > GameDisplay.d.getHeight() - 230 ||
                        gameDisplay.currentHazardOrMoney1_x_position < 50 ||
                        !gameDisplay.hugoSkiing.tic) {
                    gameDisplay.currentHazardOrMoney1_y_position = 8000;
                    //System.out.println("Off screen 1");
                    gameDisplay.currentHazardOrMoney1w = 1;
                    gameDisplay.currentHazardOrMoney1h = 1;
                }
            }
            if (gameDisplay.vanish4Faster) {
                gameDisplay.currentHazardOrMoney1_y_position += 3;
                gameDisplay.currentHazardOrMoney1_x_position -= 5;
                gameDisplay.currentHazardOrMoney1w += 3;
                gameDisplay.currentHazardOrMoney1h += 3;
            }
        }
    }
}

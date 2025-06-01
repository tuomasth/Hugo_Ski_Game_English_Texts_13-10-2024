package hugohiihto;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The game loop, reads the hazard array step by step when not paused, uses timer.
 * <p>
 * This is the most important part of the game logic.
 * <p>
 * Thanks to this for huge progress:
 * https://www.digitalocean.com/community/tutorials/java-timer-timertask-example
 */
public class GameLoop extends TimerTask {
    private final HugoSkiing hugoSkiing;
    private final GameDisplay gameDisplay;
    private boolean hasDoneOnce = false;
    private String compareString = "";

    public GameLoop(HugoSkiing hugoSkiing, GameDisplay gameDisplay) {
        this.hugoSkiing = hugoSkiing;
        this.gameDisplay = gameDisplay;
    }

    @Override
    /**
     * Running the time-based tasks.
     */
    public void run() {

        if (hugoSkiing.currentStateAtTheLevel >= 71 || hugoSkiing.gameOver) {
            this.cancel(); // we're at the finish line or it is game over, now the memory puzzle if alive
            gameDisplay.gamePaused = true;

            if (hugoSkiing.currentStateAtTheLevel >= 71 && gameDisplay.gamePaused) {
                gameDisplay.video = 7;
                gameDisplay.nextState = 6;
            }
        }
        if (gameDisplay.nextState == 2) {
            gameDisplay.currentGrid = 0;
            gameDisplay.gamePaused = true; // no gameplay during a video
        } else {
            if (hugoSkiing.currentStateAtTheLevel < 37) {
                File fileGameMusicH = new File("res/hiihtoaani.wav");
                try {
                    gameDisplay.clipH = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipH.open(AudioSystem.getAudioInputStream(fileGameMusicH));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) gameDisplay.clipH.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-11.0f); // volume setting for music or sound
                gameDisplay.clipH.start();
            }
        }

        if (!gameDisplay.gamePaused) {
            if (hugoSkiing.currentStateAtTheLevel < 70) {
                hasDoneOnce = false;
            }

            if (hugoSkiing.tic) {
                hugoSkiing.tic = false;
                System.out.println("\nTIC, location is " + hugoSkiing.currentStateAtTheLevel + ", next will be " + hugoSkiing.theFurthestThePlayerHasGot);
                if (hugoSkiing.theFurthestThePlayerHasGot > -1) {
                    if (compareString.charAt(gameDisplay.currentGrid) == 'E' ||
                            compareString.charAt(gameDisplay.currentGrid) == '1' ||
                            compareString.charAt(gameDisplay.currentGrid) == '2') {
                        gameDisplay.currentHazardOrMoney1_y_position += 4;
                        gameDisplay.currentHazardOrMoney2_y_position += 4;
                        gameDisplay.currentHazardOrMoney3_y_position += 4;
                        gameDisplay.currentHazardOrMoney4_y_position += 4;
                        gameDisplay.vanish4Faster = true;
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'F') {
                        hugoSkiing.currentStateAtTheLevel = 71;
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == '8') {
                        System.out.println("ENEMY HIT -1---- SNOWMAN" + ", line (from 0 to 3) is " + gameDisplay.currentGrid);
                        gameDisplay.gamePaused = true;
                        gameDisplay.video = 14;
                        gameDisplay.nextState = 2;
                        hugoSkiing.decreaseLives(gameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'o') {
                        System.out.println("ENEMY HIT --2--- SNOWBALL" + ", line (from 0 to 3) is " + gameDisplay.currentGrid);
                        gameDisplay.gamePaused = true;
                        gameDisplay.video = 15;
                        gameDisplay.nextState = 2;
                        hugoSkiing.decreaseLives(gameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'Q') {
                        System.out.println("ENEMY HIT ---3-- BOMB" + ", line (from 0 to 3) is " + gameDisplay.currentGrid);
                        gameDisplay.gamePaused = true;
                        gameDisplay.video = 16;
                        gameDisplay.nextState = 2;
                        hugoSkiing.decreaseLives(gameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'B') {
                        System.out.println("ENEMY HIT ----4- BEAVER" + ", line (from 0 to 3) is " + gameDisplay.currentGrid);
                        gameDisplay.gamePaused = true;
                        gameDisplay.video = 17;
                        gameDisplay.nextState = 2;
                        hugoSkiing.decreaseLives(gameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'S') {
                        System.out.println("SCYLLA BUTTON PRESS");
                        gameDisplay.gamePaused = true;
                        gameDisplay.video = 2;
                        gameDisplay.nextState = 2;

                        gameDisplay.clip4 = null; // clip variables are music or sound

                        if (gameDisplay.cheatBackflip180) {
                            File fileGameMusic2 = new File("res/music_FinnishHugo.wav");
                            try {
                                gameDisplay.clip4 = AudioSystem.getClip();
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                gameDisplay.clip4.open(AudioSystem.getAudioInputStream(fileGameMusic2));
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                            }
                            gameDisplay.clip4.start();
                        } else {
                            if (gameDisplay.number_of_lives <= 3) {
                                File fileGameMusic2 = new File("res/music_from_classic_skateboard.wav");
                                try {
                                    gameDisplay.clip4 = AudioSystem.getClip();
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    gameDisplay.clip4.open(AudioSystem.getAudioInputStream(fileGameMusic2));
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                                }
                                FloatControl gainControl =
                                        (FloatControl) gameDisplay.clip4.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-4.0f); // volume setting for music
                                gameDisplay.clip4.start();
                            } else { // popcorn music from the Finnish TV show's airplane Hugo
                                try {
                                    gameDisplay.clip1 = AudioSystem.getClip();
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    gameDisplay.clip1.open(AudioSystem.getAudioInputStream(gameDisplay.fileGameMusic1));
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                                }
                                FloatControl gainControl =
                                        (FloatControl) gameDisplay.clip1.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-15.0f); // volume for music, decreasing volume so Hugo's words can be heard
                                gameDisplay.clip1.start();
                            }
                        }
                    }

                    if (compareString.charAt(gameDisplay.currentGrid) == 'M') {
                        gameDisplay.currentHazardOrMoney1_y_position += 4;
                        gameDisplay.currentHazardOrMoney2_y_position += 4;
                        gameDisplay.currentHazardOrMoney3_y_position += 4;
                        gameDisplay.currentHazardOrMoney4_y_position += 4;
                        gameDisplay.vanish4Faster = true;
                        switch (gameDisplay.currentGrid) {
                            case 0 -> gameDisplay.currentHazardOrMoney1_y_position += 400;
                            case 1 -> gameDisplay.currentHazardOrMoney2_y_position += 400;
                            case 2 -> gameDisplay.currentHazardOrMoney3_y_position += 400;
                            case 3 -> gameDisplay.currentHazardOrMoney4_y_position += 400;
                        }

                        try {
                            gameDisplay.clipMoney = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clipMoney.open(AudioSystem.getAudioInputStream(gameDisplay.fileMoney));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clipMoney.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting
                        gameDisplay.clipMoney.start();

                        if (gameDisplay.cheatBackflip180) {
                            hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);

                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);

                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                        } else {
                            hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                            hugoSkiing.increaseScoreTens(gameDisplay.tens);
                            hugoSkiing.increaseScoreHundreds(gameDisplay.hundreds);
                            hugoSkiing.increaseScoreThousands(gameDisplay.thousands); // 1111 p -- then add 0 or 12 or 100 or 112 p (4 possible)
                            if (Math.random() < 0.4) {
                                hugoSkiing.increaseScoreHundreds(gameDisplay.hundreds); // 1211 p
                            }
                            if (Math.random() < 0.6) {
                                hugoSkiing.increaseScoreTens(gameDisplay.tens);   // 1123 p
                                hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                                hugoSkiing.increaseScoreOnes(gameDisplay.ones);   // max can be +1223 p at one time, min is +1111 p
                            }
                        }
                    }
                    if (compareString.charAt(gameDisplay.currentGrid) == 'F' && !hasDoneOnce) {
                        hasDoneOnce = true;
                        hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                        if (gameDisplay.number_of_lives >= 1) {
                            hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                            hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                            if (gameDisplay.number_of_lives >= 2) {
                                hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                                hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                                if (gameDisplay.number_of_lives >= 3) {
                                    hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                                    hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                                    if (gameDisplay.number_of_lives >= 4) {
                                        hugoSkiing.increaseScoreTenThousands(gameDisplay.tenThousands);
                                    }
                                }
                            }
                        }

                        try {
                            gameDisplay.clipScore = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clipScore.open(AudioSystem.getAudioInputStream(gameDisplay.fileScore));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clipScore.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume
                        gameDisplay.clipScore.start();
                    }
                }

            } else {
                hugoSkiing.tic = true;
                gameDisplay.vanish4Faster = false;
                if (hugoSkiing.currentStateAtTheLevel < 0 || (hugoSkiing.currentStateAtTheLevel == 14 || hugoSkiing.currentStateAtTheLevel == 25)) {
                    gameDisplay.currentHazardOrMoney1_y_position = 7000; // not even started yet,
                    gameDisplay.currentHazardOrMoney2_y_position = 7000; // if showing enemies, don't
                    gameDisplay.currentHazardOrMoney3_y_position = 7000;
                    gameDisplay.currentHazardOrMoney4_y_position = 7000;
                    gameDisplay.currentHazardOrMoney1_x_position = 4000;
                    gameDisplay.currentHazardOrMoney2_x_position = 4000;
                    gameDisplay.currentHazardOrMoney3_x_position = 4000;
                    gameDisplay.currentHazardOrMoney4_x_position = 4000;
                    gameDisplay.currentHazardOrMoney1w = 1;
                    gameDisplay.currentHazardOrMoney1h = 1;
                }
                gameDisplay.reset4positions();
                if (hugoSkiing.currentStateAtTheLevel < 70 && hugoSkiing.theFurthestThePlayerHasGot < 70) {
                    hugoSkiing.currentStateAtTheLevel++;
                    if (hugoSkiing.currentStateAtTheLevel >= hugoSkiing.theFurthestThePlayerHasGot) {
                        hugoSkiing.theFurthestThePlayerHasGot++;
                    }
                }
                System.out.println("TAC, location is " + hugoSkiing.currentStateAtTheLevel + ", next will be " + hugoSkiing.theFurthestThePlayerHasGot);

                if (hugoSkiing.currentStateAtTheLevel == 14 || hugoSkiing.currentStateAtTheLevel == 25) {
                    gameDisplay.currentHazardOrMoney1_x_position = 20;
                    gameDisplay.currentHazardOrMoney1_y_position = 30;
                }

                Set<Character> allowed = Set.of('E', 'M', '8', 'o', 'Q', 'B', '1', '2', 'S', 'F');
                for (int i = 0; i < hugoSkiing.haz.length; i++) {
                    if (hugoSkiing.theFurthestThePlayerHasGot == i) {
                        System.out.print(" ... ABOUT TO GIVE THE NEXT 4 with index " + i + " --- " + hugoSkiing.haz[i]);
                        compareString = hugoSkiing.haz[i];
                        String values = hugoSkiing.haz[i];

                        for (int j = 0; j < 4; j++) {
                            char c = values.charAt(j);
                            if (allowed.contains(c)) {
                                switch (j) {
                                    case 0 -> gameDisplay.setcurrentHazardOrMoney1(String.valueOf(c));
                                    case 1 -> gameDisplay.setcurrentHazardOrMoney2(String.valueOf(c));
                                    case 2 -> gameDisplay.setcurrentHazardOrMoney3(String.valueOf(c));
                                    case 3 -> gameDisplay.setcurrentHazardOrMoney4(String.valueOf(c));
                                }
                            }
                        }


                        if (!"FFFF".equals(hugoSkiing.haz[i])) {
                            hugoSkiing.haz[i] = "EEEE";
                        }
                    }
                }

            }
        } else {
            if (!hasDoneOnce && hugoSkiing.currentStateAtTheLevel > -2 && hugoSkiing.currentStateAtTheLevel < 70) {
                hugoSkiing.currentStateAtTheLevel--;
            }
            hasDoneOnce = true;
        }
    }
}

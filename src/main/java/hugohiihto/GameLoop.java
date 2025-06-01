package hugohiihto;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
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
    private final HugoSkiing hugoHiihto;
    boolean tic = true;
    boolean hasDoneOnce = false;
    String compareString = "";

    public GameLoop(HugoSkiing hugoHiihto) {
        this.hugoHiihto = hugoHiihto;
    }

    @Override
    /**
     * Running the time-based tasks.
     */
    public void run() {

        if (HugoSkiing.currentStateAtTheLevel >= 71 || HugoSkiing.gameOver) {
            this.cancel(); // we're at the finish line or it is game over, now the memory puzzle if alive
            GameDisplay.gamePaused = true;

            if (HugoSkiing.currentStateAtTheLevel >= 71 && GameDisplay.gamePaused) {
                GameDisplay.video = 7;
                GameDisplay.nextState = 6;
            }
        }
        if (GameDisplay.nextState == 2) {
            GameDisplay.currentGrid = 0;
            GameDisplay.gamePaused = true; // no gameplay during a video
        } else {
            if (HugoSkiing.currentStateAtTheLevel < 37) {
                File fileGameMusicH = new File("res/hiihtoaani.wav");
                try {
                    GameDisplay.clipH = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    GameDisplay.clipH.open(AudioSystem.getAudioInputStream(fileGameMusicH));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) GameDisplay.clipH.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-11.0f); // volume setting for music or sound
                GameDisplay.clipH.start();
            }
        }

        if (!GameDisplay.gamePaused) {
            if (HugoSkiing.currentStateAtTheLevel < 70) {
                hasDoneOnce = false;
            }

            if (tic) {
                tic = false;
                System.out.println("\nTIC, location is " + HugoSkiing.currentStateAtTheLevel + ", next will be " + HugoSkiing.theFurthestThePlayerHasGot);
                if (HugoSkiing.theFurthestThePlayerHasGot > -1) {
                    if (compareString.charAt(GameDisplay.currentGrid) == 'E' ||
                            compareString.charAt(GameDisplay.currentGrid) == '1' ||
                            compareString.charAt(GameDisplay.currentGrid) == '2') {
                        GameDisplay.currentHazardOrMoney1_y_position += 4;
                        GameDisplay.currentHazardOrMoney2_y_position += 4;
                        GameDisplay.currentHazardOrMoney3_y_position += 4;
                        GameDisplay.currentHazardOrMoney4_y_position += 4;
                        GameDisplay.vanish4Faster = true;
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'F') {
                        HugoSkiing.currentStateAtTheLevel = 71;
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == '8') {
                        System.out.println("ENEMY HIT -1---- SNOWMAN" + ", line (from 0 to 3) is " + GameDisplay.currentGrid);
                        GameDisplay.gamePaused = true;
                        GameDisplay.video = 14;
                        GameDisplay.nextState = 2;
                        HugoSkiing.decreaseLives(GameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'o') {
                        System.out.println("ENEMY HIT --2--- SNOWBALL" + ", line (from 0 to 3) is " + GameDisplay.currentGrid);
                        GameDisplay.gamePaused = true;
                        GameDisplay.video = 15;
                        GameDisplay.nextState = 2;
                        HugoSkiing.decreaseLives(GameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'Q') {
                        System.out.println("ENEMY HIT ---3-- BOMB" + ", line (from 0 to 3) is " + GameDisplay.currentGrid);
                        GameDisplay.gamePaused = true;
                        GameDisplay.video = 16;
                        GameDisplay.nextState = 2;
                        HugoSkiing.decreaseLives(GameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'B') {
                        System.out.println("ENEMY HIT ----4- BEAVER" + ", line (from 0 to 3) is " + GameDisplay.currentGrid);
                        GameDisplay.gamePaused = true;
                        GameDisplay.video = 17;
                        GameDisplay.nextState = 2;
                        HugoSkiing.decreaseLives(GameDisplay.number_of_lives);
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'S') {
                        System.out.println("SCYLLA BUTTON PRESS");
                        GameDisplay.gamePaused = true;
                        GameDisplay.video = 2;
                        GameDisplay.nextState = 2;

                        GameDisplay.clip4 = null; // clip variables are music or sound

                        if (GameDisplay.cheatBackflip180) {
                            File fileGameMusic2 = new File("res/music_FinnishHugo.wav");
                            try {
                                GameDisplay.clip4 = AudioSystem.getClip();
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                GameDisplay.clip4.open(AudioSystem.getAudioInputStream(fileGameMusic2));
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            GameDisplay.clip4.start();
                        } else {
                            if (GameDisplay.number_of_lives <= 3) {
                                File fileGameMusic2 = new File("res/music_from_classic_skateboard.wav");
                                try {
                                    GameDisplay.clip4 = AudioSystem.getClip();
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    GameDisplay.clip4.open(AudioSystem.getAudioInputStream(fileGameMusic2));
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                FloatControl gainControl =
                                        (FloatControl) GameDisplay.clip4.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-4.0f); // volume setting for music
                                GameDisplay.clip4.start();
                            } else { // popcorn music from the Finnish TV show's airplane Hugo
                                try {
                                    GameDisplay.clip1 = AudioSystem.getClip();
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    GameDisplay.clip1.open(AudioSystem.getAudioInputStream(GameDisplay.fileGameMusic1));
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                FloatControl gainControl =
                                        (FloatControl) GameDisplay.clip1.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-15.0f); // volume for music, decreasing volume so Hugo's words can be heard
                                GameDisplay.clip1.start();
                            }
                        }
                    }

                    if (compareString.charAt(GameDisplay.currentGrid) == 'M') {
                        GameDisplay.currentHazardOrMoney1_y_position += 4;
                        GameDisplay.currentHazardOrMoney2_y_position += 4;
                        GameDisplay.currentHazardOrMoney3_y_position += 4;
                        GameDisplay.currentHazardOrMoney4_y_position += 4;
                        GameDisplay.vanish4Faster = true;
                        if (GameDisplay.currentGrid == 0) {
                            GameDisplay.currentHazardOrMoney1_y_position += 400;
                        }
                        if (GameDisplay.currentGrid == 1) {
                            GameDisplay.currentHazardOrMoney2_y_position += 400;
                        }
                        if (GameDisplay.currentGrid == 2) {
                            GameDisplay.currentHazardOrMoney3_y_position += 400;
                        }
                        if (GameDisplay.currentGrid == 3) {
                            GameDisplay.currentHazardOrMoney4_y_position += 400;
                        }

                        try {
                            GameDisplay.clipMoney = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clipMoney.open(AudioSystem.getAudioInputStream(GameDisplay.fileMoney));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clipMoney.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting
                        GameDisplay.clipMoney.start();

                        if (GameDisplay.cheatBackflip180) {
                            HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);

                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);

                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                        } else {
                            HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                            HugoSkiing.increaseScoreTens(GameDisplay.tens);
                            HugoSkiing.increaseScoreHundreds(GameDisplay.hundreds);
                            HugoSkiing.increaseScoreThousands(GameDisplay.thousands); // 1111 p -- then add 0 or 12 or 100 or 112 p (4 possible)
                            if (Math.random() < 0.4) {
                                HugoSkiing.increaseScoreHundreds(GameDisplay.hundreds); // 1211 p
                            }
                            if (Math.random() < 0.6) {
                                HugoSkiing.increaseScoreTens(GameDisplay.tens);   // 1123 p
                                HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                                HugoSkiing.increaseScoreOnes(GameDisplay.ones);   // max can be +1223 p at one time, min is +1111 p
                            }
                        }
                    }
                    if (compareString.charAt(GameDisplay.currentGrid) == 'F' && !hasDoneOnce) {
                        hasDoneOnce = true;
                        HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                        if (GameDisplay.number_of_lives >= 1) {
                            HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                            HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                            if (GameDisplay.number_of_lives >= 2) {
                                HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                                HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                                if (GameDisplay.number_of_lives >= 3) {
                                    HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                                    HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                                    if (GameDisplay.number_of_lives >= 4) {
                                        HugoSkiing.increaseScoreTenThousands(GameDisplay.tenThousands);
                                    }
                                }
                            }
                        }

                        try {
                            GameDisplay.clipScore = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clipScore.open(AudioSystem.getAudioInputStream(GameDisplay.fileScore));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(HugoSkiing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clipScore.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume
                        GameDisplay.clipScore.start();
                    }
                }

            } else {
                tic = true;
                GameDisplay.vanish4Faster = false;
                if (HugoSkiing.currentStateAtTheLevel < 0 || (HugoSkiing.currentStateAtTheLevel == 14 || HugoSkiing.currentStateAtTheLevel == 25)) {
                    GameDisplay.currentHazardOrMoney1_y_position = 7000; // not even started yet,
                    GameDisplay.currentHazardOrMoney2_y_position = 7000; // if showing enemies, don't
                    GameDisplay.currentHazardOrMoney3_y_position = 7000;
                    GameDisplay.currentHazardOrMoney4_y_position = 7000;
                    GameDisplay.currentHazardOrMoney1_x_position = 4000;
                    GameDisplay.currentHazardOrMoney2_x_position = 4000;
                    GameDisplay.currentHazardOrMoney3_x_position = 4000;
                    GameDisplay.currentHazardOrMoney4_x_position = 4000;
                    GameDisplay.currentHazardOrMoney1w = 1;
                    GameDisplay.currentHazardOrMoney1h = 1;
                }
                GameDisplay.reset4positions();
                if (HugoSkiing.currentStateAtTheLevel < 70 && HugoSkiing.theFurthestThePlayerHasGot < 70) {
                    HugoSkiing.currentStateAtTheLevel++;
                    if (HugoSkiing.currentStateAtTheLevel >= HugoSkiing.theFurthestThePlayerHasGot) {
                        HugoSkiing.theFurthestThePlayerHasGot++;
                    }
                }
                System.out.println("TAC, location is " + HugoSkiing.currentStateAtTheLevel + ", next will be " + HugoSkiing.theFurthestThePlayerHasGot);

                if (HugoSkiing.currentStateAtTheLevel == 14 || HugoSkiing.currentStateAtTheLevel == 25) {
                    GameDisplay.currentHazardOrMoney1_x_position = 20;
                    GameDisplay.currentHazardOrMoney1_y_position = 30;
                }

                for (int i = 0; i < hugoHiihto.haz.length; i++) {
                    if (HugoSkiing.theFurthestThePlayerHasGot == i) {
                        System.out.print(" ... ABOUT TO GIVE THE NEXT 4 with index " + i + " --- " + hugoHiihto.haz[i]);
                        compareString = hugoHiihto.haz[i];
                        String values = hugoHiihto.haz[i];
                        if (values.charAt(0) == 'E' ||
                                values.charAt(0) == 'M' ||
                                values.charAt(0) == '8' ||
                                values.charAt(0) == 'o' ||
                                values.charAt(0) == 'Q' ||
                                values.charAt(0) == 'B' ||
                                values.charAt(0) == '1' ||
                                values.charAt(0) == '2' ||
                                values.charAt(0) == 'S' ||
                                values.charAt(0) == 'F') {
                            GameDisplay.setcurrentHazardOrMoney1(String.valueOf(values.charAt(0)));
                        }
                        if (values.charAt(1) == 'E' ||
                                values.charAt(1) == 'M' ||
                                values.charAt(1) == '8' ||
                                values.charAt(1) == 'o' ||
                                values.charAt(1) == 'Q' ||
                                values.charAt(1) == 'B' ||
                                values.charAt(1) == '1' ||
                                values.charAt(1) == '2' ||
                                values.charAt(1) == 'S' ||
                                values.charAt(1) == 'F') {
                            GameDisplay.setcurrentHazardOrMoney2(String.valueOf(values.charAt(1)));
                        }
                        if (values.charAt(2) == 'E' ||
                                values.charAt(2) == 'M' ||
                                values.charAt(2) == '8' ||
                                values.charAt(2) == 'o' ||
                                values.charAt(2) == 'Q' ||
                                values.charAt(2) == 'B' ||
                                values.charAt(2) == '1' ||
                                values.charAt(2) == '2' ||
                                values.charAt(2) == 'S' ||
                                values.charAt(2) == 'F') {
                            GameDisplay.setcurrentHazardOrMoney3(String.valueOf(values.charAt(2)));
                        }
                        if (values.charAt(3) == 'E' ||
                                values.charAt(3) == 'M' ||
                                values.charAt(3) == '8' ||
                                values.charAt(3) == 'o' ||
                                values.charAt(3) == 'Q' ||
                                values.charAt(3) == 'B' ||
                                values.charAt(3) == '1' ||
                                values.charAt(3) == '2' ||
                                values.charAt(3) == 'S' ||
                                values.charAt(3) == 'F') {
                            GameDisplay.setcurrentHazardOrMoney4(String.valueOf(values.charAt(3)));
                        }

                        if (!"FFFF".equals(hugoHiihto.haz[i])) {
                            hugoHiihto.haz[i] = "EEEE";
                        }
                    }
                }

            }
        } else {
            if (!hasDoneOnce && HugoSkiing.currentStateAtTheLevel > -2 && HugoSkiing.currentStateAtTheLevel < 70) {
                HugoSkiing.currentStateAtTheLevel--;
            }
            hasDoneOnce = true;
        }
    }
}

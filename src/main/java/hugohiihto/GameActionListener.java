package hugohiihto;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Key listeners when pressing buttons. Please call only once!
 * Else, input bugs will occur with multiple presses.
 */
public class GameActionListener extends KeyAdapter {

    private final GameDisplay gameDisplay;
    private final HugoSkiing hugoSkiing;

    public GameActionListener(HugoSkiing hugoSkiing) {
        this.hugoSkiing = hugoSkiing;
        this.gameDisplay = hugoSkiing.getGameDisplay();
    }

    /**
     * Key pressed event when the player gives input.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) {

            System.gc();    // run garbage collector
            System.exit(0); // and exit if ESC pressed
        }

        if (keyCode == KeyEvent.VK_ENTER) {

            if (gameDisplay.video != 3) {
                if (gameDisplay.videoimg != null) {  // so videos will always start at the beginning
                    gameDisplay.videoimg.flush();
                    gameDisplay.videoimg = null;
                }
            }

            // Mutings
            if (gameDisplay.clip0 != null) {
                if (gameDisplay.clip0.isRunning()) {
                    gameDisplay.clip0.stop();
                }
            }
            /*
            if(clip1 != null) {
                if(clip1.isRunning()) {
                    clip1.stop();
                }
            }
            */
            if (gameDisplay.clip2 != null) {
                if (gameDisplay.clip2.isRunning()) {
                    gameDisplay.clip2.stop();
                }
            }
            if (gameDisplay.clip3 != null) {
                if (gameDisplay.clip3.isRunning()) {
                    gameDisplay.clip3.stop();
                }
            }
            if (gameDisplay.mediaPlayer != null && gameDisplay.video != 2 && gameDisplay.video != 3) {
                gameDisplay.mediaPlayer.stop();
            }
        }


        if ((double) gameDisplay.gameState < 0.1) {
            if (keyCode == KeyEvent.VK_ENTER) {

                gameDisplay.constructFrames(gameDisplay.gameState);

                if (gameDisplay.clip1 != null) {
                    if (gameDisplay.clip1.isRunning()) {
                        gameDisplay.clip1.stop(); //popcorn
                    }
                }
                if (gameDisplay.clip4 != null) {
                    if (gameDisplay.clip4.isRunning()) {
                        gameDisplay.clip4.stop(); //skateboard
                    }
                }

                gameDisplay.video = 0;
                gameDisplay.nextState = 6;
            }
        } else if ((double) gameDisplay.gameState > 0.9 && (double) gameDisplay.gameState < 1.1) {
            if (keyCode == KeyEvent.VK_ENTER) {

                gameDisplay.videoimg = new ImageIcon("res/scylla_intro_s.gif").getImage();
                gameDisplay.videoimg = new ImageIcon("res/start_hoplaa_s.gif").getImage();
                gameDisplay.videoimg.setAccelerationPriority((float) 1.0); // from 0-> lowest to 1-> highest

                if (hugoSkiing.currentStateAtTheLevel >= 71 && (gameDisplay.pulled_rope_3 || gameDisplay.pulled_rope_1)) {
                    gameDisplay.nextState = 0;
                    hugoSkiing.currentStateAtTheLevel = -5;
                } else {
                    if (hugoSkiing.gameOver) {
                        if (hugoSkiing.timerTask != null) {
                            hugoSkiing.timerTask.cancel();
                        }
                    }

                    gameDisplay.reset();

                    gameDisplay.video = 1;
                    gameDisplay.nextState = 6;
                    gameDisplay.constructFrames(gameDisplay.gameState);
                }
            }
            if ((keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) && gameDisplay.key5) {
                gameDisplay.key6 = true;
            }
            if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) {
                gameDisplay.key1 = true;
            }
            if ((keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) && gameDisplay.key9) {
                gameDisplay.key10 = true;
            }
            if ((keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) && gameDisplay.key1) {
                gameDisplay.key2 = true;
            }
            if ((keyCode == KeyEvent.VK_0 || keyCode == KeyEvent.VK_NUMPAD0) && gameDisplay.key3) {
                gameDisplay.key4 = true;
            }
            if ((keyCode == KeyEvent.VK_0 || keyCode == KeyEvent.VK_NUMPAD0) && gameDisplay.key2) {
                gameDisplay.key3 = true;
            }
            if ((keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) && gameDisplay.key6) {
                gameDisplay.key7 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && gameDisplay.key11) {
                gameDisplay.key12 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && gameDisplay.key10) {
                gameDisplay.key11 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && gameDisplay.key8) {
                gameDisplay.key9 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && gameDisplay.key7) {
                gameDisplay.key8 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && gameDisplay.key4) {
                gameDisplay.key5 = true;
            }
            if (gameDisplay.cheatBackflip180) {
                try {
                    gameDisplay.clipMoney = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(hugoSkiing.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipMoney.open(AudioSystem.getAudioInputStream(gameDisplay.fileMoney));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(hugoSkiing.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) gameDisplay.clipMoney.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-0.0f); // volume setting
                gameDisplay.clipMoney.start();
            }
            if (gameDisplay.key12) { // Activating the cheat mode!
                gameDisplay.cheatBackflip180 = true;
            }
        } else if ((double) gameDisplay.gameState > 1.9 && (double) gameDisplay.gameState < 2.1) {
            // 0 = Scylla intro,          1 = Hugo's first words hoplaa nyt hommiin,
            // 2 = Scylla button,         3 = three ropes intro,
            // 4 = Hugo asks for two,     5 = two chosen correctly,
            // 6 = made a wrong choice,   7 = (knock) Hugo finished the skiing,
            // 8 = [knock] wake up pahvi, 9 = (knock) now the last troll going,
            // 10 = (knock) game over,    11 = rope #1,
            // 12 = rope #2,              13 = rope #3,
            // 14 = snowman,              15 = snowball,
            // 16 = bomb,                 17 = beaver.

            if (gameDisplay.video == 0) {
                if (keyCode == KeyEvent.VK_ENTER) {

                    if (hugoSkiing.currentStateAtTheLevel >= 71 && hugoSkiing.gameOver == false) {
                        try {
                            gameDisplay.clip3 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clip3.open(AudioSystem.getAudioInputStream(gameDisplay.fileGameMusic2));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clip3.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        gameDisplay.clip3.start();
                    } else {
                        try {
                            gameDisplay.clip0 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clip0.open(AudioSystem.getAudioInputStream(gameDisplay.fileGameMusic0));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clip0.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        gameDisplay.clip0.start();
                    }

                    gameDisplay.gamePaused = true;
                    gameDisplay.nextState = 1;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 1) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 3; // to the actual game
                    gameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 2) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 3;
                    gameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 3) {
                if (gameDisplay.clip1 != null) {
                    if (gameDisplay.clip1.isRunning()) {
                        gameDisplay.clip1.stop();
                    }
                }

                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
                if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) {
                    if (gameDisplay.mediaPlayer != null) {
                        gameDisplay.mediaPlayer.stop();
                    }
                    gameDisplay.pulled_rope_1 = true;
                    gameDisplay.pulled_rope_2 = false;
                    gameDisplay.pulled_rope_3 = false;
                    System.out.println("1 chosen!");

                    // 1004 points like in the good old times (at least in some classic games)
                    hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);

                    gameDisplay.video = 11;
                    gameDisplay.nextState = 6; // use state 6 or higher when moving from a video to another video
                }
                if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) {
                    if (gameDisplay.mediaPlayer != null) {
                        gameDisplay.mediaPlayer.stop();
                    }
                    gameDisplay.pulled_rope_2 = true;
                    gameDisplay.pulled_rope_1 = false;
                    gameDisplay.pulled_rope_3 = false;
                    System.out.println("2 chosen!");
                    hugoSkiing.currentStateAtTheLevel = -5;
                    gameDisplay.video = 12;
                    gameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) {
                    if (gameDisplay.mediaPlayer != null) {
                        gameDisplay.mediaPlayer.stop();
                    }
                    gameDisplay.pulled_rope_3 = true;
                    gameDisplay.pulled_rope_1 = false;
                    gameDisplay.pulled_rope_2 = false;
                    System.out.println("3 chosen!");

                    // 2026 points = the best ending score, more than just a money bag
                    hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                    hugoSkiing.increaseScoreThousands(gameDisplay.thousands);
                    hugoSkiing.increaseScoreTens(gameDisplay.tens);
                    hugoSkiing.increaseScoreTens(gameDisplay.tens);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);
                    hugoSkiing.increaseScoreOnes(gameDisplay.ones);

                    gameDisplay.video = 13;
                    gameDisplay.nextState = 6;
                }
            }
            if (gameDisplay.video == 4) {    // hugo asks 2
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 4;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 5) {    // 2 right
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (gameDisplay.clip1 != null) {
                        if (gameDisplay.clip1.isRunning()) {
                            gameDisplay.clip1.stop();//popcorn stop
                        }
                    }
                    if (gameDisplay.clip4 != null) {
                        if (gameDisplay.clip4.isRunning()) {
                            gameDisplay.clip4.stop();//skateboard stop
                        }
                    }

                    gameDisplay.video = 3;
                    gameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 6) {    // wrong
                if (keyCode == KeyEvent.VK_ENTER) {
                    hugoSkiing.gameOver = true;
                    gameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 7) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.video = 4;
                    gameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 8) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 3; // to the actual game
                    gameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 9) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 3; // to the actual game
                    gameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 10) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    // cancel all timertasks!
                    hugoSkiing.gameOver = true;
                    gameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 11) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 12) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 13) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    gameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 14) {
                gameDisplay.currentGrid = 0;
                gameDisplay.currentHazardOrMoney1_y_position += 800;
                gameDisplay.currentHazardOrMoney2_y_position += 800;
                gameDisplay.currentHazardOrMoney3_y_position += 800;
                gameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (gameDisplay.number_of_lives >= 2) {
                        gameDisplay.video = 8;
                        gameDisplay.nextState = 6;
                    } else {
                        if (gameDisplay.number_of_lives >= 1) {
                            gameDisplay.video = 9;
                            gameDisplay.nextState = 6;
                        } else {
                            gameDisplay.video = 10;
                            gameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 15) {
                gameDisplay.currentGrid = 0;
                gameDisplay.currentHazardOrMoney1_y_position += 800;
                gameDisplay.currentHazardOrMoney2_y_position += 800;
                gameDisplay.currentHazardOrMoney3_y_position += 800;
                gameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (gameDisplay.number_of_lives >= 2) {
                        gameDisplay.video = 8;
                        gameDisplay.nextState = 6;
                    } else {
                        if (gameDisplay.number_of_lives >= 1) {
                            gameDisplay.video = 9;
                            gameDisplay.nextState = 6;
                        } else {
                            gameDisplay.video = 10;
                            gameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 16) {
                gameDisplay.currentGrid = 0;
                gameDisplay.currentHazardOrMoney1_y_position += 800;
                gameDisplay.currentHazardOrMoney2_y_position += 800;
                gameDisplay.currentHazardOrMoney3_y_position += 800;
                gameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (gameDisplay.number_of_lives >= 2) {
                        gameDisplay.video = 8;
                        gameDisplay.nextState = 6;
                    } else {
                        if (gameDisplay.number_of_lives >= 1) {
                            gameDisplay.video = 9;
                            gameDisplay.nextState = 6;
                        } else {
                            gameDisplay.video = 10;
                            gameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (gameDisplay.video == 17) {
                gameDisplay.currentGrid = 0;
                gameDisplay.currentHazardOrMoney1_y_position += 800;
                gameDisplay.currentHazardOrMoney2_y_position += 800;
                gameDisplay.currentHazardOrMoney3_y_position += 800;
                gameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (gameDisplay.number_of_lives >= 2) {
                        gameDisplay.video = 8;
                        gameDisplay.nextState = 6;
                    } else {
                        if (gameDisplay.number_of_lives >= 1) {
                            gameDisplay.video = 9;
                            gameDisplay.nextState = 6;
                        } else {
                            gameDisplay.video = 10;
                            gameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
        } else if ((double) gameDisplay.gameState > 2.9 && (double) gameDisplay.gameState < 3.1) {

            gameDisplay.maxW = gameDisplay.d.width - 220;

            if ((keyCode == KeyEvent.VK_LEFT && keyCode != KeyEvent.VK_RIGHT) ||
                    (keyCode == KeyEvent.VK_4 && !(keyCode == KeyEvent.VK_6)) ||
                    (keyCode == KeyEvent.VK_NUMPAD4 && !(keyCode == KeyEvent.VK_NUMPAD6))) {
                if (!gameDisplay.gamePaused) {
                    if (gameDisplay.x <= -25) {
                    } else {
                        if (gameDisplay.currentGrid >= 1) {
                            gameDisplay.currentGrid--;
                        }
                    }
                }
            } else if ((keyCode == KeyEvent.VK_RIGHT && keyCode != KeyEvent.VK_LEFT) ||
                    (keyCode == KeyEvent.VK_6 && !(keyCode == KeyEvent.VK_4)) ||
                    (keyCode == KeyEvent.VK_NUMPAD6 && !(keyCode == KeyEvent.VK_NUMPAD4))) {
                if (!gameDisplay.gamePaused) {
                    if (gameDisplay.x >= gameDisplay.maxW) {
                    } else {
                        if (gameDisplay.currentGrid <= 2) {
                            gameDisplay.currentGrid++;
                        }
                    }
                }
            }


            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.pausedWithEnter = true;
                if (!gameDisplay.gamePaused) {
                    gameDisplay.gamePaused = true;
                } else {
                    gameDisplay.pausedWithEnter = false;
                    gameDisplay.gamePaused = false;
                }
            }
        } else if ((double) gameDisplay.gameState > 3.9 && (double) gameDisplay.gameState < 4.1) {
            //keyCode = e.getKeyCode();
            // currentlyAllCorrect = true;
            if (keyCode == KeyEvent.VK_NUMPAD1 || keyCode == KeyEvent.VK_1) { // 1
                if (!gameDisplay.secondPhase) {
                    if (gameDisplay.thingsToRemember.charAt(0) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(0) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(0) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(0) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(0) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(0) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    gameDisplay.secondPhase = true;
                } else {
                    if (gameDisplay.thingsToRemember.charAt(3) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(3) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(3) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(3) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(3) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(3) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (gameDisplay.currentlyAllCorrect) {
                        gameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }
            if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_2) { // 2
                if (!gameDisplay.secondPhase) {
                    if (gameDisplay.thingsToRemember.charAt(1) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(1) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(1) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(1) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(1) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(1) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    gameDisplay.secondPhase = true;
                } else {
                    if (gameDisplay.thingsToRemember.charAt(4) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(4) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(4) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(4) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(4) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(4) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (gameDisplay.currentlyAllCorrect) {
                        gameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }
            if (keyCode == KeyEvent.VK_NUMPAD3 || keyCode == KeyEvent.VK_3) { // 3
                if (!gameDisplay.secondPhase) {
                    if (gameDisplay.thingsToRemember.charAt(2) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(2) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(2) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(2) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(2) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(2) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    gameDisplay.secondPhase = true;
                } else {
                    if (gameDisplay.thingsToRemember.charAt(5) == 'A' || // if caps, then correct
                            gameDisplay.thingsToRemember.charAt(5) == 'B' ||
                            gameDisplay.thingsToRemember.charAt(5) == 'C' ||
                            gameDisplay.thingsToRemember.charAt(5) == 'D' ||
                            gameDisplay.thingsToRemember.charAt(5) == 'H' ||
                            gameDisplay.thingsToRemember.charAt(5) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        gameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (gameDisplay.currentlyAllCorrect) {
                        gameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }

            if ((gameDisplay.currentlyAllCorrect && (
                    keyCode == KeyEvent.VK_1 ||
                            keyCode == KeyEvent.VK_2 ||
                            keyCode == KeyEvent.VK_3 ||
                            keyCode == KeyEvent.VK_NUMPAD1 ||
                            keyCode == KeyEvent.VK_NUMPAD2 ||
                            keyCode == KeyEvent.VK_NUMPAD3))) {
                try {
                    gameDisplay.clipCorrect = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipCorrect.open(AudioSystem.getAudioInputStream(gameDisplay.fileCorrect));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) gameDisplay.clipCorrect.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-0.0f); // volume setting if wanted
                gameDisplay.clipCorrect.start();
            }
            if (!gameDisplay.currentlyAllCorrect) {
                if (!gameDisplay.allCorrectInTheEnd) {
                    System.out.println("Wrong guess, not proceeding to ropes!");

                    gameDisplay.nextState = 5;
                }
            }
            if (gameDisplay.allCorrectInTheEnd) {
                System.out.println("Proceeding to ropes!");
                gameDisplay.video = 3;
                gameDisplay.nextState = 2;
            }
        } else if ((double) gameDisplay.gameState > 4.9 && (double) gameDisplay.gameState < 5.1) {
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gameDisplay.pulled_rope_1 || gameDisplay.pulled_rope_3) {
                    hugoSkiing.currentStateAtTheLevel = 71;
                    gameDisplay.nextState = 1;

                    if (hugoSkiing.currentStateAtTheLevel >= 71) {
                        try {
                            gameDisplay.clip3 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clip3.open(AudioSystem.getAudioInputStream(gameDisplay.fileGameMusic2));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clip3.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        gameDisplay.clip3.start();
                    } else {
                        try {
                            gameDisplay.clip0 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            gameDisplay.clip0.open(AudioSystem.getAudioInputStream(gameDisplay.fileGameMusic0));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) gameDisplay.clip0.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        gameDisplay.clip0.start();
                    }
                } else {
                    gameDisplay.nextState = 0;
                }
            }
        } else if ((double) gameDisplay.gameState >= 5.1) {
            gameDisplay.nextState = 2;
            System.out.println(" --- keyPressed --- to state " + gameDisplay.nextState);
        }
    }

    /**
     * When releasing the left/right button in state 3. Plays a sound effect.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (!gameDisplay.gamePaused && gameDisplay.gameState == 3) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                try {
                    gameDisplay.clipChangeGrid = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) gameDisplay.clipChangeGrid.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-23.0f); // volume setting, decreasing sfx volume a bit
                gameDisplay.clipChangeGrid.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                try {
                    gameDisplay.clipChangeGrid4 = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid4.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid4));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                gameDisplay.clipChangeGrid4.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                try {
                    gameDisplay.clipChangeGrid6 = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid6.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid6));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
                }
                gameDisplay.clipChangeGrid6.start();
            }
        }
    }
}

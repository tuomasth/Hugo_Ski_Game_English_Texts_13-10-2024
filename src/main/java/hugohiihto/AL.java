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
public class AL extends KeyAdapter {

    private final GameDisplay gameDisplay;

    public AL(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    /**
     * Key pressed event when player gives input.
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

            if (GameDisplay.video != 3) {
                if (gameDisplay.videoimg != null) {  // so videos will always start at the beginning
                    gameDisplay.videoimg.flush();
                    gameDisplay.videoimg = null;
                }
            }

            // Mutings
            if (GameDisplay.clip0 != null) {
                if (GameDisplay.clip0.isRunning()) {
                    GameDisplay.clip0.stop();
                }
            }
            /*
            if(clip1 != null) {
                if(clip1.isRunning()) {
                    clip1.stop();
                }
            }
            */
            if (GameDisplay.clip2 != null) {
                if (GameDisplay.clip2.isRunning()) {
                    GameDisplay.clip2.stop();
                }
            }
            if (GameDisplay.clip3 != null) {
                if (GameDisplay.clip3.isRunning()) {
                    GameDisplay.clip3.stop();
                }
            }
            if (GameDisplay.mediaPlayer != null && GameDisplay.video != 2 && GameDisplay.video != 3) {
                GameDisplay.mediaPlayer.stop();
            }
        }


        if ((double) gameDisplay.game_state < 0.1) {
            if (keyCode == KeyEvent.VK_ENTER) {

                gameDisplay.constructFrames(gameDisplay.game_state);

                if (GameDisplay.clip1 != null) {
                    if (GameDisplay.clip1.isRunning()) {
                        GameDisplay.clip1.stop(); //popcorn
                    }
                }
                if (GameDisplay.clip4 != null) {
                    if (GameDisplay.clip4.isRunning()) {
                        GameDisplay.clip4.stop(); //skateboard
                    }
                }

                GameDisplay.video = 0;
                GameDisplay.nextState = 6;
            }
        } else if ((double) gameDisplay.game_state > 0.9 && (double) gameDisplay.game_state < 1.1) {
            if (keyCode == KeyEvent.VK_ENTER) {

                gameDisplay.videoimg = new ImageIcon("res/scylla_intro_s.gif").getImage();
                gameDisplay.videoimg = new ImageIcon("res/start_hoplaa_s.gif").getImage();
                gameDisplay.videoimg.setAccelerationPriority((float) 1.0); // from 0-> lowest to 1-> highest


                if (HugoSkiing.currentStateAtTheLevel >= 71 && (GameDisplay.pulled_rope_3 || GameDisplay.pulled_rope_1)) {
                    GameDisplay.nextState = 0;
                    HugoSkiing.currentStateAtTheLevel = -5;
                } else {
                    if (HugoSkiing.gameOver) {
                        if (HugoSkiing.timerTask != null) {
                            HugoSkiing.timerTask.cancel();
                        }
                    }

                    gameDisplay.reset();

                    GameDisplay.video = 1;
                    GameDisplay.nextState = 6;
                    gameDisplay.constructFrames(gameDisplay.game_state);
                }
            }
            if ((keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) && GameDisplay.key5) {
                GameDisplay.key6 = true;
            }
            if (keyCode == KeyEvent.VK_9 || keyCode == KeyEvent.VK_NUMPAD9) {
                GameDisplay.key1 = true;
            }
            if ((keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) && GameDisplay.key9) {
                GameDisplay.key10 = true;
            }
            if ((keyCode == KeyEvent.VK_7 || keyCode == KeyEvent.VK_NUMPAD7) && GameDisplay.key1) {
                GameDisplay.key2 = true;
            }
            if ((keyCode == KeyEvent.VK_0 || keyCode == KeyEvent.VK_NUMPAD0) && GameDisplay.key3) {
                GameDisplay.key4 = true;
            }
            if ((keyCode == KeyEvent.VK_0 || keyCode == KeyEvent.VK_NUMPAD0) && GameDisplay.key2) {
                GameDisplay.key3 = true;
            }
            if ((keyCode == KeyEvent.VK_5 || keyCode == KeyEvent.VK_NUMPAD5) && GameDisplay.key6) {
                GameDisplay.key7 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && GameDisplay.key11) {
                GameDisplay.key12 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && GameDisplay.key10) {
                GameDisplay.key11 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && GameDisplay.key8) {
                GameDisplay.key9 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && GameDisplay.key7) {
                GameDisplay.key8 = true;
            }
            if ((keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) && GameDisplay.key4) {
                GameDisplay.key5 = true;
            }
            if (GameDisplay.cheatBackflip180) {
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
            }
            if (GameDisplay.key12) { // Activating the cheat mode!
                GameDisplay.cheatBackflip180 = true;
            }
        } else if ((double) gameDisplay.game_state > 1.9 && (double) gameDisplay.game_state < 2.1) {
            // 0 = Scylla intro,          1 = Hugo's first words hoplaa nyt hommiin,
            // 2 = Scylla button,         3 = three ropes intro,
            // 4 = Hugo asks for two,     5 = two chosen correctly,
            // 6 = made a wrong choice,   7 = (knock) Hugo finished the skiing,
            // 8 = [knock] wake up pahvi, 9 = (knock) now the last troll going,
            // 10 = (knock) game over,    11 = rope #1,
            // 12 = rope #2,              13 = rope #3,
            // 14 = snowman,              15 = snowball,
            // 16 = bomb,                 17 = beaver.

            if (GameDisplay.video == 0) {
                if (keyCode == KeyEvent.VK_ENTER) {

                    if (HugoSkiing.currentStateAtTheLevel >= 71 && HugoSkiing.gameOver == false) {
                        try {
                            GameDisplay.clip3 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clip3.open(AudioSystem.getAudioInputStream(GameDisplay.fileGameMusic2));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clip3.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        GameDisplay.clip3.start();
                    } else {
                        try {
                            GameDisplay.clip0 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clip0.open(AudioSystem.getAudioInputStream(GameDisplay.fileGameMusic0));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clip0.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        GameDisplay.clip0.start();
                    }

                    GameDisplay.gamePaused = true;
                    GameDisplay.nextState = 1;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 1) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 3; // to the actual game
                    GameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 2) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 3;
                    GameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 3) {
                if (GameDisplay.clip1 != null) {
                    if (GameDisplay.clip1.isRunning()) {
                        GameDisplay.clip1.stop();
                    }
                }

                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
                if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) {
                    if (GameDisplay.mediaPlayer != null) {
                        GameDisplay.mediaPlayer.stop();
                    }
                    GameDisplay.pulled_rope_1 = true;
                    GameDisplay.pulled_rope_2 = false;
                    GameDisplay.pulled_rope_3 = false;
                    System.out.println("1 chosen!");

                    // 1004 points like in the good old times (at least in some classic games)
                    HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);

                    GameDisplay.video = 11;
                    GameDisplay.nextState = 6; // use state 6 or higher when moving from a video to another video
                }
                if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) {
                    if (GameDisplay.mediaPlayer != null) {
                        GameDisplay.mediaPlayer.stop();
                    }
                    GameDisplay.pulled_rope_2 = true;
                    GameDisplay.pulled_rope_1 = false;
                    GameDisplay.pulled_rope_3 = false;
                    System.out.println("2 chosen!");
                    HugoSkiing.currentStateAtTheLevel = -5;
                    GameDisplay.video = 12;
                    GameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) {
                    if (GameDisplay.mediaPlayer != null) {
                        GameDisplay.mediaPlayer.stop();
                    }
                    GameDisplay.pulled_rope_3 = true;
                    GameDisplay.pulled_rope_1 = false;
                    GameDisplay.pulled_rope_2 = false;
                    System.out.println("3 chosen!");

                    // 2026 points = the best ending score, more than just a money bag
                    HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                    HugoSkiing.increaseScoreThousands(GameDisplay.thousands);
                    HugoSkiing.increaseScoreTens(GameDisplay.tens);
                    HugoSkiing.increaseScoreTens(GameDisplay.tens);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);
                    HugoSkiing.increaseScoreOnes(GameDisplay.ones);

                    GameDisplay.video = 13;
                    GameDisplay.nextState = 6;
                }
            }
            if (GameDisplay.video == 4) {    // hugo asks 2
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 4;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 5) {    // 2 right
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (GameDisplay.clip1 != null) {
                        if (GameDisplay.clip1.isRunning()) {
                            GameDisplay.clip1.stop();//popcorn stop
                        }
                    }
                    if (GameDisplay.clip4 != null) {
                        if (GameDisplay.clip4.isRunning()) {
                            GameDisplay.clip4.stop();//skateboard stop
                        }
                    }

                    GameDisplay.video = 3;
                    GameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 6) {    // wrong
                if (keyCode == KeyEvent.VK_ENTER) {
                    HugoSkiing.gameOver = true;
                    GameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 7) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.video = 4;
                    GameDisplay.nextState = 6;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 8) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 3; // to the actual game
                    GameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 9) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 3; // to the actual game
                    GameDisplay.gamePaused = false;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 10) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    // cancel all timertasks!
                    HugoSkiing.gameOver = true;
                    GameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 11) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 12) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 13) {
                if (keyCode == KeyEvent.VK_ENTER) {
                    GameDisplay.nextState = 5;
                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 14) {
                GameDisplay.currentGrid = 0;
                GameDisplay.currentHazardOrMoney1_y_position += 800;
                GameDisplay.currentHazardOrMoney2_y_position += 800;
                GameDisplay.currentHazardOrMoney3_y_position += 800;
                GameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (GameDisplay.number_of_lives >= 2) {
                        GameDisplay.video = 8;
                        GameDisplay.nextState = 6;
                    } else {
                        if (GameDisplay.number_of_lives >= 1) {
                            GameDisplay.video = 9;
                            GameDisplay.nextState = 6;
                        } else {
                            GameDisplay.video = 10;
                            GameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 15) {
                GameDisplay.currentGrid = 0;
                GameDisplay.currentHazardOrMoney1_y_position += 800;
                GameDisplay.currentHazardOrMoney2_y_position += 800;
                GameDisplay.currentHazardOrMoney3_y_position += 800;
                GameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (GameDisplay.number_of_lives >= 2) {
                        GameDisplay.video = 8;
                        GameDisplay.nextState = 6;
                    } else {
                        if (GameDisplay.number_of_lives >= 1) {
                            GameDisplay.video = 9;
                            GameDisplay.nextState = 6;
                        } else {
                            GameDisplay.video = 10;
                            GameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 16) {
                GameDisplay.currentGrid = 0;
                GameDisplay.currentHazardOrMoney1_y_position += 800;
                GameDisplay.currentHazardOrMoney2_y_position += 800;
                GameDisplay.currentHazardOrMoney3_y_position += 800;
                GameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (GameDisplay.number_of_lives >= 2) {
                        GameDisplay.video = 8;
                        GameDisplay.nextState = 6;
                    } else {
                        if (GameDisplay.number_of_lives >= 1) {
                            GameDisplay.video = 9;
                            GameDisplay.nextState = 6;
                        } else {
                            GameDisplay.video = 10;
                            GameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
            if (GameDisplay.video == 17) {
                GameDisplay.currentGrid = 0;
                GameDisplay.currentHazardOrMoney1_y_position += 800;
                GameDisplay.currentHazardOrMoney2_y_position += 800;
                GameDisplay.currentHazardOrMoney3_y_position += 800;
                GameDisplay.currentHazardOrMoney4_y_position += 800;
                if (keyCode == KeyEvent.VK_ENTER) {
                    if (GameDisplay.number_of_lives >= 2) {
                        GameDisplay.video = 8;
                        GameDisplay.nextState = 6;
                    } else {
                        if (GameDisplay.number_of_lives >= 1) {
                            GameDisplay.video = 9;
                            GameDisplay.nextState = 6;
                        } else {
                            GameDisplay.video = 10;
                            GameDisplay.nextState = 6;
                        }
                    }

                }
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    System.gc();
                    System.exit(0);
                }
            }
        } else if ((double) gameDisplay.game_state > 2.9 && (double) gameDisplay.game_state < 3.1) {

            gameDisplay.maxW = GameDisplay.d.width - 220;

            if ((keyCode == KeyEvent.VK_LEFT && keyCode != KeyEvent.VK_RIGHT) ||
                    (keyCode == KeyEvent.VK_4 && !(keyCode == KeyEvent.VK_6)) ||
                    (keyCode == KeyEvent.VK_NUMPAD4 && !(keyCode == KeyEvent.VK_NUMPAD6))) {
                if (!GameDisplay.gamePaused) {
                    if (GameDisplay.x <= -25) {
                    } else {
                        if (GameDisplay.currentGrid >= 1) {
                            GameDisplay.currentGrid--;
                        }
                    }
                }
            } else if ((keyCode == KeyEvent.VK_RIGHT && keyCode != KeyEvent.VK_LEFT) ||
                    (keyCode == KeyEvent.VK_6 && !(keyCode == KeyEvent.VK_4)) ||
                    (keyCode == KeyEvent.VK_NUMPAD6 && !(keyCode == KeyEvent.VK_NUMPAD4))) {
                if (!GameDisplay.gamePaused) {
                    if (GameDisplay.x >= gameDisplay.maxW) {
                    } else {
                        if (GameDisplay.currentGrid <= 2) {
                            GameDisplay.currentGrid++;
                        }
                    }
                }
            }


            if (keyCode == KeyEvent.VK_ENTER) {
                GameDisplay.pausedWithEnter = true;
                if (!GameDisplay.gamePaused) {
                    GameDisplay.gamePaused = true;
                } else {
                    GameDisplay.pausedWithEnter = false;
                    GameDisplay.gamePaused = false;
                }
            }
        } else if ((double) gameDisplay.game_state > 3.9 && (double) gameDisplay.game_state < 4.1) {
            //keyCode = e.getKeyCode();
            // currentlyAllCorrect = true;
            if (keyCode == KeyEvent.VK_NUMPAD1 || keyCode == KeyEvent.VK_1) { // 1
                if (!GameDisplay.secondPhase) {
                    if (GameDisplay.thingsToRemember.charAt(0) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(0) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(0) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(0) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(0) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(0) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    GameDisplay.secondPhase = true;
                } else {
                    if (GameDisplay.thingsToRemember.charAt(3) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(3) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(3) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(3) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(3) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(3) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (GameDisplay.currentlyAllCorrect) {
                        GameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }
            if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_2) { // 2
                if (!GameDisplay.secondPhase) {
                    if (GameDisplay.thingsToRemember.charAt(1) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(1) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(1) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(1) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(1) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(1) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    GameDisplay.secondPhase = true;
                } else {
                    if (GameDisplay.thingsToRemember.charAt(4) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(4) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(4) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(4) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(4) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(4) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (GameDisplay.currentlyAllCorrect) {
                        GameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }
            if (keyCode == KeyEvent.VK_NUMPAD3 || keyCode == KeyEvent.VK_3) { // 3
                if (!GameDisplay.secondPhase) {
                    if (GameDisplay.thingsToRemember.charAt(2) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(2) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(2) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(2) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(2) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(2) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    GameDisplay.secondPhase = true;
                } else {
                    if (GameDisplay.thingsToRemember.charAt(5) == 'A' || // if caps, then correct
                            GameDisplay.thingsToRemember.charAt(5) == 'B' ||
                            GameDisplay.thingsToRemember.charAt(5) == 'C' ||
                            GameDisplay.thingsToRemember.charAt(5) == 'D' ||
                            GameDisplay.thingsToRemember.charAt(5) == 'H' ||
                            GameDisplay.thingsToRemember.charAt(5) == 'S') {
                        //currentlyAllCorrect = true;
                    } else {
                        GameDisplay.currentlyAllCorrect = false;
                    }
                    //secondPhase = false;
                    if (GameDisplay.currentlyAllCorrect) {
                        GameDisplay.allCorrectInTheEnd = true;
                        System.out.println("Both correct!");
                    }
                }
            }

            if ((GameDisplay.currentlyAllCorrect && (
                    keyCode == KeyEvent.VK_1 ||
                            keyCode == KeyEvent.VK_2 ||
                            keyCode == KeyEvent.VK_3 ||
                            keyCode == KeyEvent.VK_NUMPAD1 ||
                            keyCode == KeyEvent.VK_NUMPAD2 ||
                            keyCode == KeyEvent.VK_NUMPAD3))) {
                try {
                    gameDisplay.clipCorrect = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipCorrect.open(AudioSystem.getAudioInputStream(gameDisplay.fileCorrect));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                FloatControl gainControl =
                        (FloatControl) gameDisplay.clipCorrect.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-0.0f); // volume setting if wanted
                gameDisplay.clipCorrect.start();
            }
            if (!GameDisplay.currentlyAllCorrect) {
                if (!GameDisplay.allCorrectInTheEnd) {
                    System.out.println("Wrong guess, not proceeding to ropes!");

                    GameDisplay.nextState = 5;
                }
            }
            if (GameDisplay.allCorrectInTheEnd) {
                System.out.println("Proceeding to ropes!");
                GameDisplay.video = 3;
                GameDisplay.nextState = 2;
            }
        } else if ((double) gameDisplay.game_state > 4.9 && (double) gameDisplay.game_state < 5.1) {
            if (keyCode == KeyEvent.VK_ENTER) {
                if (GameDisplay.pulled_rope_1 || GameDisplay.pulled_rope_3) {
                    HugoSkiing.currentStateAtTheLevel = 71;
                    GameDisplay.nextState = 1;

                    if (HugoSkiing.currentStateAtTheLevel >= 71) {
                        try {
                            GameDisplay.clip3 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clip3.open(AudioSystem.getAudioInputStream(GameDisplay.fileGameMusic2));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clip3.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        GameDisplay.clip3.start();
                    } else {
                        try {
                            GameDisplay.clip0 = AudioSystem.getClip();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            GameDisplay.clip0.open(AudioSystem.getAudioInputStream(GameDisplay.fileGameMusic0));
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FloatControl gainControl =
                                (FloatControl) GameDisplay.clip0.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-0.0f); // volume setting for music, decreasing the volume if wanted
                        GameDisplay.clip0.start();
                    }
                } else {
                    GameDisplay.nextState = 0;
                }
            }
        } else if ((double) gameDisplay.game_state >= 5.1) {
            GameDisplay.nextState = 2;
            System.out.println(" --- keyPressed --- to state " + GameDisplay.nextState);
        }
    }

    /**
     * When releasing the left/right button in state 3. Plays a sound effect.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (!GameDisplay.gamePaused && gameDisplay.game_state == 3) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                try {
                    gameDisplay.clipChangeGrid = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid4.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid4));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameDisplay.clipChangeGrid4.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                try {
                    gameDisplay.clipChangeGrid6 = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gameDisplay.clipChangeGrid6.open(AudioSystem.getAudioInputStream(gameDisplay.fileChangeGrid6));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameDisplay.clipChangeGrid6.start();
            }
        }
    }
}

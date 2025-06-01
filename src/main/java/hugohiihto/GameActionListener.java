package hugohiihto;

import hugohiihto.type.GameState;
import hugohiihto.type.VideoType;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
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

        tryEscape(keyCode);

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gameDisplay.video != VideoType.THREE_ROPES_INTRO) {
                gameDisplay.videoFlush();
            }

            stopClip(gameDisplay.clip0);
            stopClip(gameDisplay.clip1); // uncomment if needed
            stopClip(gameDisplay.clip2);
            stopClip(gameDisplay.clip3);

            if (gameDisplay.mediaPlayer != null &&
                    gameDisplay.video != VideoType.SCYLLA_BUTTON &&
                    gameDisplay.video != VideoType.THREE_ROPES_INTRO) {
                gameDisplay.mediaPlayer.stop();
            }
        }

        switch (gameDisplay.gameState) {
            case PRE_TITLE -> handlePreTitle(keyCode);
            case TITLE_SCREEN -> handleTitleScreen(keyCode);
            case SHOWING_VIDEO -> handleShowingVideo(keyCode);
            case SKI_GAME -> handleSkiGame(keyCode);
            case REMEMBER_ITEMS -> handleRememberItems(keyCode);
            case GAME_OVER -> handleGameOver(keyCode);
            case VIDEO_TRANSITION -> handleVideoTransition();
        }
    }

    private void stopClip(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    private void handleVideoTransition() {
        gameDisplay.nextState = GameState.SHOWING_VIDEO;
        System.out.println(" --- keyPressed --- to state " + gameDisplay.nextState);
    }

    private void handleGameOver(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            if (gameDisplay.pulled_rope_1 || gameDisplay.pulled_rope_3) {
                hugoSkiing.currentStateAtTheLevel = 71;
                gameDisplay.nextState = GameState.TITLE_SCREEN;

                if (hugoSkiing.currentStateAtTheLevel >= 71) {
                    playSound(gameDisplay.fileGameMusic2, clip -> gameDisplay.clip3 = clip, -0.0f);
                } else {
                    playSound(gameDisplay.fileGameMusic0, clip -> gameDisplay.clip0 = clip, -0.0f);
                }
            } else {
                gameDisplay.nextState = GameState.PRE_TITLE;
            }
        }
    }

    private void handleRememberItems(int keyCode) {
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
            playSound(gameDisplay.fileCorrect, clip -> gameDisplay.clipCorrect = clip, -0.0f);
        }
        if (!gameDisplay.currentlyAllCorrect) {
            if (!gameDisplay.allCorrectInTheEnd) {
                System.out.println("Wrong guess, not proceeding to ropes!");

                gameDisplay.nextState = GameState.GAME_OVER;
            }
        }
        if (gameDisplay.allCorrectInTheEnd) {
            System.out.println("Proceeding to ropes!");
            gameDisplay.video = VideoType.THREE_ROPES_INTRO;
            gameDisplay.nextState = GameState.SHOWING_VIDEO;
        }
    }

    private void handleSkiGame(int keyCode) {
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
    }

    private void handleShowingVideo(int keyCode) {
        if (gameDisplay.video == VideoType.SCYLLA_INTRO) {
            if (keyCode == KeyEvent.VK_ENTER) {

                if (hugoSkiing.currentStateAtTheLevel >= 71 && hugoSkiing.gameOver == false) {
                    playSound(gameDisplay.fileGameMusic2, clip -> gameDisplay.clip3 = clip, -0.0f);
                } else {
                    playSound(gameDisplay.fileGameMusic0, clip -> gameDisplay.clip0 = clip, -0.0f);
                }

                gameDisplay.gamePaused = true;
                gameDisplay.nextState = GameState.TITLE_SCREEN;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.HUGO_FIRST_WORDS) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.SKI_GAME; // to the actual game
                gameDisplay.gamePaused = false;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.SCYLLA_BUTTON) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.SKI_GAME;
                gameDisplay.gamePaused = false;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.THREE_ROPES_INTRO) {
            if (gameDisplay.clip1 != null) {
                if (gameDisplay.clip1.isRunning()) {
                    gameDisplay.clip1.stop();
                }
            }

            tryEscape(keyCode);
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

                gameDisplay.video = VideoType.ROPE_1;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION; // use state 6 or higher when moving from a video to another video
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
                gameDisplay.video = VideoType.ROPE_2;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION;
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

                gameDisplay.video = VideoType.ROPE_3;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION;
            }
        }
        if (gameDisplay.video == VideoType.HUGO_ASKS_FOR_TWO) {    // hugo asks 2
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.REMEMBER_ITEMS;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.TWO_CHOSEN_CORRECTLY) {    // 2 right
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

                gameDisplay.video = VideoType.THREE_ROPES_INTRO;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.WRONG_CHOICE) {    // wrong
            if (keyCode == KeyEvent.VK_ENTER) {
                hugoSkiing.gameOver = true;
                gameDisplay.nextState = GameState.GAME_OVER;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.SKIING_FINISHED) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.video = VideoType.HUGO_ASKS_FOR_TWO;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.WAKE_UP_PAHVI) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.SKI_GAME; // to the actual game
                gameDisplay.gamePaused = false;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.LAST_TROLL_GOING) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.SKI_GAME; // to the actual game
                gameDisplay.gamePaused = false;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.GAME_OVER) {
            if (keyCode == KeyEvent.VK_ENTER) {
                // cancel all timertasks!
                hugoSkiing.gameOver = true;
                gameDisplay.nextState = GameState.GAME_OVER;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.ROPE_1) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.GAME_OVER;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.ROPE_2) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.GAME_OVER;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.ROPE_3) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameDisplay.nextState = GameState.GAME_OVER;
            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.SNOWMAN) {
            gameDisplay.currentGrid = 0;
            gameDisplay.currentHazardOrMoney1_y_position += 800;
            gameDisplay.currentHazardOrMoney2_y_position += 800;
            gameDisplay.currentHazardOrMoney3_y_position += 800;
            gameDisplay.currentHazardOrMoney4_y_position += 800;
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gameDisplay.number_of_lives >= 2) {
                    gameDisplay.video = VideoType.WAKE_UP_PAHVI;
                    gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                } else {
                    if (gameDisplay.number_of_lives >= 1) {
                        gameDisplay.video = VideoType.LAST_TROLL_GOING;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    } else {
                        gameDisplay.video = VideoType.GAME_OVER;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    }
                }

            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.SNOWBALL) {
            gameDisplay.currentGrid = 0;
            gameDisplay.currentHazardOrMoney1_y_position += 800;
            gameDisplay.currentHazardOrMoney2_y_position += 800;
            gameDisplay.currentHazardOrMoney3_y_position += 800;
            gameDisplay.currentHazardOrMoney4_y_position += 800;
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gameDisplay.number_of_lives >= 2) {
                    gameDisplay.video = VideoType.WAKE_UP_PAHVI;
                    gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                } else {
                    if (gameDisplay.number_of_lives >= 1) {
                        gameDisplay.video = VideoType.LAST_TROLL_GOING;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    } else {
                        gameDisplay.video = VideoType.GAME_OVER;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    }
                }

            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.BOMB) {
            gameDisplay.currentGrid = 0;
            gameDisplay.currentHazardOrMoney1_y_position += 800;
            gameDisplay.currentHazardOrMoney2_y_position += 800;
            gameDisplay.currentHazardOrMoney3_y_position += 800;
            gameDisplay.currentHazardOrMoney4_y_position += 800;
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gameDisplay.number_of_lives >= 2) {
                    gameDisplay.video = VideoType.WAKE_UP_PAHVI;
                    gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                } else {
                    if (gameDisplay.number_of_lives >= 1) {
                        gameDisplay.video = VideoType.LAST_TROLL_GOING;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    } else {
                        gameDisplay.video = VideoType.GAME_OVER;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    }
                }

            }
            tryEscape(keyCode);
        }
        if (gameDisplay.video == VideoType.BEAVER) {
            gameDisplay.currentGrid = 0;
            gameDisplay.currentHazardOrMoney1_y_position += 800;
            gameDisplay.currentHazardOrMoney2_y_position += 800;
            gameDisplay.currentHazardOrMoney3_y_position += 800;
            gameDisplay.currentHazardOrMoney4_y_position += 800;
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gameDisplay.number_of_lives >= 2) {
                    gameDisplay.video = VideoType.WAKE_UP_PAHVI;
                    gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                } else {
                    if (gameDisplay.number_of_lives >= 1) {
                        gameDisplay.video = VideoType.LAST_TROLL_GOING;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    } else {
                        gameDisplay.video = VideoType.GAME_OVER;
                        gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                    }
                }

            }
            tryEscape(keyCode);
        }
    }

    private void handleTitleScreen(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {

            gameDisplay.videoFlush();
            gameDisplay.stopSound();
            gameDisplay.videoimg = new ImageIcon("res/start_hoplaa_s.gif").getImage();
            gameDisplay.videoimg.setAccelerationPriority((float) 1.0); // from 0-> lowest to 1-> highest

            if (hugoSkiing.currentStateAtTheLevel >= 71 && (gameDisplay.pulled_rope_3 || gameDisplay.pulled_rope_1)) {
                gameDisplay.nextState = GameState.PRE_TITLE;
                hugoSkiing.currentStateAtTheLevel = -5;
            } else {
                if (hugoSkiing.gameOver) {
                    if (hugoSkiing.timerTask != null) {
                        hugoSkiing.timerTask.cancel();
                    }
                }

                gameDisplay.reset();

                gameDisplay.video = VideoType.HUGO_FIRST_WORDS;
                gameDisplay.nextState = GameState.VIDEO_TRANSITION;
                gameDisplay.constructFrames(gameDisplay.gameState);
            }
        }
        if (isKey(keyCode, KeyEvent.VK_9) && gameDisplay.key5) gameDisplay.key6 = true;
        if (isKey(keyCode, KeyEvent.VK_9)) gameDisplay.key1 = true;

        if (isKey(keyCode, KeyEvent.VK_7) && gameDisplay.key9) gameDisplay.key10 = true;
        if (isKey(keyCode, KeyEvent.VK_7) && gameDisplay.key1) gameDisplay.key2 = true;

        if (isKey(keyCode, KeyEvent.VK_0) && gameDisplay.key3) gameDisplay.key4 = true;
        if (isKey(keyCode, KeyEvent.VK_0) && gameDisplay.key2) gameDisplay.key3 = true;

        if (isKey(keyCode, KeyEvent.VK_5) && gameDisplay.key6) gameDisplay.key7 = true;

        if (isKey(keyCode, KeyEvent.VK_4) && gameDisplay.key11) gameDisplay.key12 = true;
        if (isKey(keyCode, KeyEvent.VK_4) && gameDisplay.key10) gameDisplay.key11 = true;
        if (isKey(keyCode, KeyEvent.VK_4) && gameDisplay.key8) gameDisplay.key9 = true;
        if (isKey(keyCode, KeyEvent.VK_4) && gameDisplay.key7) gameDisplay.key8 = true;
        if (isKey(keyCode, KeyEvent.VK_4) && gameDisplay.key4) gameDisplay.key5 = true;

        if (gameDisplay.cheatBackflip180) {
            playSound(gameDisplay.fileMoney, clip -> gameDisplay.clipMoney = clip, -0.0f);
        }
        if (gameDisplay.key12) { // Activating the cheat mode!
            gameDisplay.cheatBackflip180 = true;
        }
    }

    private boolean isKey(int keyCode, int baseKey) {
        return keyCode == baseKey || keyCode == getNumpadKey(baseKey);
    }

    private int getNumpadKey(int baseKey) {
        return switch (baseKey) {
            case KeyEvent.VK_0 -> KeyEvent.VK_NUMPAD0;
            case KeyEvent.VK_1 -> KeyEvent.VK_NUMPAD1;
            case KeyEvent.VK_2 -> KeyEvent.VK_NUMPAD2;
            case KeyEvent.VK_3 -> KeyEvent.VK_NUMPAD3;
            case KeyEvent.VK_4 -> KeyEvent.VK_NUMPAD4;
            case KeyEvent.VK_5 -> KeyEvent.VK_NUMPAD5;
            case KeyEvent.VK_6 -> KeyEvent.VK_NUMPAD6;
            case KeyEvent.VK_7 -> KeyEvent.VK_NUMPAD7;
            case KeyEvent.VK_8 -> KeyEvent.VK_NUMPAD8;
            case KeyEvent.VK_9 -> KeyEvent.VK_NUMPAD9;
            default -> -1;
        };
    }

    private void handlePreTitle(int keyCode) {
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

            gameDisplay.video = VideoType.SCYLLA_INTRO;
            gameDisplay.nextState = GameState.VIDEO_TRANSITION;
        }
    }

    /**
     * When releasing the left/right button in state 3. Plays a sound effect.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (gameDisplay.gamePaused || gameDisplay.gameState != GameState.SKI_GAME) return;

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            playSound(gameDisplay.fileChangeGrid, clip -> gameDisplay.clipChangeGrid = clip, -23.0f);
        } else if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) {
            playSound(gameDisplay.fileChangeGrid4, clip -> gameDisplay.clipChangeGrid4 = clip, null);
        } else if (keyCode == KeyEvent.VK_6 || keyCode == KeyEvent.VK_NUMPAD6) {
            playSound(gameDisplay.fileChangeGrid6, clip -> gameDisplay.clipChangeGrid6 = clip, null);
        }
    }

    private void playSound(File audioFile, Consumer<Clip> clipSetter, Float volumeDb) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));

            if (volumeDb != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volumeDb);
            }

            clipSetter.accept(clip);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(gameDisplay.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tryEscape(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.gc();
            System.exit(0);
        }
    }

}

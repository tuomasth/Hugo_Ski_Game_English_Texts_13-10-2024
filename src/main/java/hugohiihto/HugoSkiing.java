package hugohiihto;

import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;

/**
 * Hugo Ski Game v1.1 by Tuomas Hyvönen, 10/2024
 * <p>
 * New in this version:
 * - English texts (but I wanted to keep the Finnish Harri Hyttinen voices).
 * - The background ski tracks have a little animation.
 * - There are old cell phone sounds when pressing 4 and 6.
 * - Some files are edited, for example the GIF files have smaller sizes.
 * - The player does not get stuck when pressing 2 buttons simultaneously.
 * - There are 2 more tree objects and the cloud can move both left and right.
 * - The player has 4 lives instead of 3, the zeroth life counts as a life.
 * - There is a cheat code "9700 4954 4744".
 * - A Commodore64 music has been added to the credits screen.
 * - Scoring is different.
 * - The graphics match a bit better with what is really happening according to the game's logic.
 * - The score bar does not show unnecessary zeros and the lives are on the left side instead of right.
 * <p>
 * - There can still be bugs and other flaws, I am not a perfect programmer.
 * You should be happy if the application even starts up in the first place.
 * If a video cut scene does not start, please move the mouse cursor on the game window.
 * <p>
 * The game window cannot be resized currently, press the Windows key then +Plus/-Minus to zoom in/out.
 * Windows Magnifier/zooming instructions:
 * https://www.androidpolice.com/how-to-zoom-in-out-on-windows-10-11/
 * Something like this should be possible on Linux, MAC etc. too but without Windows button...
 * <p>
 * Thanks for trying out this game! I do not own the Hugo franchise.
 * Sharing Java codes and random files on the Internet should not be a crime - as far as I do not cause harm to people or their money.
 * The purpose is to share the fun with people and keep the Hugo franchise alive.
 * <p>
 * Java must be installed before playing the game:
 * https://www.java.com/en/download/manual.jsp
 * <p>
 * <p>
 * "Work is not man's punishment. It is his reward and his strength and his pleasure."
 * - George Sand, French novelist
 * <p>
 * "Love is a serious mental disease."      (especially for Hugo franchise)
 * - Plato, Greek philosopher
 * <p>
 * "We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light."
 * - Plato
 * <p>
 * https://www.brainyquote.com
 * <p>
 * ----
 * <p>
 * Please support the original and official Hugo releases! They have been the inspiration for this game.
 * Consider this game to be treated the same way as "Mega Man Unlimited".
 * https://megamanfanon.fandom.com/wiki/Mega_Man_Unlimited
 * <p>
 * Or if you're mean, treat this the same way as Metroid AM2R.
 * https://en.wikipedia.org/wiki/AM2R
 * <p>
 * Or Commodore64 Super Mario Bros. that Nintendo also did not treat well.
 * https://archive.org/details/Super_Mario_Bros_C64_Zeropaige
 * <p>
 * <p>
 * Thanks to this discussion for huge progress in game development when getting started:
 * https://stackoverflow.com/questions/12082660/background-image-for-simple-game
 * <p>
 * "The beginning is the most important part of the work."
 * - also from Plato
 * <p>
 * ----
 * <p>
 * Some Hugo creators are not involved in this project, thanks for each one of them!
 * Consider this as "the real credits screen":
 * <p>
 * https://screentroll.fandom.com/wiki/Hugo_(PlayStation)
 * Producers: Ivan Sølvason, Lars Rikart Jensen, Troels Gram, Jens C. Ringdal
 * Design: Niels Krogh Mortensen, Troels Gram, Lars Rikart Jensen
 * Programmers: Peter Marino, Poul-Jesper Olsen, Ole Thomas Jensen, Mario Gomes, Claes Hougaard,
 * Michael Barklund, Erik Schack Andersen, John Dideriksen, Troels Gram, Jørgen Lortentsen
 * Graphics: Chadi Freigeh, Claus Friese, Thomas Skellund, Mark Gregory, Jakob Steffensen, Peter Eide Paulsen,
 * Jørgen Trolle Ørberg, John Madsen, Niels Krogh Mortensen, Lars Krogh Mortensen, René Bidstrup,
 * Anders Morgenthaler, Torben Bakager Larsen, Jesper Laugesen
 * Music and sound: David A. Filskov, Christian Steen Jensen.
 * <p>
 * https://screentroll.fandom.com/wiki/Hugo_2_(PlayStation)
 * Producer: Ivan Sølvason, Lars Rikart Jensen. Piet N. Kargaard
 * Design: Mario Gomes, Peter Eide Paulsen, Poul Engelbrech Madsen, John Dideriksen
 * Programming: Mario Gomes, Jesper Olsen, Anders Emil Hansen, Ole T. Jensen
 * Graphics: Claus Friese, Chadi Freigeh, Peter Eide Paulsen, Piet N. Kargaard, John Madsen, Jørgen Trolle Ørberg
 * Music and sound: David A. Filskov, Christian Steen Jensen, Klaus Mulvad Nielsen, Asbjørn Andersen
 * Other: Niels Krogh Mortensen, Lars Krogh Mortensen, René Bidstrup, Anders Morgenthaler, Torben Bakager Larsen,
 * Jesper Laugesen, Tom Westerman, Thomas Skellund, Espen Toft Jacobsen, Laust Palbo Nielsen.
 * <p>
 * https://screentroll.fandom.com/wiki/Hugo_5
 * Production manager: Ivan Sølvason
 * Programmers: Jakob Frandsen, Bo Krohn, Kim Frederiksen, Lasse S. Tassing, Jens Nordahl
 * TV programmers: Kim Frederiksen, Stig Jørgensen
 * Graphics: Lars Krogh Mortensen, Laust Palbo Nielsen, Tom Westermann, Esben Toft Jacobsen,
 * Jakob Steffensen, Jesper Eskildsen, Thomas Skellund
 * Music and sound effects: Mads Kristensen, David Filskov.
 * <p>
 * https://screentroll.fandom.com/wiki/Hugo_(1996_video_game)
 * Producer: Ivan Sølvason
 * PC programming: Jakob Frandsen, Lasse Tassing, Kim Frederisken, Troels Gram
 * TV programming: Stig Jørgansen, Kim Frederisken, Bo Krohn, Morten Hansen, Esben Krag Hansen
 * Graphics: Niels Krogh Mortensen, Lars Krogh Mortensen, René Bidstrup, Anders Morgenthaler,
 * Laust Palbo Nielsen, Thomas Skellund, Torben Bakager, Martin De Thurah
 * Music and sound effects: Mads Kriestensen, Nicolai Thilo, Thomas Engell, Jørgen Traun.
 * <p>
 * https://fi.wikipedia.org/wiki/DJ_Hugo
 * https://fi.wikipedia.org/wiki/Hugo_(televisio-ohjelma)
 * Music: Slotmachine featuring Gemini 7, Kata Laurikainen, Anssi Ahonen, Jaana Rinne
 * Other: Harri Hyttinen, Eija Ahvo, Jussi-Pekka Koskiranta, Pekka Kossila, Ari Meriläinen, Taru Valkeapää, Marika Saukkonen.
 * <p>
 * Guyus the Raptor and other uploaders at YouTube etc.
 * Commodore 64 music by Jens-Christian Huus
 * <p>
 * ----
 * <p>
 * <p>
 * Computations and methods not included in the user interface / game display Java source code file.
 * Comments and names are sometimes in Finnish but tried to use English mostly.
 * <p>
 * Tested with Microsoft Windows 11
 * Java developed by Oracle / Sun Microsystems.
 * Created with Apache NetBeans 23
 * Official release date: 24/2/2023 - v1.0 Finnish version available worldwide
 *
 * @author Tuomas Hyvönen
 * @version 1.1.ENG
 */
public class HugoSkiing {
    TimerTask timerTask;
    private GameDisplay gameDisplay;
    String[] haz = null;
    String rem = null;
    boolean gameOver = true;
    boolean hasAchievedMaxScore = false;
    public int currentStateAtTheLevel = -5; // -5   So the game does not start with a surprise attack.
    int theFurthestThePlayerHasGot = -4; // -4   (-5 +1)   This is necessary because of the pause feature.
    public boolean tic = true;
    // There is no clear logic in what class file the variables should belong to, there are only 2 Java classes used anyway.

    /**
     * The constructor.
     */
    public HugoSkiing(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
        this.gameDisplay.leftWind = (Math.random() < 0.5);
        haz = giveStageHazards(gameDisplay.cheatBackflip180);
        rem = giveThingsToRemember();
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    /**
     * Resetting the game or beginning a new game.
     * The game speed integer is in milliseconds, for example 1800 = 1.8 seconds.
     * Does not edit how fast the graphics will show on the screen though!
     *
     * @param gameSpeed
     */
    public void gameReset(int gameSpeed) {
        if (gameSpeed > 1000 && gameSpeed < 3000 && gameOver) {
            System.out.println("Game reset called");
            gameSpeed = GameDisplay.GAMESPEED;
            System.gc(); // run Java garbage collector
            gameOver = false;
            gameDisplay.gameReset();

            currentStateAtTheLevel = -5;
            hasAchievedMaxScore = false;
            theFurthestThePlayerHasGot = -4;

            gameDisplay.thingsToRemember = this.getREM();
            timerTask = new GameLoop(this, gameDisplay);  // Game loop handles the stage hazards with time tasks and
            java.util.Timer ti = new java.util.Timer(true);   // processes them, different than the game display itself.
            ti.scheduleAtFixedRate(timerTask, 0, gameSpeed);  // For example, 1800 = 1.8 sec (affects the game speed, not graphics)
        } else {
            gameSpeed = 1700;
            gameOver = true;
            gameReset(gameSpeed);
        }
    }

    /**
     * Get the 2 things to remember.
     *
     * @return String
     */
    public String getREM() {
        return this.rem;
    }

    /**
     * Increase score, ones.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreOnes(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setOnes(whatWillBeIncreased);
        } else {
            increaseScoreTens(gameDisplay.tens);
            gameDisplay.setOnes(0);
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Increase score, tens.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreTens(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        gameDisplay.tensVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setTens(whatWillBeIncreased);
        } else {
            increaseScoreHundreds(gameDisplay.hundreds);
            gameDisplay.setTens(0);
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Increase score, 100s.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreHundreds(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        gameDisplay.tensVisible = true;
        gameDisplay.hundredsVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setHundreds(whatWillBeIncreased);
        } else {
            increaseScoreThousands(gameDisplay.thousands);
            gameDisplay.setHundreds(0);
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Increase score, 1000s.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreThousands(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        gameDisplay.tensVisible = true;
        gameDisplay.hundredsVisible = true;
        gameDisplay.thousandsVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setThousands(whatWillBeIncreased);
        } else {
            increaseScoreTenThousands(gameDisplay.tenThousands);
            gameDisplay.setThousands(0);
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Increase score, 10 000s.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreTenThousands(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        gameDisplay.tensVisible = true;
        gameDisplay.hundredsVisible = true;
        gameDisplay.thousandsVisible = true;
        gameDisplay.tenThousandsVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setTenThousands(whatWillBeIncreased);
        } else {
            increaseScoreHundredThousands(gameDisplay.hundredThousands);
            gameDisplay.setTenThousands(0);
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Increase score, 100 000s.
     *
     * @param whatWillBeIncreased
     */
    public void increaseScoreHundredThousands(int whatWillBeIncreased) {
        gameDisplay.onesVisible = true;
        gameDisplay.tensVisible = true;
        gameDisplay.hundredsVisible = true;
        gameDisplay.thousandsVisible = true;
        gameDisplay.tenThousandsVisible = true;
        gameDisplay.hundredThousandsVisible = true;
        if ((whatWillBeIncreased < 9) && (whatWillBeIncreased > -1) && (!hasAchievedMaxScore)) {
            whatWillBeIncreased++;
            gameDisplay.setHundredThousands(whatWillBeIncreased);
        } else {
            hasAchievedMaxScore = true; // trying to increase when it's already 9
            System.out.println("THE PLAYER GOT A MILLION POINTS");
        }
        if (hasAchievedMaxScore) {
            gameDisplay.setMaxScore();
        }
    }

    /**
     * Decreases lives by 1 call when hitting an enemy (4 possible enemies).
     *
     * @param lives
     */
    public void decreaseLives(int lives) {
        lives--;
        gameDisplay.setLives(lives);
    }

    /**
     * Create a random integer, Java's own Math.random() has been used.
     *
     * @param min
     * @param max_that_does_not_count
     * @return int
     */
    public int getRandom(int min, int max_that_does_not_count) {
        int random = (int) ((Math.random() * (max_that_does_not_count - min)) + min);
        return random; // max_that_does_not_count if zero counts
    }

    /**
     * Creating the stage hazards to the 1d array called hazards.
     * Length is 71, negative values = the stage is about to begin soon, and Hugo is just skiing.
     * In a previous version, the game array was longer.
     *
     * @return int[]
     */
    public int[] createStageHazards() {
        int[] hazards = new int[71];
        /*
         * E- empty
         * M- money
         * 8- snowman
         * o- snowball
         * Q- bomb
         * B- Masi the beaver (in Finland he is called Masi)
         *
         * 1- thing to remember #1 (6 possible)
         * 2- thing to remember #2 (6 possible (actually 5 because no same again))
         * S- Scylla button press with short horror music
         * F- goal, just end the skiing session
         *
         *
         * 0 = empty, empty, SNOWBALL, empty
         * 1 = MONEY, empty, SNOWMAN, empty
         * 2 = empty, MONEY, SNOWMAN, empty
         * 3 = empty, empty, MONEY, empty
         * 4 = empty, SNOWMAN, empty, MONEY
         * 5 = SNOWMAN, empty, empty, empty
         * 6 = empty, empty, empty, SNOWMAN
         * 7 = empty, SNOWBALL, empty, empty
         * 8 = empty, empty, empty, empty
         * 9 = BOMB, BOMB, empty, BOMB
         * 10 = BOMB, empty, BOMB, BOMB
         * 11 = empty, BOMB, BOMB, empty
         * 12 = BOMB, empty, MONEY, BOMB
         * 13 = BEAVER, MONEY, empty, empty
         * 14 = SNOWBALL, BEAVER, empty, empty
         * 15 = empty, empty, BEAVER, SNOWBALL
         * 16 = BOMB, empty, empty, BEAVER
         * 17 = empty, SNOWBALL, BOMB, BOMB
         * 18 = BOMB, BOMB, SNOWBALL, empty
         *
         * // some will be overwritten with 2 must-remember-images etc.
         */

        int randomPrevious = 8;
        for (int i = 0; i < 40; i++) {
            int random = getRandom(0, 8);
            while (randomPrevious == random) { // avoids getting the same number again
                random = getRandom(0, 8);
            }
            hazards[i] = random;
            randomPrevious = hazards[i];
        }
        for (int i = 40; i < hazards.length; i++) {
            int random = getRandom(5, 19);
            while (randomPrevious == random) {
                random = getRandom(5, 19);
            }
            hazards[i] = random;
            randomPrevious = hazards[i];
        }
        for (int i = 8; i < 40; i++) {
            if (i % 2 == 0) {
                hazards[i] = 8; // every 2nd empty, a selected range
            }
            if (i == 14 || i == 25 || i == 15 || i == 26 || i == 16 || i == 27 || i == 17 || i == 28) {
                hazards[i] = 8;
            }
        }
        System.out.println(Arrays.toString(hazards));
        return hazards;
    }

    /**
     * Getting the stage hazards.
     *
     * @return String[]
     */
    public String[] giveStageHazards(boolean cheatBackflip180) {
        String[] s = new String[71];
        int haz[] = createStageHazards();

        /*
         * E- empty (even though E might not be needed to be read, it is meaningful to show positions)
         * M- money
         * 8- snowman
         * o- snowball (small o)
         * Q- bomb
         * B- the beaver
         * 1- thing to remember #1 (6 possible)
         * 2- thing to remember #2 (6 possible (actually 5 because never same again allowed))
         * S- Scylla button press with short horror music
         * F- goal, just end the skiing session
         *
         * A-  black asterisk* as +correct
         * a-  black asterisk* as incorrect-
         * B-  yellow bell as +correct
         * b-  yellow bell as incorrect-
         * C-  red clock as +correct
         * c-  red clock as incorrect-
         * D-  red diamond as +correct
         * d-  red diamond as incorrect-
         * H-  black hash(tag#) as +correct
         * h-  black hash(tag#) as incorrect-
         * S-  yellow star as +correct
         * s-  yellow star as incorrect-
         */

        Map<Integer, String> hazardMap = Map.ofEntries(
                Map.entry(0, "EEoE"),
                Map.entry(1, "ME8E"),
                Map.entry(2, "EM8E"),
                Map.entry(3, "EEME"),
                Map.entry(4, "E8EM"),
                Map.entry(5, "8EEE"),
                Map.entry(6, "EEE8"),
                Map.entry(7, "EoEE"),
                Map.entry(8, "EEEE"),
                Map.entry(9, "QQEQ"),
                Map.entry(10, "QEQQ"),
                Map.entry(11, "EQQE"),
                Map.entry(12, "QEMQ"),
                Map.entry(13, "BMEE"),
                Map.entry(14, "oBEE"),
                Map.entry(15, "EEBo"),
                Map.entry(16, "QEEB"),
                Map.entry(17, "EoQQ")
                // 18+ => handled separately
        );

        for (int i = 0; i < 70; i++) {
            s[i] = hazardMap.getOrDefault(haz[i], "QQoE"); // fallback >= 18
        }

        // zero counts as the 1st number,

        // from 0 to 14:    casual easy
        // the 15th:          the 1st to remember
        // from 16 to 25:   casual easy
        // the 26th:          the 2nd to remember
        // from 27 to 38:   casual easy
        // the 39th:          Scylla's button press, popcorn starts
        // from 40 to 69:   hardcore bombing
        // the 70th:          the finish line
        // "memory game"
        // 3 ropes
        s[39] = "SSSS";
        if (cheatBackflip180) {
            for (int i = 0; i < 1; i++) {
                s[i] = "SSSS";
            }
            for (int i = 1; i < 4; i++) {
                s[i] = "QBo8";
            }
            for (int i = 4; i < 15; i++) {
                s[i] = "EMME";
            }
            for (int i = 17; i < 26; i++) {
                s[i] = "EMME";
            }
            for (int i = 28; i < 63; i++) {
                s[i] = "EMME";
            }
            for (int i = 63; i < 70; i++) {
                s[i] = "FFFF";
            }
        }
        s[15] = "1111";
        s[16] = "EEEE"; // always an empty set after a scroll (1 and 2).
        s[26] = "2222";
        s[27] = "EEEE";
        s[70] = "FFFF";
        System.out.println(Arrays.toString(s));
        return s;
    }


    /**
     * Generate what 2 things must be remembered when Hugo is at the goal.
     *
     * @return int
     */
    public int generateIntToRemember() {
        /*
         * 1 = asterisk & bell
         * 2 = asterisk & clock
         * 3 = asterisk & diamond
         * 4 = asterisk & hashtag
         * 5 = asterisk & star  // "tähti, tähti"
         * 6 = bell & clock     // "kello, kello"
         * 7 = bell & diamond
         * 8 = bell & hashtag
         * 9 = bell & star
         * 10 = clock & diamond
         * 11 = clock & hashtag
         * 12 = clock & star
         * 13 = diamond & hashtag // "ruutu, ruutu"
         * 14 = diamond & star
         * 15 = hashtag & star
         *
         * Wrong answers when 3+3 are shown to the player:
         * 1 clock, diamond; hashtag, star
         * 2 bell, diamond; hashtag, star
         * 3 bell, clock; hashtag, star
         * 4 bell, clock; diamond, star
         * 5 bell, clock; diamond, hashtag
         * 6 asterisk, diamond; hashtag, star
         * 7 asterisk, clock; hashtag, star
         * 8 asterisk, clock; diamond, star
         * 9 asterisk, clock; diamond, hashtag
         * 10 asterisk, bell; hashtag, star
         * 11 asterisk, bell; diamond, star
         * 12 asterisk, bell; diamond, hashtag
         * 13 asterisk, bell; clock, star
         * 14 asterisk, bell; clock, hashtag
         * 15 asterisk, bell; clock, diamond
         *
         */
        int items = getRandom(0, 16); // some may have more probability to become chosen
        return items;
    }

    /**
     * Give the 2 items to remember to get the skull cave key at the end.
     *
     * @return String
     */
    public String giveThingsToRemember() {
        int problem = generateIntToRemember();
        boolean[][] problem2array = new boolean[3][3];
        // asterisk a, bell b, clock c, diamond d, hashtag h, star s
        // caps means the right answer
        String s = "";
        if (problem <= 1) {
            problem2array[0][0] = true; // Acd Bhs
            s = "AcdBhs100100";
        }
        if (problem == 2) {
            problem2array[0][1] = true; // Ads bCh
            s = "AdsbCh100010";
        }
        if (problem == 3) {
            problem2array[0][2] = true; // Acb shD
            s = "AcbshD100001";
        }
        if (problem == 4) {
            problem2array[1][0] = true; // bAc Hds
            s = "bAcHds010100";
        }
        if (problem == 5) {
            problem2array[1][1] = true; // cAb hSd
            s = "cAbhSd010010";
        }
        if (problem == 6) {
            problem2array[1][2] = true; // aBd hsC
            s = "aBdhsC010001";
        }
        if (problem == 7) {
            problem2array[2][0] = true; // chB Das
            s = "chBDas001100";
        }
        if (problem == 8) {
            problem2array[2][1] = true; // acB sHd
            s = "acBsHd001010";
        }
        if (problem == 9) {
            problem2array[2][2] = true; // haB cdS
            s = "haBcdS001001";
        }
        if (problem == 10) {
            problem2array[0][0] = true; // Cab Dsh
            s = "CabDsh100100";
        }
        if (problem == 11) {
            problem2array[0][1] = true; // Cbd aHs
            s = "CbdaHs100010";
        }
        if (problem == 12) {
            problem2array[2][2] = true; // haC dbS
            s = "haCdbS001001";
        }
        if (problem == 13) {
            problem2array[0][0] = true; // Das Hbc
            s = "DasHbc100100";
        }
        if (problem == 14) {
            problem2array[1][1] = true; // aDb cSh
            s = "aDbcSh010010";
        }
        if (problem >= 15) {
            problem2array[0][0] = true; // Hba Sdc
            s = "HbaSdc100100";
        }

        System.out.println(s);
        return s;
    }
}
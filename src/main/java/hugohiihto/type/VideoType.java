package hugohiihto.type;

public enum VideoType {
    SCYLLA_INTRO,             // 0 = Scylla intro
    HUGO_FIRST_WORDS,         // 1 = Hugo's first words "hoplaa nyt hommiin"
    SCYLLA_BUTTON,            // 2 = Scylla button
    THREE_ROPES_INTRO,        // 3 = three ropes intro
    HUGO_ASKS_FOR_TWO,        // 4 = Hugo asks for two
    TWO_CHOSEN_CORRECTLY,     // 5 = two chosen correctly
    WRONG_CHOICE,             // 6 = made a wrong choice
    SKIING_FINISHED,          // 7 = (knock) Hugo finished the skiing
    WAKE_UP_PAHVI,            // 8 = [knock] wake up pahvi
    LAST_TROLL_GOING,         // 9 = (knock) now the last troll going
    GAME_OVER,                // 10 = (knock) game over
    ROPE_1,                   // 11 = rope #1
    ROPE_2,                   // 12 = rope #2
    ROPE_3,                   // 13 = rope #3
    SNOWMAN,                  // 14 = snowman
    SNOWBALL,                 // 15 = snowball
    BOMB,                     // 16 = bomb
    BEAVER;                   // 17 = beaver

    public int getIndex() {
        return this.ordinal();
    }

    public static VideoType fromIndex(int index) {
        if (index < 0 || index >= values().length) {
            throw new IllegalArgumentException("Invalid video index: " + index);
        }
        return values()[index];
    }
}
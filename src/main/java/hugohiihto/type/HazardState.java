package hugohiihto.type;

public enum HazardState {
    EMPTY('E'),
    MONEY('M'),
    SNOWMAN('8'),
    SNOWBALL('o'),
    BOMB('Q'),
    MASI('B'), // Masi the beaver
    REMEMBER_1('1'), // Thing to remember #1
    REMEMBER_2('2'), // Thing to remember #2
    SCYLLA_BUTTON('S'), // Button press with short horror music
    GOAL('F'); // End of skiing session

    private final char symbol;

    HazardState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static HazardState fromSymbol(char symbol) {
        for (HazardState state : values()) {
            if (state.symbol == symbol) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown hazard symbol: " + symbol);
    }
}

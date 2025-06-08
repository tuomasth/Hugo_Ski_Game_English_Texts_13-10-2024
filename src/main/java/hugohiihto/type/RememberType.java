package hugohiihto.type;

import java.util.HashSet;
import java.util.Set;

public enum RememberType {
    ASTERISK('A'),
    BELL('B'),
    CLOCK('C'),
    DIAMOND('D'),
    HASH('H'),
    STAR('S');

    private final char symbol;

    RememberType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Set<Character> getSymbols(){
        Set<Character> symbols = new HashSet<>();
        for (RememberType rememberType : RememberType.values()) {
            symbols.add(rememberType.getSymbol());
        }
        return symbols;
    }

    public static RememberType fromSymbol(char symbol) {
        for (RememberType type : values()) {
            if (Character.toUpperCase(type.symbol) == Character.toUpperCase(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown RememberType symbol: " + symbol);
    }

}

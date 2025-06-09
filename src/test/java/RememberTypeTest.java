import hugohiihto.type.RememberType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RememberTypeTest {

    @Test
    void shouldReturnAsteriskForUpperCaseA() {
        Optional<RememberType> result = RememberType.fromSymbol('A');
        assertTrue(result.isPresent(), "Expected a value for symbol 'A'");
        assertEquals(RememberType.ASTERISK, result.get());
    }

    @Test
    void shouldReturnEmptyForLowerCaseA() {
        Optional<RememberType> result = RememberType.fromSymbol('a');
        assertTrue(result.isEmpty(), "Expected empty for lowercase 'a'");
    }

    @Test
    void shouldReturnEmptyForUnknownSymbol() {
        Optional<RememberType> result = RememberType.fromSymbol('?');
        assertTrue(result.isEmpty(), "Expected empty for unknown symbol '?'");
    }
}

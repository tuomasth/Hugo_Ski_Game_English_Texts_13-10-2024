import hugohiihto.type.RememberType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RememberTypeTest {

    @Test
    void validUpperCaseRememberType() {
        assertEquals(RememberType.ASTERISK, RememberType.fromSymbol('A'));
    }

    @Test
    void invalidLowerCaseRememberType() {
        assertThrows(IllegalArgumentException.class, () -> RememberType.fromSymbol('a'));
    }

    @Test
    void notFoundRememberType() {
        assertThrows(IllegalArgumentException.class, () -> RememberType.fromSymbol('?'));
    }

}

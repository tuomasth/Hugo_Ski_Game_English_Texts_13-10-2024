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
    void validLowerCaseRememberType() {
        assertEquals(RememberType.ASTERISK, RememberType.fromSymbol('a'));
    }

    @Test
    void invalidRememberType() {
        assertThrows(IllegalArgumentException.class, () -> RememberType.fromSymbol('?'));
    }

}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @Test
    void calculateFactorial_positiveNumber() {
        assertEquals(120, Factorial.calculateFactorial(5));
        assertEquals(1, Factorial.calculateFactorial(0));
    }
    @Test
    void calculateFactorial_negativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.calculateFactorial(-1));
    }
}
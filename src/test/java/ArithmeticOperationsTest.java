import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {
    @Test
    void add_validInput() {
        assertEquals(5, ArithmeticOperations.add(2, 3));
        assertEquals(-1, ArithmeticOperations.add(2, -3));
    }
    @Test
    void subtract_validInput() {
        assertEquals(-1, ArithmeticOperations.subtract(2, 3));
        assertEquals(5, ArithmeticOperations.subtract(2, -3));
    }
    @Test
    void multiply_validInput() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3));
        assertEquals(-6, ArithmeticOperations.multiply(2, -3));
    }
    @Test
    void divide_validInput() {
        assertEquals(2.0, ArithmeticOperations.divide(6, 3));
        assertEquals(-2.0, ArithmeticOperations.divide(6, -3));
    }
    @Test
    void divide_byZero() {
        assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.divide(6, 0));
    }
}

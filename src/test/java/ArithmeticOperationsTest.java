import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {
    @Test
    public void add_validInput() {
        assertEquals(ArithmeticOperations.add(2, 3), 5);
    }

    @Test
    public void subtract_validInput() {
        assertEquals(ArithmeticOperations.subtract(2, 3), -1);
    }

    @Test
    public void multiply_validInput() {
        assertEquals(ArithmeticOperations.multiply(2, 3), 6);
    }

    @Test
    public void divide_validInput() {
        assertEquals(ArithmeticOperations.divide(6, 3), 2.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void divide_byZero() {
        ArithmeticOperations.divide(6, 0);
    }
}

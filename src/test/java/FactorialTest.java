import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
    @Test
    public void calculateFactorial_positiveNumber() {
        assertEquals(Factorial.calculateFactorial(5), 120);
        assertEquals(Factorial.calculateFactorial(0), 1);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void calculateFactorial_negativeNumber() {
        Factorial.calculateFactorial(-1);
    }
}

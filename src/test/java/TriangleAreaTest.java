import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    @Test
    public void calculateArea_validInput() {
        assertEquals(TriangleArea.calculateArea(5, 4), 10.0);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void calculateArea_invalidInput_negativeBase() {
        TriangleArea.calculateArea(-5, 4);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void calculateArea_invalidInput_negativeHeight() {
        TriangleArea.calculateArea(5, -4);
    }
}

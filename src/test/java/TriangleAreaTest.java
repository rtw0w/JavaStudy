import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void calculateArea_validInput() {
        assertEquals(10.0, TriangleArea.calculateArea(5, 4));
        assertEquals(0.0, TriangleArea.calculateArea(0, 4));
    }

    @Test
    void calculateArea_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculateArea(-5, 4));
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculateArea(5, -4));
    }
}
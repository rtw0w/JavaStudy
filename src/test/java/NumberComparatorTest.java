import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    @Test
    void compare_greaterThan() {
        assertEquals("Первое число больше второго", NumberComparator.compare(5, 3));
    }
    @Test
    void compare_lessThan() {
        assertEquals("Первое число меньше второго", NumberComparator.compare(3, 5));
    }
    @Test
    void compare_equals() {
        assertEquals("Числа равны", NumberComparator.compare(3, 3));
    }
}

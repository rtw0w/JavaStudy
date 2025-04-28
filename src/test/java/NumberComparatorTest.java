import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    @Test
    public void compare_greaterThan() {
        assertEquals(NumberComparator.compare(5, 3), "Первое число больше второго");
    }
    @Test
    public void compare_lessThan() {
        assertEquals(NumberComparator.compare(3, 5), "Первое число меньше второго");
    }
    @Test
    public void compare_equals() {
        assertEquals(NumberComparator.compare(3, 3), "Числа равны");
    }
}
package algos;

import util.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void testCorrectness() {
        int[] a = new java.util.Random().ints(200, 0, 10000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        QuickSort.sort(a, m);
        assertArrayEquals(expected, a);
    }
}


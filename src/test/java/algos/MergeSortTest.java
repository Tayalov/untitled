package algos;

import util.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    public void testCorrectness() {
        int[] a = new java.util.Random().ints(200, 0, 10000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        assertArrayEquals(expected, a);
    }
}

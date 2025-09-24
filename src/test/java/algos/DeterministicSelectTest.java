package algos;

import util.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {
    @Test
    public void testSelect() {
        java.util.Random rnd = new java.util.Random();
        for (int t = 0; t < 50; t++) {
            int[] a = rnd.ints(200, 0, 10000).toArray();
            int k = rnd.nextInt(a.length);
            int[] copy = a.clone();
            Arrays.sort(copy);
            Metrics m = new Metrics();
            int res = DeterministicSelect.select(a.clone(), k, m);
            assertEquals(copy[k], res);
        }
    }
}



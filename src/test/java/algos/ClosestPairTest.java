package algos;

import util.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {
    @Test
    public void testClosestPairSmall() {
        java.util.Random rnd = new java.util.Random();
        Point[] pts = new Point[100];
        for (int i = 0; i < pts.length; i++) pts[i] = new Point(rnd.nextDouble()*100, rnd.nextDouble()*100);
        double expected = brute(pts);
        Metrics m = new Metrics();
        double got = ClosestPair.closestPairDistance(pts, m);
        assertEquals(expected, got, 1e-6);
    }

    private double brute(Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) for (int j = i+1; j < pts.length; j++) {
            double d = pts[i].distance(pts[j]);
            if (d < best) best = d;
        }
        return best;
    }
}


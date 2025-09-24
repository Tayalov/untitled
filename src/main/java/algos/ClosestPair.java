package algos;

import util.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    // Public API: with metrics (can pass null if not needed)
    public static double closestPairDistance(Point[] pts, Metrics m) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        Point[] byX = pts.clone();
        Arrays.sort(byX, new Comparator<Point>() {
            @Override public int compare(Point a, Point b) { return Double.compare(a.x, b.x); }
        });
        Point[] aux = new Point[pts.length];
        if (m != null) m.incAllocations(pts.length);
        return rec(byX, aux, 0, pts.length - 1, m);
    }

    // Convenience API: without metrics
    public static double closestPairDistance(Point[] pts) {
        return closestPairDistance(pts, null);
    }

    private static double rec(Point[] byX, Point[] aux, int left, int right, Metrics m) {
        if (m != null) m.enter();
        try {
            int n = right - left + 1;
            if (n <= 3) return bruteForce(byX, left, right);

            int mid = (left + right) >> 1;
            double midx = byX[mid].x;

            double dl = rec(byX, aux, left, mid, m);
            double dr = rec(byX, aux, mid + 1, right, m);
            double d = Math.min(dl, dr);

            // merge by y into aux[left..right]
            int i = left, j = mid + 1, k = left;
            while (i <= mid && j <= right) {
                if (byX[i].y <= byX[j].y) aux[k++] = byX[i++];
                else aux[k++] = byX[j++];
            }
            while (i <= mid) aux[k++] = byX[i++];
            while (j <= right) aux[k++] = byX[j++];

            for (i = left; i <= right; i++) byX[i] = aux[i];

            // collect strip
            int mCount = 0;
            for (i = left; i <= right; i++) {
                if (Math.abs(byX[i].x - midx) < d) aux[mCount++] = byX[i];
            }

            for (i = 0; i < mCount; i++) {
                for (j = i + 1; j < mCount && (aux[j].y - aux[i].y) < d; j++) {
                    double dist = aux[i].distance(aux[j]);
                    if (dist < d) d = dist;
                }
            }

            return d;
        } finally {
            if (m != null) m.exit();
        }
    }

    private static double bruteForce(Point[] a, int l, int r) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                double d = a[i].distance(a[j]);
                if (d < best) best = d;
            }
        }
        return best;
    }
}



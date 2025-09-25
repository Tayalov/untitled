package algos;

import util.Metrics;

public class ClosestPair {

    public static double closestPair(Point[] points) {
        Metrics.enter();
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Metrics.incComparisons();
                double dist = distance(points[i], points[j]);
                if (dist < minDist) minDist = dist;
            }
        }
        Metrics.exit();
        return minDist;
    }

    private static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}



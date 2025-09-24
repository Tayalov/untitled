package algos;

public class Point {
    public final double x;
    public final double y;
    public Point(double x, double y) { this.x = x; this.y = y; }

    public double distance(Point other) {
        double dx = x - other.x, dy = y - other.y;
        return Math.hypot(dx, dy);
    }
}

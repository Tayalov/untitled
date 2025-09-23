package algos;
public class Point {
    public final double x;
    public final double y;
    public Point(double x, double y) { this.x = x; this.y = y; }

    public double distance(Point o) {
        double dx = x - o.x, dy = y - o.y;
        return Math.hypot(dx, dy);
    }
}

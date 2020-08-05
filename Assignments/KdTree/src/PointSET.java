import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {

    private SET<Point2D> points;

    public PointSET() {
        points = new SET<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        ArrayList<Point2D> insideRectangle = new ArrayList<>();

        for (Point2D point : points) {
            if (rect.contains(point)) insideRectangle.add(point);
        }

        return insideRectangle;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (this.isEmpty()) return null;

        Point2D nearestNeighbor = null;
        Double nearestNeighborDistance = Double.POSITIVE_INFINITY;

        for (Point2D point : points) {
            Double currentDistance = p.distanceTo(point);

            if (Double.compare(currentDistance, nearestNeighborDistance) < 0) {
                nearestNeighbor = point;
                nearestNeighborDistance = currentDistance;
            }
        }

        return nearestNeighbor;
    }

}

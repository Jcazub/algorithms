import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private boolean isVertical;
    }

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        root = insert(p, root, true);
    }

    private Node insert(Point2D p, Node n, boolean isVertical) {
        if (n == null) {
            Node node = new Node();
            node.p = p;
            node.isVertical = isVertical;
            node.rect = buildRectangle(node.p);
            size++;
            return node;
        }

        if (n.p.compareTo(p) == 0) return n;

        int comp = 0;

        if (isVertical) {
           comp = Double.compare(p.x(), n.p.x());
        } else {
            comp = Double.compare(p.y(), n.p.y());
        }

        if (comp < 0) {
            n.lb = insert(p, n.lb, !n.isVertical);
        } else {
            n.rt = insert(p, n.rt, !n.isVertical);
        }

        return n;
    }

    private RectHV buildRectangle(Point2D p) {

        if (this.isEmpty()) return new RectHV(0, 0, 1, 1);

        double minx = 0;
        double miny = 0;
        double maxx = 1;
        double maxy = 1;

        Node n = root;
        int comp = 0;
        boolean isPointBigger = false;

        while (n != null && n.p.compareTo(p) != 0) {
            if (n.isVertical) {

                comp = Double.compare(p.x(), n.p.x());
                isPointBigger = comp >= 0;

               if (isPointBigger) {
                   minx = Math.max(minx, n.p.x());
               } else {
                   maxx = Math.min(maxx, n.p.x());
               }
            } else {

                comp = Double.compare(p.y(), n.p.y());
                isPointBigger = comp >= 0;

                if (isPointBigger) {
                    miny = Math.max(miny, n.p.y());
                } else {
                    maxy = Math.min(maxy, n.p.y());
                }
            }

            if (comp < 0) {
                n = n.lb;
            } else if (comp > 0) {
                n = n.rt;
            } else {
                if (n.p.compareTo(p) == 0) {
                    return new RectHV(minx, miny, maxx, maxy);
                } else {
                    n = n.rt;
                }
            }
        }

        return new RectHV(minx, miny, maxx, maxy);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return search(p) != null;
    }

    private Node search(Point2D p) {
        Node n = root;
        int comp = 0;

        while (n != null) {

            if (n.isVertical) {
                comp = Double.compare(p.x(), n.p.x());
            } else {
                comp = Double.compare(p.y(), n.p.y());
            }

            if (comp < 0) {
                n = n.lb;
            } else if (comp > 0) {
                n = n.rt;
            } else {
                if (n.p.compareTo(p) == 0) {
                    return n;
                } else {
                    n = n.rt;
                }
            }
        }

        return null;
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node n) {

        if (n == null) return;

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        n.p.draw();

        StdDraw.setPenRadius();

        if (n.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);

            Point2D startingPoint = new Point2D(n.p.x(), n.rect.ymin());
            startingPoint.drawTo(n.p);

            Point2D endingPoint = new Point2D(n.p.x(), n.rect.ymax());
            n.p.drawTo(endingPoint);

        } else {
            StdDraw.setPenColor(StdDraw.BLUE);

            Point2D startingPoint = new Point2D(n.rect.xmin(), n.p.y());
            startingPoint.drawTo(n.p);

            Point2D endingPoint = new Point2D(n.rect.xmax(), n.p.y());
            n.p.drawTo(endingPoint);
        }

        draw(n.lb);
        draw(n.rt);

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        ArrayList<Point2D> insideRectangle = new ArrayList<>();

        range(root, rect, insideRectangle);

        return insideRectangle;
    }

    private void range(Node node, RectHV rect, List<Point2D> insideRectangle) {

        if (node == null || !rect.intersects(node.rect)) return;
        if (rect.contains(node.p)) insideRectangle.add(node.p);

        range(node.lb, rect, insideRectangle);
        range(node.rt, rect, insideRectangle);

    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return nearest(root, p, null);
    }

    private Point2D nearest(Node node, Point2D p, Point2D closestPoint) {

        if (node == null) return closestPoint;
        if (closestPoint == null) closestPoint = node.p;

        double closestSoFarDistance = p.distanceSquaredTo(closestPoint);
        double possibleNewClosestDistance = p.distanceSquaredTo(node.p);

        if (Double.compare(closestSoFarDistance, possibleNewClosestDistance) > 0) {
            closestPoint = node.p;
        }

        boolean isSmaller;
        if (node.isVertical) {
            isSmaller = Double.compare(p.x(), node.p.x()) < 0;
        } else {
            isSmaller = Double.compare(p.y(), node.p.y()) < 0;
        }

        if (isSmaller) {
            closestPoint = nearest(node.lb, p, closestPoint);

            closestSoFarDistance = p.distanceSquaredTo(closestPoint);
            double distanceToRTRect = node.rt != null ?
                    node.rt.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;
            boolean isRbRectCloser = Double.compare(closestSoFarDistance, distanceToRTRect) >= 0;
            if (isRbRectCloser) closestPoint = nearest(node.rt, p, closestPoint);

        } else {
            closestPoint = nearest(node.rt, p, closestPoint);

            closestSoFarDistance = p.distanceSquaredTo(closestPoint);
            double distanceToLbRect = node.lb != null ?
                    node.lb.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;
            boolean isLbRectCloser = Double.compare(closestSoFarDistance, distanceToLbRect) >= 0;
            if (isLbRectCloser) closestPoint = nearest(node.lb, p, closestPoint);
        }

        return closestPoint;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        Point2D query = new Point2D(0.937, 0.537);

        System.out.println(kdtree.nearest(query));

    }

}

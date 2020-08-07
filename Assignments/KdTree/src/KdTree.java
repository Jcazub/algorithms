import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;

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
            n.lb = insert(p, n.lb, !isVertical);
        } else {
            n.rt = insert(p, n.rt, !isVertical);
        }

        return n;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return search(p) != null;
    }

    private Node search(Point2D p) {
        Node n = root;
        boolean isVertical = true;
        int comp = 0;

        while (n != null) {

            if (isVertical) {
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

            isVertical = !isVertical;
        }

        return null;
    }

    public void draw() {

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

        if (!contains(p)) return null;

        return nearest(root, p, null, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D closestPoint, boolean isVertical) {

        if (closestPoint == null) closestPoint = node.p;

        Double closestSoFarDistance = p.distanceSquaredTo(closestPoint);

        Double possibleNewClosestDistance = p.distanceSquaredTo(node.p);

        if (Double.compare(closestSoFarDistance, possibleNewClosestDistance) > 0) {
            closestPoint = node.p;
            closestSoFarDistance = p.distanceSquaredTo(closestPoint);
        }

        Double distanceToLbRect = node.lb.rect.distanceSquaredTo(p);
        Double distanceToRTRect = node.rt.rect.distanceSquaredTo(p);

        boolean isLbRectCloser = Double.compare(closestSoFarDistance, distanceToLbRect) >= 0;
        boolean isRbRectCloser = Double.compare(closestSoFarDistance, distanceToRTRect) >= 0;

        if (isLbRectCloser && isRbRectCloser) {
            if (isVertical) {
                boolean isPointSmaller = Double.compare(p.x(), node.p.x()) < 0;

                if (isPointSmaller) {
                    nearest(node.lb, p, closestPoint, false);
                    nearest(node.rt, p, closestPoint, false);
                } else {
                    nearest(node.rt, p, closestPoint, false);
                    nearest(node.lb, p, closestPoint, false);
                }
            } else {
                boolean isPointSmaller = Double.compare(p.y(), node.p.y()) < 0;

                if (isPointSmaller) {
                    nearest(node.lb, p, closestPoint, false);
                    nearest(node.rt, p, closestPoint, false);
                } else {
                    nearest(node.rt, p, closestPoint, false);
                    nearest(node.lb, p, closestPoint, false);
                }
            }

        } else if (isLbRectCloser) {
            nearest(node.lb, p, closestPoint, !isVertical);
        } else if (isRbRectCloser) {
            nearest(node.rt, p, closestPoint, !isVertical);
        }

        return closestPoint;

    }

    public static void main(String[] args) {

    }

    private void testInsert() {
        KdTree kd = new KdTree();

        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.3, 0.7);
        Point2D p3 = new Point2D(0.4, 0.3);
        Point2D p4 = new Point2D(0.2, 0.2);

        kd.insert(p1);
        kd.insert(p2);
        kd.insert(p3);
        kd.insert(p4);

        System.out.println(kd.contains(new Point2D(0.4, 0.4)));
        System.out.println(kd.size());
    }

    private void testCl

}

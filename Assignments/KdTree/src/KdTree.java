import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;

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

        return null;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return null;
    }

    public static void main(String[] args) {
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

}

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;
    private Point[] pointsSortedOrder;
    private Point[] pointsNaturalOrder;

    public FastCollinearPoints(Point[] points)
    {
        verifyPointsNotNull(points);
        initializeVariables(points);
        verifyNoDuplicates();
        findCollinearLineSegments();
    }

    public int numberOfSegments()
    {
        return lineSegments.size();
    }

    public LineSegment[] segments()
    {
        return lineSegments.toArray(new LineSegment[0]);
    }

    private void findCollinearLineSegments()
    {
        for (int i = 0; i < pointsNaturalOrder.length; i++)
        {
            pointsSortedOrder = pointsNaturalOrder.clone();
            Arrays.sort(pointsSortedOrder, pointsNaturalOrder[i].slopeOrder());
            findPointsWithSameSlope();
        }
    }

    private void findPointsWithSameSlope()
    {
        int sameSlopeCount = 1;
        int pointsLength = pointsNaturalOrder.length;
        int i = 0;
        while (i < pointsLength - 1)
        {
            if (onSameLine(i)) {
                sameSlopeCount++;
                int k = i + 1;
                while (k < pointsSortedOrder.length && stillSameLine(i, k)) {
                    sameSlopeCount++;
                    k++;
                }
                if (sameSlopeCount > 3) {
                    int endPoint = k - 1;
                    if (verifyLineOrder(i, endPoint)) {
                        LineSegment ls = new LineSegment(pointsSortedOrder[0], pointsSortedOrder[endPoint]);
                        lineSegments.add(ls);

                        sameSlopeCount = 1;
                        i = k - 1;
                    } else {
                        sameSlopeCount = 1;
                        i = k - 1;
                    }
                } else {
                    sameSlopeCount = 1;
                    i = k - 1;
                }
            }
            i++;
        }
    }

    private boolean verifyLineOrder(int nextPoint, int endPoint) {
        boolean lessThanNextPoint = pointsSortedOrder[0].compareTo(pointsSortedOrder[nextPoint]) < 0;
        boolean lessThanLastPoint = pointsSortedOrder[0].compareTo(pointsSortedOrder[endPoint]) < 0;
        return lessThanNextPoint && lessThanLastPoint;
    }

    private boolean onSameLine(int i)
    {
        double firstSlope = pointsSortedOrder[0].slopeTo(pointsSortedOrder[i]);
        double secondSlope = pointsSortedOrder[0].slopeTo(pointsSortedOrder[i + 1]);
        return Double.compare(firstSlope, secondSlope) == 0;
    }

    private boolean stillSameLine(int i, int k)
    {
        double firstSlope = pointsSortedOrder[0].slopeTo(pointsSortedOrder[i]);
        double secondSlope = pointsSortedOrder[i].slopeTo(pointsSortedOrder[k]);
        return Double.compare(firstSlope, secondSlope) == 0;
    }

    /*
    MEMBER INTITALIZATIONS
     */
    private void initializeVariables(Point[] points)
    {
        this.lineSegments = new ArrayList<>();

        pointsNaturalOrder = Arrays.copyOf(points, points.length);
        pointsSortedOrder = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsNaturalOrder);
    }

    /*
    * INPUT VERIFICATION
    * */
    private void verifyPointsNotNull(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException();
        verifyEachPointNotNull(points);
    }

    private void verifyEachPointNotNull(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {

            if (points[i] == null) throw new IllegalArgumentException();
        }
    }

    private void verifyNoDuplicates() {
        for (int i = 0; i < pointsNaturalOrder.length - 1; i++) {
            if (pointsNaturalOrder[i].compareTo(pointsNaturalOrder[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {
        // test code here
    }
}

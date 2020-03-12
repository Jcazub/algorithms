import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;


    public BruteCollinearPoints(Point[] points)
    {
        verifyPoints(points);

        this.lineSegments = new ArrayList<>();

        // evaluate every 4 points
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i + 1; j < points.length; j++)
            {
                for (int k = j + 1; k < points.length; k++)
                {
                    for (int m = k + 1; m < points.length; m++)
                    {
                        double slopeIJ = slopeBetween(points, i, j);

                        if (Double.compare(slopeIJ, slopeBetween(points, i, k)) == 0 &&
                                Double.compare(slopeIJ, slopeBetween(points, i, m)) == 0)
                        {
                            Point[] line = {points[i], points[j], points[k], points[m]};
                            Arrays.sort(line);

                            lineSegments.add(new LineSegment(line[0], line[3]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    // ensures there are no null or duplicate points
    private static void verifyPoints(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++)
        {
            for (int k = i + 1; k < points.length; k++) {
                if (points[i].compareTo(points[k]) == 0) throw new IllegalArgumentException();
            }
        }
    }

    // determine slope between points
    private static double slopeBetween(Point[] points, int i, int j)
    {
        return points[i].slopeTo(points[j]);
    }

    public static void main(String[] args) {
        // test code here
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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

                        if (Double.compare(slopeIJ, slopeBetween(points, i, k)) != 0 &&
                                Double.compare(slopeIJ, slopeBetween(points, i, m)) != 0)
                        {
                            // determine lowest and highest point
                            Point[] line = {points[i], points[j], points[k], points[m]};
                            Arrays.sort(line);



                            // add to line segments array
                            LineSegment lineSegment = new LineSegment(line[0], line[3]);
                            if (!detectLineSegmentDuplicates(lineSegments, lineSegment))
                            {
                                lineSegments.add(lineSegment);
                            }
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

            for (int k = i + 1; k < points.length; k++)
            {
                if (points[i].compareTo(points[k]) == 0) throw new IllegalArgumentException();
            }
        }
    }

    // determine slope between points
    private static double slopeBetween(Point[] points, int i, int j)
    {
        return points[i].slopeTo(points[j]);
    }

    // ensure no duplicates in line segment array
    private static boolean detectLineSegmentDuplicates(List<LineSegment> lineSegments, LineSegment lineSegment)
    {
        for (LineSegment currentLine : lineSegments)
        {
            if (lineSegment.toString().equals(currentLine.toString())) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

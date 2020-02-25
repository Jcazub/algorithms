import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;
    private Point[] auxPoints;
    private List<Point> startingPoints;

    public FastCollinearPoints(Point[] points)
    {
        verifyAllPoints(points);
        initializeVariables(points);
        copyToAuxArray(points);
        findLineSegments(points);
    }

    public int numberOfSegments()
    {
        return lineSegments.size();
    }

    public LineSegment[] segments()
    {
        return lineSegments.toArray(new LineSegment[0]);
    }



    private void findLineSegments(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {
            Arrays.sort(auxPoints, points[i].slopeOrder());
            Point[] collinearPoints = findPointsWithSameSlope();
            Arrays.sort(collinearPoints);
            if (!detectLineSegmentDuplicates(collinearPoints[0]))
            {
                LineSegment ls = new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]);
                lineSegments.add(ls);
            }
        }
    }

    private Point[] findPointsWithSameSlope()
    {
        Point mainPoint = auxPoints[0];

        ArrayList<Point> samePoints = new ArrayList<>();
        samePoints.add(mainPoint);
        for (int i = 1; i < auxPoints.length; i++)
        {
            int j = i + 1;
            double slope = mainPoint.slopeTo(auxPoints[i]);

            while (j < auxPoints.length)
            {
                if (Double.compare(slope, mainPoint.slopeTo(auxPoints[j])) != 0)
                {
                    break;
                }
                j++;
            }

            if (j - i > 2)
            {
                for (int k = i; k < j; k++)
                {
                    samePoints.add(auxPoints[k]);
                }
            }
        }
        return samePoints.size() > 3 ? samePoints.toArray(new Point[0]) : new Point[0];
    }

    private void initializeVariables(Point[] points)
    {
        this.lineSegments = new ArrayList<>();
        this.auxPoints = new Point[points.length];
        this.startingPoints = new ArrayList<>();
    }

    private void copyToAuxArray(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {
            auxPoints[i] = points[i];
        }
    }

    // ensures there are no null or duplicate points
    private void verifyAllPoints(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException();
        verifyEachPoint(points);

    }

    private void verifyEachPoint(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null) throw new IllegalArgumentException();

            for (int k = i + 1; k < points.length; k++)
            {
                if (points[i].compareTo(points[k]) == 0) throw new IllegalArgumentException();
            }
        }
    }

    // ensure no duplicates in line segment array
    private boolean detectLineSegmentDuplicates(Point startingPointToCheck)
    {
        for (Point currentPoint : startingPoints)
        {
            if (currentPoint.compareTo(startingPointToCheck) == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // test client code here
        System.out.println(Double.compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }
}

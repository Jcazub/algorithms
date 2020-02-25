package Part1.Week2.Sorts;

public class QuicksortImproved extends Sort {

    private static final int CUTOFF = 10;

    public static void sort(Comparable[] a)
    {
        // shuffle first
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        // cutoff for small subarrays
        if (hi <= lo + CUTOFF - 1) {
            InsertionSort.sort(a, lo, hi);
            return;
        }

        //int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
        //exch(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi + 1;
        while (true)
        {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }

            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }
}

package Part1.Week3.QuickSort;

import Part1.Week2.Sorts.Sort;

public class Quicksort extends Sort {

    public static void sort(Comparable[] a)
    {
        // shuffle first
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    static int partition(Comparable[] a, int lo, int hi)
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

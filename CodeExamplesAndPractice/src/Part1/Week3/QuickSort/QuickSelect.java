package Part1.Week3.QuickSort;

public class QuickSelect extends Quicksort {

    public static Comparable select(Comparable[] a, int k)
    {
        // CODE TO SHUFFLE HERE
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[j];
        }
        return a[lo];

    }
}

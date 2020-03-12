package Part1.Week3.QuickSort;

import Part1.Week2.Sorts.Sort;

public class Quicksort3Way extends Sort {

    public void sort(Comparable[] a)
    {
        //  SHUFFLE CODE HERE
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i);
            else i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

}

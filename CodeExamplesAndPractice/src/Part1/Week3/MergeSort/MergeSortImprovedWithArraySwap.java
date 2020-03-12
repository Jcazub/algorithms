package Part1.Week3.MergeSort;

import Part1.Week2.Sorts.InsertionSort;
import Part1.Week2.Sorts.Sort;

public class MergeSortImprovedWithArraySwap extends Sort {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        // ensures sub-arrays are sorted
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy elements to auxiliary array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        // using aux array, merge sub-arrays back into original array
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
        {
            // if left side is exhausted, copy rest of right
            if (i > mid) aux[k++] = a[j++];
            // if right side is exhausted, copy rest of left
            else if (j < mid) aux[k++] = a[i++];
            else if (less(aux[j], aux[i])) aux[k++] = a[j++];
            else aux[k++] = a[j++];
        }

        // ensure array is sorted
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        int CUTOFF = 7;
        if (hi <= lo + CUTOFF - 1)
        {
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // swaps position of aux and a to cut down on array copies
        sort (aux, a, lo, mid);
        sort (aux, a, mid+1, hi);
        if (!less(a[mid+1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int start, int end)
    {
        if (end <= start) return true;

        for (int i = start + 1; i <= end; i++)
        {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }
}

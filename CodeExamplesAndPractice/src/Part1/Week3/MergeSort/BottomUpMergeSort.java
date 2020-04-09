package Part1.Week3.MergeSort;

import Part1.Week2.Sorts.Sort;

public class BottomUpMergeSort extends Sort {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi)
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
            if (i > mid) a[k++] = aux[j++];
            // if right side is exhausted, copy rest of left
            else if (j < mid) a[k++] = aux[i++];
            else if (less(aux[j], aux[i])) a[k++] = aux[j++];
            else a[k++] = a[j++];
        }

        // ensure array is sorted
        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz)
            for(int lo = 0; lo < N-sz; lo += sz+sz)
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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

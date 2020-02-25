package Part1.Week2.Sorts;

public abstract class Sort {

    protected static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) > 0l;
    }

    protected static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[j] = a[i];
        a[j] = swap;
    }

}

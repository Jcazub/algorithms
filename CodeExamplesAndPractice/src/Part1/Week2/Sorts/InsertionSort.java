package Part1.Week2.Sorts;

public class InsertionSort extends Sort {

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (less(a[j],a[j-1]))
                {
                    exch(a, j, j - 1);
                }
                else break;
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        for (int i = lo; i < hi; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (less(a[j],a[j-1]))
                {
                    exch(a, j, j - 1);
                }
                else break;
            }
        }
    }
}

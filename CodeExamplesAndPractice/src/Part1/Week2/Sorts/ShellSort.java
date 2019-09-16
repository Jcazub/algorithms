package Part1.Week2.Sorts;

public class ShellSort extends Sort {

    public static void sort(Comparable[] a)
    {
        int N = a.length;

        // finds highest increment of 3x+1
        int h = 1;
        while (h < N/3) h = 3*h + 1;

        while (h >= 1)
        {
            // h-sort the array
            for (int i = h; i < N; i++)
            {
                for (int j = i; j >= h && less(a[j], a [j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
        }
    }

}

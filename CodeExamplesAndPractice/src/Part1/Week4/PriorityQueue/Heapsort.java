package Part1.Week4.PriorityQueue;

public class Heapsort {

    public static void sort(Comparable pq[])
    {
        int N = pq.length;

        // first pass, heap construction
        for (int k = N/2; k >= 1; k--) sink(pq, k, N);

        // second pass, final sorting
        while (N > 1) {
            exch(pq, 1, N);
            sink(pq, 1, --N);
        }
    }

    private static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    /* Indices decremented in less and exch to simulate a 1 based array for a 0 based array*/

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[--i].compareTo(pq[--j]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable t = pq[--i];
        pq[i] = pq[--j];
        pq[j] = t;
    }
}

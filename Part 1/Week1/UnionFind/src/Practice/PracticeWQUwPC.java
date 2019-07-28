package Practice;

public class PracticeWQUwPC {

    private int[] id;
    private int[] sz;

    public PracticeWQUwPC(int N)
    {

        id = new int[N];
        sz = new int[N];

        for(int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int i)
    {

        while (i != id[i] )
        {
            i = id[id[i]];
        }

        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {

        int rootp = root(p);
        int rootq = root(q);

        if (rootp == rootq) return;

        if (sz[rootp] > sz[rootq])
        {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];
        }
        else
        {
            id[rootq] = rootp;
            sz[rootq] += sz[rootq];
        }


    }

}

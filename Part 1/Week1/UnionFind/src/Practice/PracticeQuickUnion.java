package Practice;

public class PracticeQuickUnion {

    private int[] root;

    public PracticeQuickUnion(int N) {

        root = new int[N];

        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    private int root(int i) {
        while (root[i] != i) i = root[i];

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int p_root = root(p);
        int q_root = root(q);

        root[p_root] = q_root;
    }
}

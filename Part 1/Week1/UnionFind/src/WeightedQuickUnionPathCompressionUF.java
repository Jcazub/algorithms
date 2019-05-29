public class WeightedQuickUnionPathCompressionUF {

    //array to track object connections
    //uses a tree-like system to track connections
    //2 objects are connected if they have the same root
    private int[] id;

    //array to track the size of each tree
    private int[] sz;

    public WeightedQuickUnionPathCompressionUF(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    //checks if the object is the parent of a tree
    //if not, goes one level above and repeats process till it arrives at the parent
    //on each pass, points each node to its grandparent, thus reducing tree depth
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    //checks if the objects have the same parent
    //if so, they are connected
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    //connects 2 objects by pointing them to the same parent
    //checks the size of each tree and connects the smaller one to the larger one
    //updates the size of the resulting tree
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}

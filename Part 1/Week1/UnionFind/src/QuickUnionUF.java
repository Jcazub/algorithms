public class QuickUnionUF {

    //array to track object connections
    //uses a tree-like system to track connections
    //2 objects are connected if they have the same root
    private int[] id;

    public QuickUnionUF(int N) {
        id = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    //checks if the object is the parent of a tree
    //if not, goes one level above and repeats process till it arrives at the parent
    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    //checks if the objects have the same parent
    //if so, they are connected
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    //connects 2 objects by pointing them to the same parent
    //essentially joins 2 trees together
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        id[i] = j;

    }
}

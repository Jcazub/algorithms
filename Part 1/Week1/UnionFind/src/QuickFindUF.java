public class QuickFindUF {

    //array to track object connections
    //stored value points to a singular object that is connected to all other objects in the component
    private int[] id;

    public QuickFindUF(int N) {

        id = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

    }

    //checks if the objects are connected, i.e. are they connected to the same object
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    //symbolically merges the components of 2 objects
    //replaces the id of all objects connected to p to connect to q
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }

    }
}

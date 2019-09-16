package Part1.Week1.Practice;

public class SocialConnectivityUF {

    private int[] id;
    private int[] sz;

    public SocialConnectivityUF(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int count() {
        return id.length;
    }

    private int root(int i) {

        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int union(int p, int q) {
        int pid = root(p);
        int qid = root(q);

        if (pid == qid) return sz[pid];

        if (sz[pid] > sz[qid ]) {
            id[qid] = pid;
            sz[pid] += sz[qid];
            return sz[pid];
        } else {
            id[pid] = qid;
            sz[qid] += sz[qid];
            return sz[qid];
        }

    }

}

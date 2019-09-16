package Part1.Week1.Practice;

public class PracticeQuickFind {

    private int[] id;

    public PracticeQuickFind(int N) {
        id = new int[N];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public boolean find(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {

        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qid) id[i] = pid;
        }

    }
}

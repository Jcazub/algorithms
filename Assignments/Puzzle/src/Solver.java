import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class Solver {

    private SearchNode finalSearchNode;

    private class SearchNode implements Comparable<SearchNode> {
        int moves;
        int manhattan;
        Board currentBoard;
        SearchNode prevNode;

        public SearchNode(Board currentBoard, SearchNode prevNode, int moves) {
            this.currentBoard = currentBoard;
            this.prevNode = prevNode;
            this.moves = moves;
            this.manhattan = currentBoard.manhattan();
        }

        private int priority() {
            return moves + manhattan;
        }

        @Override
        public int compareTo(SearchNode searchNode) {
            return Integer.compare(this.priority(), searchNode.priority());
        }

        @Override
        public boolean equals(Object y) {
            if (y == null) return false;

            if (y == this) return true;

            if (y.getClass() != this.getClass()) return false;

            SearchNode that = (SearchNode) y;

            if (this.moves != that.moves) return false;
            if (!this.prevNode.currentBoard.equals(that.prevNode.currentBoard)) return false;
            if (!this.currentBoard.equals(that.currentBoard)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(moves, manhattan, currentBoard, prevNode);
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        Board twin = initial.twin();
        solveParallel(initial, twin);
    }

    public boolean isSolvable() {
        return finalSearchNode != null;
    }

    public int moves() {
        if (finalSearchNode != null) return finalSearchNode.moves;
        else return -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> solutionPath = new Stack<>();

        SearchNode node = finalSearchNode;

        while (node != null) {
            solutionPath.push(node.currentBoard);
            node = node.prevNode;
        }

        return solutionPath;
    }

    private boolean solveParallel(Board initial, Board twin) {
        MinPQ<SearchNode> pq = getPQ(initial);
        MinPQ<SearchNode> twinPq = getPQ(twin);

        SearchNode node = pq.min();
        SearchNode twinNode = twinPq.min();

        while (!node.currentBoard.isGoal() && !twinNode.currentBoard.isGoal()) {
            node = processNodes(pq);
            twinNode = processNodes(twinPq);
        }

        if (twinNode.currentBoard.isGoal()) return false;

        finalSearchNode = node;
        return true;
    }

    private SearchNode processNodes(MinPQ<SearchNode> priorityQueue) {
        SearchNode node = priorityQueue.delMin();

        Iterable<Board> neighbors = node.currentBoard.neighbors();

        for (Board currentNeighbor : neighbors) {
            SearchNode currentNode = new SearchNode(currentNeighbor, node, node.moves + 1);
            if (node.prevNode == null || !currentNeighbor.equals(node.prevNode.currentBoard))
                priorityQueue.insert(currentNode);
        }
        return node;
    }

    private MinPQ<SearchNode> getPQ(Board initial) {
        SearchNode initialSearchNode = new SearchNode(initial, null, 0);
        MinPQ<SearchNode> pq = new MinPQ<>();

        pq.insert(initialSearchNode);

        return pq;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

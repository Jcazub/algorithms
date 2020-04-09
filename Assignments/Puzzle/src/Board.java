import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public final class Board {

    private final int[][] tiles;
    private final int n;

    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(tiles[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int tile = tiles[row][col];
                if (tile == 0) continue;

                int truePosition = tile - 1;
                int currentPosition = (n * row) + col;

                if (truePosition != currentPosition) count++;
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int tile = tiles[row][col];
                if (tile == 0) continue;

                int truePosition = tile - 1;

                int truePositionRow = truePosition / n;
                int truePositionCol = truePosition % n;

                int distance = Math.abs(row - truePositionRow) + Math.abs(col - truePositionCol);

                count += distance;
            }
        }

        return count;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;

        if (this.getClass() != y.getClass()) return false;

        Board that = (Board) y;

        if (that.dimension() != this.dimension()) return false;

        return Arrays.deepEquals(this.tiles, that.tiles);
    }

    public Iterable<Board> neighbors() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (tiles[row][col] == 0) {
                    return generateNeighborBoards(new int[]{row, col});
                }
            }
        }
        return null;
    }

    private List<int[]> getNeighbors(int row, int col) {
        List<int[]> neighbors = new ArrayList<>();
        if (0 <= row - 1 && row - 1 < n) neighbors.add(new int[]{row - 1, col});
        if (0 <= row + 1 && row + 1 < n) neighbors.add(new int[]{row + 1, col});
        if (0 <= col - 1 && col - 1 < n) neighbors.add(new int[]{row, col - 1});
        if (0 <= col + 1 && col + 1 < n) neighbors.add(new int[]{row, col + 1});
        return neighbors;
    }

    private Iterable<Board> generateNeighborBoards(int[] indices) {
        List<int[]> neighbors = getNeighbors(indices[0], indices[1]);
        ArrayList<Board> neighborBoards = new ArrayList<>();

        for (int[] neighbor : neighbors) {
            Board board = new Board(tiles);
            board.swap(indices, neighbor);
            neighborBoards.add(board);
        }

        return neighborBoards;
    }

    private void swap(int[] a, int[] b) {
        int temp = tiles[a[0]][a[1]];
        tiles[a[0]][a[1]] = tiles[b[0]][b[1]];
        tiles[b[0]][b[1]] = temp;
    }


    public Board twin() {
        Board twin = new Board(tiles);

        int[] a = twin.tiles[0][0] != 0 ? new int[]{0, 0} : new int[]{1, 0};
        int[] b = twin.tiles[0][1] != 0 ? new int[]{0, 1} : new int[]{1, 1};

        twin.swap(a, b);

        return twin;
    }

    public static void main(String[] args) {
        // used for testing
        int[][] tiles = {{0, 2}, {3, 1}};
        Board board = new Board(tiles);

        System.out.println(board.twin());
    }
}

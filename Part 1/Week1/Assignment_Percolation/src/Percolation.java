import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private boolean[] openSites;
    private final int inputNumber;
    private final int gridSize;
    private int numberOfOpenSites;

    public Percolation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException();
        }

        inputNumber = n;
        gridSize = (n*n) + 2;
        numberOfOpenSites = 0;

        uf = new WeightedQuickUnionUF(gridSize);

        // connect top virtual site
        for (int i = 1; i <= inputNumber; i++) {
            uf.union(0,i);
        }

        // connect bottom virtual site
        for (int i = (inputNumber * (inputNumber - 1)) + 1; i < inputNumber * inputNumber; i++) {
            uf.union(gridSize - 1, i);
        }

        openSites = new boolean[gridSize];
        for (int i = 0; i < gridSize; i++) openSites[i] = false;


    }

    // create n-by-n grid, with all sites blocked

    public void open(int row, int col) {
        validateIndices(row,col);

        if (!isOpen(row, col)) {
            int index = xyto1D(row, col);
            openSites[index] = true;
            numberOfOpenSites++;

            // OPEN NEIGHBORS
            connectNeighbor(row + 1, col, index);
            connectNeighbor(row - 1, col, index);
            connectNeighbor(row, col + 1, index);
            connectNeighbor(row, col - 1, index);
        }

    }  // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        validateIndices(row,col);
        int index = xyto1D(row, col);
        return openSites[index] == true;
    }// is site (row, col) open?

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            if (uf.connected(0, xyto1D(row, col))) {
                return true;
            }
            return false;
        }
        return false;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
//        int n = 0;
//        for (int i = 0; i < openSites.length; i++) {
//            if (openSites[i] == 1) {
//                n++;
//            }
//        }
//
//        return n;
        return numberOfOpenSites;
    }// number of open sites

    public boolean percolates() {
        return uf.connected(0,gridSize - 1);
    }          // does the system percolate?

    private void validateIndices(int row, int col) {
        if (row < 1 || row > inputNumber) {
            throw new IllegalArgumentException("row index i out of bounds");
        }

        if (col < 1 || col > inputNumber) {
            throw new IllegalArgumentException("row index i out of bounds");
        }

    }

    private int xyto1D(int row, int col) {
        validateIndices(row,col);
        int index;
        if (row == 1) {
            index = col;
        } else {
            index = (inputNumber * (row - 1)) + col;
        }
        return index;
    }

    private boolean checkNeighborIndices(int row, int col) {
        try {
            validateIndices(row, col);
            return true;
        } catch (IllegalArgumentException e) {

        }
        return false;
    }

    private void connectNeighbor(int row, int col, int index) {
        if (checkNeighborIndices(row, col)) {
            if (isOpen(row, col)) {
                uf.union(index, xyto1D(row,col));
            }
        }

    }

    // test client (optional)
}
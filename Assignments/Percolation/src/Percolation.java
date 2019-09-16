import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] openSites;
    private final int n;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private final WeightedQuickUnionUF siteGrid;
    private final WeightedQuickUnionUF siteGridShadow;
    private int numberOfOpenSites;
    private boolean isABottomSiteOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0) throw new IllegalArgumentException();

        // set variables
        this.n = n;
        numberOfOpenSites = 0;
        virtualTopSite = 0;
        virtualBottomSite = (n*n) + 1;
        siteGrid = new WeightedQuickUnionUF((n*n) + 2);
        isABottomSiteOpen = false;

        // siteGridShadow solves backwash issue
        siteGridShadow = new WeightedQuickUnionUF((n*n) + 2);
        openSites = new boolean[(n*n) + 2];

        // connect top row to virtual top site
        for (int i = 1; i <= n; i++)
        {
            int currentTopRowIndex = convertRowColToIndex(1, i, n);
            siteGrid.union(virtualTopSite, currentTopRowIndex);
            siteGridShadow.union(virtualTopSite, currentTopRowIndex);
        }

        // connect bottom row to virtual bottom site
        for (int i = 1; i <= n; i++)
        {
            int bottomRowIndex = convertRowColToIndex(n, i, n);
            siteGrid.union(virtualBottomSite, bottomRowIndex);
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        int index = convertRowColToIndex(row, col, n);

        // marks site as open
        if (!openSites[index])
        {
            openSites[index] = true;
            numberOfOpenSites++;

            // connects site to adjacent sites
            connectToNeighbors(index, row, col);

            if (row == n) isABottomSiteOpen = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        int index = convertRowColToIndex(row, col, n);
        return openSites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        int index = convertRowColToIndex(row, col, n);
        if (!isOpen(row, col)) return false;
        return siteGridShadow.connected(virtualTopSite, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates()
    {
        if (isABottomSiteOpen) return siteGrid.connected(virtualTopSite, virtualBottomSite);
        else return false;
    }

    // converts row, col position to array index
    private static int convertRowColToIndex(int row, int col, final int n)
    {
        // System.out.println("row: " + row + ", col: " + col + ", N: " + n);
        if (!(1 <= row && row <= n) || !(1 <= col && col <= n)) throw new IllegalArgumentException();
        row--;
        return (row*n) + col;
    }

    private void connectToNeighbors(int index, int row, int col)
    {
        int[][] neighbors = {{row, col + 1}, {row, col - 1}, {row + 1, col}, {row - 1, col}};

        for (int[] currentNeighbor : neighbors)
        {
            try {
                if (isOpen(currentNeighbor[0], currentNeighbor[1]))
                {
                    int neighborIndex = convertRowColToIndex(currentNeighbor[0], currentNeighbor[1], n);
                    siteGrid.union(index, neighborIndex);
                    siteGridShadow.union(index, neighborIndex);
                }
            }
            catch (IllegalArgumentException e)
            {
                // Do nothing
            }
        }
    }


}

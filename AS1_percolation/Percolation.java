import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF qu;
    private final WeightedQuickUnionUF checkFull;
    private int[][] grid;
    private int numOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        qu = new WeightedQuickUnionUF(n * n + 2); // additional 2: virtual top/bottom
        checkFull = new WeightedQuickUnionUF(n * n + 1);

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            qu.union(0, i + 1); // union the top row with virtual top
            checkFull.union(0, i + 1);
            qu.union(n * n + 1, n * n - i); // union the bottom row with virtual bottom
            numOpen = 0;
        }
    }

    // finds the index of qu
    private int index(int row, int col) {
        return (row - 1) * grid.length + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > grid.length || col < 0 || col > grid.length) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }

        grid[row - 1][col - 1] = 1;
        numOpen++;
        // connect left/right/up/down if it is opened
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                qu.union(index(row, col), index(row, col - 1));
                checkFull.union(index(row, col), index(row, col - 1));
            }
        }
        if (col < grid.length) {
            if (isOpen(row, col + 1)) {
                qu.union(index(row, col), index(row, col + 1));
                checkFull.union(index(row, col), index(row, col + 1));
            }
        }
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                qu.union(index(row, col), index(row - 1, col));
                checkFull.union(index(row, col), index(row - 1, col));
            }
        }
        if (row < grid.length) {
            if (isOpen(row + 1, col)) {
                qu.union(index(row, col), index(row + 1, col));
                checkFull.union(index(row, col), index(row + 1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > grid.length || col < 0 || col > grid.length) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > grid.length || col < 0 || col > grid.length) {
            throw new IllegalArgumentException();
        }
        return isOpen(row, col) && checkFull.connected(index(row, col), 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) {
            return isOpen(1, 1);
        }
        return qu.connected(0, grid.length * grid.length - 1);
    }
}

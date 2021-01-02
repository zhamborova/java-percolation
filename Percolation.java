import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private Cell[][] grid;
    private int n;
    private int sites;
    private WeightedQuickUnionUF uf;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw  new IllegalArgumentException("n must bigger than 0");
        }

        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new Cell[n][n];
        sites = 0;
        int id = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                grid[i][j] = new Cell(id);
                id++;
            }
        }
    }

    private int getId(int i, int j) {
            return grid[i][j].id;
    }

    private boolean validRange(int i, int j) {
      return !(i < 0 || i >= n || j < 0 || j >= n);
    }

    private String getState(int row, int col) {
        return  grid[row][col].state;
    }
    private void error(int i, int j) {
       if(!(this.validRange(i, j)))
           throw new IllegalArgumentException("invalid range " + i + " " + j);
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        error(row-1, col-1);
        if (getState(row-1, col-1).equals("blocked")) {
            this.grid[row-1][col-1].setState("open");
            this.sites += 1;
            this.connectToNeighbours(row-1, col-1);

        }
    }

    private void connectToNeighbours(int i, int j) {
        int id = getId(i, j);
        int top = validRange(i - 1, j) ? getId(i - 1, j) : -1;
        int bottom = validRange(i+1, j) ? getId(i+1, j) : -1;
        int left = validRange(i, j - 1) ? getId(i, j - 1) : -1;
        int right = validRange(i, j + 1) ? getId(i, j + 1) : -1;
        if (i == 0) {
            uf.union(id, 0);
        }
        if (i == n-1) {
            uf.union(id, n*n + 1);
        }

        if (bottom != -1 && isOpen(i+2, j+1)) {
            uf.union(id, bottom);
        }
        if (top != -1 && isOpen(i, j + 1)) {
            uf.union(id, top);
        }

        if (left != -1 && isOpen(i+1, j )) {
            uf.union(id, left);
        }

        if (right != -1 && isOpen(i+1, j + 2)) {
            uf.union(id, right);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
     error(row -1, col -1);
     return this.getState(row-1, col-1).equals("open");
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
      error(row -1 , col -1);
      return uf.connected(getId(row-1, col-1), 0);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return  this.sites;
    }


    // does the system percolate?
    public boolean percolates() {
        return uf.connected(n*n+1, 0);
    }



    // test client (optional)
    public static void main(String[] args) {
       Percolation percolation = new Percolation(10);
       System.out.println(percolation.numberOfOpenSites());
    }
}
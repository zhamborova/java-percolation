/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] stats;
    private final int trials;
    private final double s = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("invalid input");
        stats = new double[trials];
        this.trials = trials;
        while (trials > 0) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int i = StdRandom.uniform(1, n+1);
                int j = StdRandom.uniform(1, n+1);
                perc.open(i, j);
            }
            stats[trials-1] = (double) perc.numberOfOpenSites() / (n*n);
            trials--;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (s * this.stddev())/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return this.mean() + (s * this.stddev())/Math.sqrt(trials);
    }


    public static void main(String[] args) {
        int trials = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[0]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean() = " +  stats.mean());
        System.out.println("stddev() = "+ stats.stddev());
        System.out.println("95% confidence interval = " + "[" + stats.confidenceLo() + ", "
                        + stats.confidenceHi() + "]");
    }
}

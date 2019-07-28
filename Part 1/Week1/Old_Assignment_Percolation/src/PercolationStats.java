import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percolationThresholdEstimate;
    private final int trials;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) {

        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }

        Percolation p;
        this.trials = trials;
        percolationThresholdEstimate = new double[trials];

        for (int i = 0; i < trials; i++) {

            p = new Percolation(n);

            while (!p.percolates()) {

//                int row = (int)((Math.random() * n) + 1);
//                int col = (int)((Math.random() * n) + 1);
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                p.open(row, col);
            }

            double g = p.numberOfOpenSites()/((double) n*n);

            percolationThresholdEstimate[i] = g;

        }

        this.mean = StdStats.mean(percolationThresholdEstimate);
        this.stddev = StdStats.stddev(percolationThresholdEstimate);

        // System.out.println((int) (Math.random() * n) + 1);

    }    // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return this.mean;
    }                         // sample mean of percolation threshold
    public double stddev() {
        return this.stddev;
    }                       // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return this.mean() - confidenceIntervalNum();
    }                // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + confidenceIntervalNum();
    }                 // high endpoint of 95% confidence interval

    private double confidenceIntervalNum() {
        return ((1.96*this.stddev())/Math.sqrt(this.trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n,t);

        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

    }
}

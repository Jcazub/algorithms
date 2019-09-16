import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_CONSTANT = 1.96;
    private final int trials;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        // validating input
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        // setting variables
        this.trials = trials;
        double[] percolationRatios = new double[trials];

        // running trials
        for (int i = 0; i < trials; i++)
        {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates())
            {
                percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            percolationRatios[i] = percolation.numberOfOpenSites()/ (double) (n*n);
        }

        // calculating mean
        mean = StdStats.mean(percolationRatios);

        // calculating standard deviation
        stddev = StdStats.stddev(percolationRatios);

    }

    // sample mean of percolation threshold
    public double mean()
    {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean - ((CONFIDENCE_CONSTANT * stddev)/Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean + ((CONFIDENCE_CONSTANT * stddev)/Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args)
    {
        if (args.length != 2) throw new IllegalArgumentException();

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

}

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats_old {

    private final int N;
    private final int TRIALS;
    private final double MEAN;
    private final double STDDEV;

    // perform independent trials on an n-by-n grid
    public PercolationStats_old(int n, int trials)
    {
        // validating input
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        // setting variables
        N = n;
        TRIALS = trials;
        double[] percolationRatios = new double[TRIALS];

        // running trials
        for (int i = 0; i < TRIALS; i++)
        {
            Percolation percolation = new Percolation(n);

            while(!percolation.percolates())
            {
                percolation.open(StdRandom.uniform(N) + 1, StdRandom.uniform(N) + 1);
            }
            percolationRatios[i] = percolation.numberOfOpenSites()/ (double) (N*N);
        }

        // calculating mean
        double meanDividendTotal = 0;

        for (double currentRatio : percolationRatios)
        {
            meanDividendTotal += currentRatio;
        }

        MEAN = meanDividendTotal/TRIALS;

        // calculating standard deviation
        double stdDevDividendTotal = 0;

        for (double currentRatio : percolationRatios)
        {
            stdDevDividendTotal += Math.pow(currentRatio - MEAN, 2);
        }

        STDDEV = Math.sqrt(stdDevDividendTotal/(TRIALS - 1));

    }

    // sample mean of percolation threshold
    public double mean()
    {
        return MEAN;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return STDDEV;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return MEAN - ((1.96 * Math.sqrt(STDDEV))/Math.sqrt(TRIALS));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return MEAN + ((1.96 * Math.sqrt(STDDEV))/Math.sqrt(TRIALS));
    }

    // test client
    public static void main(String[] args)
    {
        if (args.length != 2) throw new IllegalArgumentException();

        PercolationStats_old percolationStatsOld = new PercolationStats_old(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean = " + percolationStatsOld.mean());
        System.out.println("stddev = " + percolationStatsOld.stddev());
        System.out.println("95% confidence interval = [" + percolationStatsOld.confidenceLo() + ", " + percolationStatsOld.confidenceHi() + "]");
    }

}

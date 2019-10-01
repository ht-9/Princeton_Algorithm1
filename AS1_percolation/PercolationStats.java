import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {

    private final double[] probabilities;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        probabilities = new double[trials];
        for(int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while(!p.percolates()) {
                p.open(StdRandom.uniform(1,n+1), StdRandom.uniform(1, n+1));
            }
            double probability = (p.numberOfOpenSites()+0.0) / (n*n);
            probabilities[i] = probability;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(probabilities);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(probabilities);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-1.96*stddev()/Math.sqrt(probabilities.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+1.96*stddev()/Math.sqrt(probabilities.length);
    }

}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
When n is sufficiently large, there is a threshold value p* such that when p < p* 
a random n-by-n grid almost never percolates, 
and when p > p*, a random n-by-n grid almost always percolates.
The threshold value is estimated to be ~ 0.593;
*/

public class PercolationStats {
    int n; // grid size
    int trials; // number of trials
    Percolation p;
    double[] openSiteRatio; // ratio between num of open sites to total number of sites;

    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.openSiteRatio = new double[trials];

        for(int i = 0; i < trials ; i++){
            p = new Percolation(n);
            while(!p.percolates()){
                p.open(StdRandom.uniform(1,p.dimension()+1), StdRandom.uniform(1, p.dimension()+1));
            }
            double numOfOpenSites = p.numOfOpenSites;
            double numOfSites = p.size();
            this.openSiteRatio[i] = numOfOpenSites/numOfSites;
        }
    }

    public double mean(){
        return StdStats.mean(this.openSiteRatio);
    }
    public double stddev(){
        return StdStats.stddev(this.openSiteRatio);
    }

    //Assuming the number of trials is large, will return a 95% confidence interval
    public double confidenceLo(){
        return ( mean() - ( (1.96 * stddev()) / Math.sqrt(this.trials) ) );
    }
    public double confidenceHi(){
        return ( mean() + ((1.96 * stddev()) / Math.sqrt(this.trials)) );
    }
 
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("Mean : " + ps.mean());
        System.out.println("Std deviation: " + ps.stddev());
        System.out.println("95% Confidence Interval: [" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
   }
 }
 
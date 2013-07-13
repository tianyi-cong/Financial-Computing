package class5;

public class GBMRandomPathGenerator implements PathGenerator {
	
	private double rate;
	private double sigma;
	private double S0;
	private int N;
	private RandomVectorGenerator rvg;
	
	public GBMRandomPathGenerator(double rate, int N,
			double sigma, double S0, RandomVectorGenerator rvg){
		this.rate = rate;
		this.S0 = S0;
		this.sigma = sigma;
		this.N = N;
		this.rvg = rvg;
	}

	@Override
	public Path getPath() {
		double[] n = rvg.getVector();
		final double[] prices = new double[N+1];
		prices[0]=S0;
		for ( int i=1; i < N; ++i){
			prices[i] = prices[i-1]*Math.exp((rate-sigma*sigma/2)+sigma * n[i-1]);
		}
		return new Path() {
			
			@Override
			public double[] getPrices() {
				return prices;
			}
		};
	}
	

	

}

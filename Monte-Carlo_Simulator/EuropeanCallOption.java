package class5;

public class EuropeanCallOption implements PayOut {
	
	private double K;
	
	public EuropeanCallOption(double K){
		this.K = K;
	}

	@Override
	public double getPayout(Path path) {
		double[] prices = path.getPrices();
		return Math.max(0, prices[prices.length-1] - K);
	}

}

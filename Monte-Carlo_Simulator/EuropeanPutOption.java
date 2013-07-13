package class5;

public class EuropeanPutOption implements PayOut {
	
	private double K;
	
	public EuropeanPutOption(double K){
		this.K = K;
	}

	@Override
	public double getPayout(Path path) {
		double[] prices = path.getPrices();
		return Math.max(0, K - prices[prices.length-1]);
	}

}
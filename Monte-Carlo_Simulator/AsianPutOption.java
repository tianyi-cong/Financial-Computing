package class5;

public class AsianPutOption implements PayOut {

	private double K;
	
	public AsianPutOption(double K){
		this.K = K;
	}

	@Override
	public double getPayout(Path path) {
		int total=0;
		
		double[] prices = path.getPrices();
		for(int x=1;x<12;x++){
		total += prices[x];	
		}
		int avgPrice = total/12;
		return avgPrice;
	}
	
}

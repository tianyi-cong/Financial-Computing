import java.util.*;

public class Bond {
	private double coupon;
	private double faceValue;
	private double maturity;
	private double price;
	private double paymentFrequency;
	private Map<Double, Double> cashFlow;

	public Bond (double coupon, double faceValue, double maturity, double price, double paymentFrequency){
		this.coupon = coupon;
		this.faceValue = faceValue;
		this.maturity = maturity;
		this.price = price;
		this.paymentFrequency = paymentFrequency;
		

		cashFlow = new HashMap<Double, Double>();
		if (paymentFrequency == 0.0){
			cashFlow.put(maturity, faceValue);
		}
		else{
			for(int i=1; i<maturity/paymentFrequency; i++){
				cashFlow.put(i * paymentFrequency, coupon * faceValue);
				cashFlow.put(maturity, coupon * faceValue + faceValue);
			}
		}
	}
	
	public double getPrice(){
		return price;
	}
	
	public double getCoupon(){
		return coupon;
	}
	
	public double getMaturity(){
		return maturity;
	}
	
	public double getFaceValue(){
		return faceValue;
	}
	
	public double getPaymentFrequency(){
		return paymentFrequency;
	}
	
	public Map<Double,Double> getCashFlow(){
		return cashFlow;
	}
}
import java.util.*;
import java.text.DecimalFormat;

public class YieldCurve {
	
	private Map<Double,Double> yieldCurve = new TreeMap<Double,Double>();
	
	public YieldCurve(List<Bond> bonds){
		Iterator<Bond> iter = bonds.iterator();
		while(iter.hasNext()) {
			Bond bond = iter.next();
			double coupon = bond.getCoupon();
			double faceValue = bond.getFaceValue();
			double time = bond.getMaturity();
			double price = bond.getPrice(); 
			double interestRate = Math.log((faceValue + coupon)/price)/time; 	// faceValue = (price + coupon)*Math.pow(Math.E, interestRate*maturity)
			yieldCurve.put(time, interestRate);	
		}	
	}
	
	public YieldCurve(Map<Double,Double> map){
		this.yieldCurve = map;
	}
	
	// Return the interest rate for each year of the given time period. 
	public double getInterestRate(double time){
		if(yieldCurve.containsKey(time)){
			return yieldCurve.get(time);
			}
		else{
			// When time not in the map, find two time which are closest to the time but one is smaller than time, the other is greater than time.
			// Then use linear interpolation to calculate the interest rate of that time.
			Collection<Double> collection = yieldCurve.keySet();	// Time Set
			Iterator<Double> iter = collection.iterator();
			double smallerTime = 0, greaterTime = 0, smallerRate = 0, greaterRate = 0;
			while(iter.hasNext()){ 
				double t1 = iter.next();
				if(t1 < time) {
					smallerTime = t1;
					smallerRate = yieldCurve.get(t1);
				}
				else {
					greaterTime = t1;
					greaterRate = yieldCurve.get(t1);
					break; 
				}	
			}
		    return (greaterRate- smallerRate) / (greaterTime - smallerTime) * (time - smallerTime) + smallerRate;	// linear interpolation
		}
	}
	
	// Return the forward rate between two dates
	public double getForwardRate(double t0, double t1){
		if (t0 >= t1){
			System.out.println("first date should be smaller than the second date.");
			return -1;
		}
		else{
			double rt0 = getInterestRate(t0);
			double rt1 = getInterestRate(t1);
			return rt1 * t1 / (rt0 * t0 * (t1 - t0));
		}		
	}
	
	// Return the discount factor of the time
	public double getDiscountFactor(double t){
		double rate = getInterestRate(t);
		return Math.pow(Math.E, rate * t); 
	}
	
	// Output format
	public String toString() {
		Collection<Double> collection = yieldCurve.keySet();
		Iterator<Double> iter = collection.iterator();
		DecimalFormat df = new DecimalFormat("0.00");
		while(iter.hasNext()) {
			Double key = iter.next();
			System.out.print("Rate at " + key + " year" + " is: " + df.format(yieldCurve.get(key)*100) + "%\n");
		}
		return "";
	}
}

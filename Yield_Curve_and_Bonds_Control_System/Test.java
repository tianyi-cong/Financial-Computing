import java.util.*;
import java.text.DecimalFormat;

/**
 * @ author Tianyi Cong
 */

public class Test {

	public static void main(String[] args) {
		// Instantiate the yield curve and print it out.
		Map<Double,Double> map = new TreeMap<Double,Double>();
		map.put((double)1, 0.02);
		map.put((double)2, 0.023);
		map.put((double)3, 0.03);
		YieldCurve yc = new YieldCurve(map);
		System.out.println(yc);
		
		// Use two new bonds to instantiate a yield curve and print it out.
		Bond bond1 = new Bond(0, 100, 0.5, 95, 0);
		Bond bond2 = new Bond(0, 1000, 1, 895, 0);		
		List<Bond> bonds = new ArrayList<Bond>();
		bonds.add(bond1);
		bonds.add(bond2);
		YieldCurve yc1 = new YieldCurve(bonds);
		System.out.println(yc1);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		// Calculate interest rate at time 0.75
	    double rate = yc1.getInterestRate(0.75); 
	    System.out.println("Rate at 0.75 year is : " + df.format(rate * 100) + "%\n");
		
		// Use the yield curve to price a 5% coupon bond with 500$ face value, which pays semi-annually. Print the price and YTM.
	    YTM ytm = new YTM();
	    Bond bond3 = new Bond(0.05, 500, 3, 0, 0.5);
	    double price = ytm.getPrice(yc, bond3);
	    System.out.println("Price of the bond is : " + df.format(price) + "\n");
	    
	    double ytmValue = ytm.getYTM(bond3, price);
	    System.out.println("The YTM is : " + df.format(ytmValue * 100) + "%\n");			    
	}

}

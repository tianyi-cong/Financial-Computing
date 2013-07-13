public class YTM{
	// Calculate the bond's fair price given a yield curve object
	public double getPrice(YieldCurve yc, Bond b){
		double coupon = b.getCoupon();
		double faceValue = b.getFaceValue();
		double maturity = b.getMaturity();	
		double price = b.getPrice();
		double paymentFrequency = b.getPaymentFrequency();
	    double couponTime = maturity/paymentFrequency;
	    double C = coupon * faceValue;
		double value = 0;
		double rate = 0;
		double sum = 0;
		
	    for(int i=1; i<=couponTime; i++){
	    	if(i < couponTime){
	    		rate = yc.getInterestRate(paymentFrequency*i);
	    		value = C / Math.pow(Math.E, rate * paymentFrequency * i);
	    		sum = sum + value;
	    		//System.out.println(r);
	    	}
	    	else{
	    		rate = yc.getInterestRate(paymentFrequency*i);
	    		double total = C + faceValue;
	    		//System.out.println(total);
	    		value = total / Math.pow(Math.E, rate * paymentFrequency * i);
	    		sum = sum + value;
	    	}
	    }
	    return sum;
	}
	
	// Returns the yield-to-maturity of the bond for a particular price.
	public double getYTM(Bond b,double price){
		double coupon = b.getCoupon();
		double faceValue = b.getFaceValue();
		double maturity = b.getMaturity();		
		double p = b.getPrice();
		double paymentFrequency = b.getPaymentFrequency();
	    double times = maturity/paymentFrequency;	    
	    double C = coupon * faceValue;	   
	    double r1 = 0;
	    double r2 = 1;
	    double rate = (r1 + r2)/2;
	    double estimatedPrice =0;
	    
	    for(int i=1; i<=times;i++){
	    	if(i<times){
	    		estimatedPrice = C / Math.pow(Math.E, rate * paymentFrequency * i) + estimatedPrice;
	    	}
	    	else{
	    		estimatedPrice = (C + faceValue) / Math.pow(Math.E, rate * paymentFrequency * i) + estimatedPrice;
	    	}
	    }
	   
	    // Calculate the rate until it is under the tolerance.
	    while(Math.abs(estimatedPrice - price) > 0.001){
		   if(estimatedPrice - price > 0){
			   r1 = rate;
			   rate = (r1 + r2)/2;
		   }
		   else{
			   r2 = rate;
			   rate = (r1 + r2)/2;
		   }
		   
		   estimatedPrice = 0;
		   for(int i=1; i<=times;i++){
		    	if(i<times){
		    		estimatedPrice = C / Math.pow(Math.E, rate * paymentFrequency * i) + estimatedPrice;
		    	}
		    	else{
		    		estimatedPrice = (C + faceValue) / Math.pow(Math.E, rate * paymentFrequency * i) + estimatedPrice;
		    	}
		    }
	   }
	   return rate;   
	}
	
	// Returns the bond's fair price given the yield to maturity.
	public double getPrice(Bond bound, double ytm){
		double coupon = bound.getCoupon();
		double faceValue = bound.getFaceValue();
		double maturity = bound.getMaturity();
		double paymentFrequency = bound.getPaymentFrequency();
	    double times = maturity/paymentFrequency;
	    double C = coupon * faceValue;
	    double fairPrice = 0;
	    
	    for(int i=1; i<=times; i++){
	    	if(i < times){
	    		fairPrice = C / Math.pow(Math.E, ytm * paymentFrequency * i) + fairPrice;
	    	}
	    	else{
	    		fairPrice = (C + faceValue) / Math.pow( Math.E, ytm * paymentFrequency * i) + fairPrice;
	    	}
	    }
	    return fairPrice;
	}
}

package class5;
import java.util.ArrayList;
import java.util.Map;

import class5.*;
public class Run {


	public static void main(String[] args) {
		Run run = new Run();
		int stockPrice = 100;
		double volatility = 0.1;
		double sigma = volatility;
		double rate = 0.01; 
		double variance = sigma*sigma;
		
		// run European Call Option
		int total=0;
		for(int x=0;x<1000;x++){
			int N=1;
			while(!(sigma*3/N < 0.01)){
				run.EuropeanCallOption(1);
				N++;
			}
				total+=N;
		}
		System.out.println("Average time for is European Call Option" + total/1000);
		
		// run European Put Option
				int total2=0;
				for(int x=0;x<1000;x++){
					int N=1;
					while(!(sigma*3/N < 0.01)){
						run.EuropeanPutOption(1);
						N++;
					}
						total2+=N;
				}
				System.out.println("Average time for is European Put Option" + total/1000);
				
				// run Asian Put Option
				int total3 =0;
				for(int x=0;x<1000;x++){
					int N=1;
					while(!(sigma*3/N < 0.01)){
						run.AsianPutOption(12);
						N++;
					}
						total3+=N;
				}
				System.out.println("Average time for is Asian Call Option" + total2/1000);
		
		// run Asian Call Option
		int total4 =0;
		for(int x=0;x<1000;x++){
			int N=1;
			while(!(sigma*3/N < 0.01)){
				run.AsianCallOption(12);
				N++;
			}
				total4+=N;
		}
		System.out.println("Average time for is Asian Call Option" + total2/1000);
		
	}

}

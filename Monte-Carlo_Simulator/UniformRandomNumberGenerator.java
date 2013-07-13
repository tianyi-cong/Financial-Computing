package class5;

public class UniformRandomNumberGenerator implements RandomVectorGenerator{
	
	private int N;
	
	public UniformRandomNumberGenerator(int N){
		this.N = N;
	}

	@Override
	public double[] getVector() {
		double[] vector = new double[N];
		for ( int i = 0; i < vector.length; ++i){
			vector[i] = Math.random() - 0.5;
		}
		return vector;
	}

}

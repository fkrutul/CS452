public class Grid {

	// Standard function to compute binomial coefficients - this just implements the usual formula
	public static long binomial(long n, long k) {
		long soln = 1L;
		if(n == k) {
			k = n - k;
		}
		for(long i = 0; i < k; i++) {
			soln = (soln * (n - i))/(i + 1);
		}

		return soln;
	}

	// Driver program to compute the required value
	public static void main(String[] args) {
		long result = binomial(38, 19); // 38 tiles in total in the grid, travelling right 19 tiles to reach bottom of grid
		System.out.println("Result: " + result);
	}
}

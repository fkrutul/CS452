import java.math.*; 
  
public class q10 { 

    // Standard iterative fibonacci implementation with adjusted base cases
    public static BigInteger newFib(int n) { 
        BigInteger fib1 = new BigInteger("5"); // First fib term
        BigInteger fib2 = new BigInteger("6"); // Second fib term
        for (int i=3 ; i<=n ; i++) { // Compute until desired term
            BigInteger fib3 =  fib1.add(fib2); // Third term is the sum of the first two
            fib1 = fib2; // Reassign fib2 to fib1 to continue computation iteratively
            fib2 = fib3; //Reassign fib3 to fib2 to continue computation iteratively
        } 
  
        return fib1; // Return nth fibonacci number
    } 
  	
  	// Driver method to test code with given condition
    public static void main(String[] args) { 
        BigInteger sum = new BigInteger("0"); // Initialize running total of even fib terms
		BigInteger num = new BigInteger("40000000000");
		BigInteger zero = new BigInteger("0");
		for(int i = 1; i<=55; i++) { //Iterate through first 55 terms since given limit is reached well before then
			int comparison = newFib(i).compareTo(num); // Check if limit has been surpassed
			if(comparison == -1 || comparison == 0){
				BigInteger modulus = newFib(i).mod(new BigInteger("2")); // If it hasn't been surpassed, calculate fib modulo 2
				boolean equals = modulus.equals(zero); 
				if(equals == true) {
				sum = sum.add(newFib(i)); // If the modulus is zero add the term to the sum total
				}
			}
		}
		System.out.println("The desired sum is " + sum); // Print out the desired sum
    } 
} 

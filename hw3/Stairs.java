import java.math.*;
public class Stairs {

	// Computes the number of ways to climb n stairs, taking 1 or 2 steps at a time
    public static BigInteger climbStairs(int n) {

        BigInteger[] ways = new BigInteger[n + 1]; // Create an array to store the number of ways 
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        ways[1] = one; // One way to climb a single stair
        ways[2] = two; // Two ways to climb two stairs
        int i = 3; // To be used in while loop
        while (i <= n) { // Loop through all values until target is reached 
            ways[i] = ways[i - 1].add(ways[i - 2]); // Recursive relation for the amount of combinations for each subsequent stair count
            i++; // Increment iteration index
        }
        return ways[n]; // Return the number of ways to climb n stairs
    }

    // Driver program to test the above code with required value
    public static void main(String[] args) {
    	BigInteger result = climbStairs(2000);
    	System.out.println("The result is: " + result);
    }
}

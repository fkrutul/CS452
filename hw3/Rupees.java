import java.util.Arrays;
import java.util.ArrayList;

public class Rupees {

	// Return the number of ways to make n Rupees
	public static int makeRupees(int n) {
		int[] denoms = {5,10,25,50,100,200,500,1000}; // Initialize array of denominations
		int[] ways = new int[n + 1]; // Create new array to store combinations
		ways[0] = 1; // Only one possible combination with just 5s
		int i = 0; // To be used in while loop
		int numOfDenoms = 7; // Number of values in the denomination array
		while(i < numOfDenoms) { // Traverse array of denominations
			int j = denoms[i]; // To be used in while loop
			while (j <= n) { // For each denomination, loop until target value is reached
				ways[j] = ways[j] + ways[j - denoms[i]];	// Increment the number of ways to reach the desired amount and exlude the current coin value   
				j++; // Increment iteration index 
			}
			i++; // Increment iteration index
		}
		return ways[n]; // Return the number of ways to make n Rupees
	}

	// Driver program to test the above code with required value
	public static void main(String[] args) {
		int result = makeRupees(700);
		System.out.println("The result is: " + result);
	}
} 

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BruteInversions {

	// Standard function to read in data file into integer array
	private static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<Integer> List = new ArrayList<Integer>();
		while (scan.hasNextInt()){
            int i = scan.nextInt();
            List.add(i); 
        }
		scan.close();
		return List;
	}	

	// Brute force method to count inversions
	public static int countInversions(int[] array) {
		int counter = 0; // Initialize a counter
		for (int i = 0; i < array.length - 1; i++) { // Loop through all values in array 
			for (int j = i + 1; j < array.length; j++) { // For each value, loop through all other values 
				if (array[i] > array[j]) { // Compare the current value to all other values
				counter = counter + 1; // Update inversion counter if a swap is needed
				}
			}
		}
		return counter; // Return number of inversions
	}

	// Driver program to test above code with given data
	public static void main(String[] args) throws FileNotFoundException {
		int[] size = {10,50,100,150,200,250,300,350,400,450,500,1000,5000,10000};
		for(int j=0;j<14;j++){
			System.out.print("Data File: " + Integer.toString(size[j]));
			System.out.println("");
			ArrayList<Integer> List = readArray(Integer.toString(size[j]));
			int[] Array = List.stream().mapToInt(i -> i).toArray();
			long start = System.nanoTime();
			int result = countInversions(Array);
			long exec = System.nanoTime() - start;
			System.out.println("Number of inversions: " + result);
			System.out.println("Time " + exec);
			System.out.println("");
		}	
	}
}

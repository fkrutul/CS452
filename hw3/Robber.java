import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;

public class Robber {

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

	// Method to compute the maximum amount of loot
	public static int maxRob(int[] data) {	
		int max = 0; // Initalize maximum variable for greedy approach
 		int length = data.length; // Store length of data array in a variable
    		int[] sums = new int[length]; // Initialize array to store the cumulative loot um
    		sums[0] = data[0]; // Known value, no movements made yet
    		if(data[0] < data[1]) { // The following 5 lines select the maximum of the two values
    			max = data[1];
    		}
    		else {
    			max = data[0];
    		}
    		sums[1] = max; // Dynamic approach: pick maximum value to advance with maximized loot
 
    		for(int i = 2; i < length; i++){ // Loop through remainder of data array
    			if(sums[i - 2] + data[i] < sums[i - 1]) { // The following 5 lines select the maximum of the two values
    				max = sums[i - 1];
    		}

    		else {
    			max = sums[i - 2] + data[i];
    		}
        	sums[i] = max; // Iteratively pick the maximum-output choice out of each avaliable possibility (either the previous value as it is, or the previous value with the current amount substituted in)
    	}
 
    	return sums[length-1]; // Return final value in sums array to obtain the maximized cumulative sum
	}

	// Driver program to test the above code with given data
	public static void main(String[] args) throws FileNotFoundException {
			ArrayList<Integer> List = readArray("money.txt");
			int[] Array = List.stream().mapToInt(i -> i).toArray();
			int result = maxRob(Array);
			System.out.println("Max loot: " + result);
	}
}

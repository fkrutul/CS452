import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DCInversions {

	// Standard method to read file into an integer array
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

	// Merge method of merge sort, modified to count inversions
	private static int merge(int[] array, int[] temp, int start, int middle, int end) {
        int counter = 0; // Initialize insertion counter
        int i = start; // Initialize loop variable
        int j = middle + 1; // Initialize mid value

        while (i <= end) { // Traverse the array
            temp[i] = array[i]; // Copy values from passed in array into helper array
            i++; // Increment iteration counter
        }

        int k = start; // Initialize loop variable
        while (k <= end) { // Traverse the array
            if (start > middle) { // Compare the first and middle indices in array
            	array[k] = temp[j++]; // If the first index is greater, assign first index as the incremented middle value in the helper array
            }         
            else if (j > end) { // Compare the mid index with the last index in array
            	array[k] = temp[start++]; // If the mid index is greater, assign the first index as the incremented first value in the helper array
            }           
            else if (temp[j] < temp[start]){ // Compare values at middle + 1 and at start of the helper array
            	array[k] = temp[j++]; // If the value at the start of the helper array is greater, swap the next value with the start
            	counter = counter + (middle - start + 1); // An inversion has occured, so increment the counter by number of inversions (mid - start + 1)
         	}
            else {
            	array[k] = temp[start++]; // Otherwise, assign first value as incremented first value in helper array
	
            } 

            k++; // Increment iteration counter                
        }

        return counter; // Return the number of inversions
    }


	// Sorts an array by repeatedly halving it, sorting its elements, and merging the sub-arrays together
	public static int countInversions(int[] array, int[] temp, int start, int end) {
		int result = 0; // Initalize inversion counter
		if (start < end) { // Compare first and last value in array
			int middle = start + (end - start)/2; // Initialize a middle element
			result = countInversions(array, temp, start, middle); // Increment counter with inversions found in sorting the left sub-array
			result = result + countInversions(array, temp, middle+1, end); // Increment counter with inversions found in sorting the right sub-array
			result = result + merge(array, temp, start, middle, end); // Increment counter with inversions found in the merge step
		}

		return result; // Return the total number of inversions
	}

	// Driver program to test the above code with given data
	public static void main(String[] args) throws FileNotFoundException {
		int[] size = {10,50,100,150,200,250,300,350,400,450,500,1000,5000,10000};
		for(int j=0;j<14;j++){
			System.out.print("Data File: " + Integer.toString(size[j]));
			System.out.println("");
			ArrayList<Integer> List = readArray(Integer.toString(size[j]));
			int[] Array = List.stream().mapToInt(i -> i).toArray();
			int [] temp = Array.clone();
			long start = System.nanoTime();
			int result = countInversions(Array,temp,0,Array.length-1);
			long exec = System.nanoTime() - start;
			System.out.println("Number of inversions: " + result);
			System.out.println("Time " + exec);
			System.out.println("");
		}
    }
}

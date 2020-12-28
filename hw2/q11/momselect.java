import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class momselect {

	// Standard method to read data file into integer array
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

	// Standard method to swap two elements
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Quicksort partitioning method
	private static int partition(int[] array, int start, int end) {
		int pivot = array[end]; // Pick pivot
		int i = start - 1; // Store lowest value in variable
		for (int j = start; j < end; j++) { // Traverse the array 
			if (array[j] <= pivot) {
				i++;
				exchange(array,i,j); // Swap lowest element with element at current index if the value is lower than the pivot
			}
		}
		exchange(array, i+1, end); // Swap i +1th element with last element
		return i + 1; // Return i+1th element
	}

	// Ith order statistic using median of medians partitioning
    public static int momselect(int[] array, int start, int end, int key){

        if (end - start < 5){
            Arrays.sort(array, start, end + 1); // Sort small array
            return start + key - 1; // Return kth position
        } 

        else {
            int index = start; // Initialize an index position
            for (int i = start ; i < end ; i = i + 5){ // Traverse array in chunks of 5
                if (i + 5 < end) {
                    Arrays.sort(array, i, i + 5); // Sort 5-element subarrays
                    exchange(array, index, i + 2); // Swap index with floor of the median of the subarray
                }
                else {
                    Arrays.sort(array, i, end); // Sort last n<5 element subarray
                    exchange(array, index, (i + end)/2); // Swap index with median of the subarray
                }
                index++; // Increment the index
            }

            int medianOfMedians = momselect(array, start, index - 1, (index - start + 1) / 2); // Recursively call the algorithm on the part of the array containing the medians of subarrays
            exchange(array, medianOfMedians, end); // Swap the median of medians with the last element
            int pivot = partition(array, start, end); // Partition around median of medians
            int pos = pivot - start + 1; // Store position of partition in variable
            return recursiveCalls(array, start, end, key, pos, pivot); // Call the recursive part of the algorithm (separate method)
        }
    }

    // Method containing recursive calls of above algorithm for better compartmentalization 
    public static int recursiveCalls(int[] array, int start, int end, int key, int pos, int pivot) {
    	
    	if(key == pos) { 
    		return pivot; // If the key is at the partition position, return the pivot index
    	}

    	else if(key < pos) {
    		return momselect(array, start, pivot - 1, key); // If the key is less that the partition position, recurse on lower subarray
    	}

    	else {
            return momselect(array, pivot + 1, end, key - pos); // Otherwise, recurse on upper subarray
        }
    }

    // Driver program to test the code with provided data
	public static void main(String[] args) throws FileNotFoundException  {
		int[] size = {10000,20000,30000,40000,50000};
		for(int j=0;j<5;j++){
			System.out.print("Data File: " + Integer.toString(size[j]));
			System.out.println("");
			ArrayList<Integer> List = readArray(Integer.toString(size[j]));
			int[] Array = List.stream().mapToInt(i -> i).toArray();
			int length = Array.length;
			float median;
        	long start = System.nanoTime();
			if (length%2==0) {
				int temp1 = length/2;
				int temp2 = length/2+1;
				int a = Array[momselect(Array, 0, length -1, temp1)];
				int b = Array[momselect(Array, 0 , length -1, temp2)];
				median = (float)(a+b)/2;
			}

			else {
				median = Array[momselect(Array, 0, length-1, length/2 + 1)];
			}

        	long exec = System.nanoTime() - start;
        	System.out.println("The median is: " + median);
        	System.out.println("Executon time: " + exec + " nanoseconds");
        	System.out.println("");
		} 
	} 
}

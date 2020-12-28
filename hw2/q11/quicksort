mport java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class quicksort {

	// Standard method to read integers from text file into array
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

	// Standard function to swap two elements in an array
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

	// Quick sort algorithm
	public static void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int q = partition(array,start,end); // Partition the array
			quickSort(array,start,q-1); // Recurse on lower subarray
			quickSort(array,q+1,end); // Recurse on upper subarray
		}
	}

	// Standard method to find median of array. Accounts for even and odd lengths
	public static void findMedian(int[] array) {
		quickSort(array,0,array.length-1); // Sort the array with quick sort
		float median;
		int length = array.length;
		if (length % 2 == 0) {
			median = (float)(array[length/2] + array[length/2 -1])/2;
			System.out.println("The median is: " + median);
		}

		else {
			median = array[array.length/2];
			System.out.println("The median is: " + median);
		}
	}

	// Driver program to test the code with given data 
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Small test: ");
		int[] tinytest = {1,2,3,4,5,6};
		findMedian(tinytest);
		System.out.println("");

		int[] size = {10000,20000,30000,40000,50000};
		for(int j=0;j<5;j++){
			System.out.print("Data File: " + Integer.toString(size[j]));
			System.out.println("");
			ArrayList<Integer> List = readArray(Integer.toString(size[j]));
			int[] Array = List.stream().mapToInt(i -> i).toArray();
			long start = System.nanoTime();
			findMedian(Array);
			long exec = System.nanoTime() - start;
			System.out.println("Execution time: " + exec + " nanoseconds");
			System.out.println("");
		}
	}
}

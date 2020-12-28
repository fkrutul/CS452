import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class randompart {

	// Standard method to read integers from text file into array
	private static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<Integer> List = new ArrayList<Integer>();
		while (scan.hasNextInt()){
            int i = scan.nextInt();
            List.add(i); 
        }
		scan.close();
		return List;
	}	

	private static Random random = new Random(); // Initialize random number generator
	 
	// Randomized ith order statistic algorithm
	public static int randomizedSelect(int[] array, int start, int end, int key) {

			if(start == end){
				return array[start]; // If first and last elements are equal, return first element 
			}
			
			int part = randomizedPartition(array, start, end);  // Randomly partition the array
			int pos = part-start+1; // Store position of partition in variable

			if(key == pos){
				return array[part]; // If the key is at the position, return value of partition
			}
			else if(key < pos){
				return randomizedSelect(array,start,part-1,key); // Recurse on lower subarray
				
			}
			else{
				return randomizedSelect(array,part+1,end,key-pos); // Recurse on upper subarray
			}
  	}
	
	// Randomized Partition 
    private static int randomizedPartition(int[] array, int start, int end) {		
		int piv = start + random.nextInt(end-start); // Pick random element in the array as pivot
		exchange(array, end, piv); // Swap pivot with last element
		return partition(array, start, end);  // Call partition method
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

	// Standard function to swap two elements in an array
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Driver program to test the above methods 
    public static void main(String args[]) throws FileNotFoundException { 
		
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
				int a = randomizedSelect(Array, 0, length-1, temp1);
				int b = randomizedSelect(Array,0, length -1, temp2);
				median = (float)(a+b)/2;
			}

			else {
				median = randomizedSelect(Array, 0, length-1, length/2 + 1);
			}

        	long exec = System.nanoTime() - start;
        	System.out.println("The median is: " + median);
        	System.out.println("Executon time: " + exec + " nanoseconds");
        	System.out.println("");
		} 
	} 
}

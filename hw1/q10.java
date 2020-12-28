import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class q10{

	// Standard method to read a file into an integer array
	public static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<Integer> Alist = new ArrayList<Integer>();
		while (scan.hasNextInt()){ 
			int i = scan.nextInt();
			Alist.add(i); 
		}
		scan.close();
		return Alist;

	}
	// Checks the array for key by halving each successive interval
	public static int binarySearch(int[] array,int key) {
    	int start = 0; // Define the lower bound of the array as 0
    	int end = array.length - 1; // Define the upper bound of the array as length - 1 
  	  	int middle; // Initialize a variable for the middle element (to be used later)
    	while (start <= end) { // While the lower bound of the array is smaller or equal to the upper bound
      		middle = (start + end) / 2; // Compute the middle element of the array (standard median calculation)
      		if (array[middle] < key) { // If the value of the middle element is smaller than the key, we search the upper sub-array
        		start = middle + 1;
      		}
      		else if (array[middle] > key) { // If the value of the middle element is larger than the key, we search the lower sub-array
        		end = middle - 1;
      		}
      		else if (array[middle] == key) { // If the middle element is the key, return it 
       			return middle;
      		}    
    	}
  		return -1; // If we get to this point it means that the key was not found
  }

  // Checks the array for key by dividing each successive interval into thirds
  static int ternarySearch(int array[], int key) 
  
    { 
    	int start = 0; // Define the lower bound of the array as 0
    	int end = array.length - 1; // Define the upper bound of the array as length - 1
        while (end >= start) { // While the lower bound of the array is smaller or equal to the upper bound
            int firstMid = start + (end - start) / 3; // First middle bounds the first third of the array
            int secondMid = end - (end - start) / 3; // Second middle bounds the last third of the array
            if (array[firstMid] == key) { // If the first middle is our key, return it
                return firstMid; 
            } 
            else if (array[secondMid] == key) { // If the second middle is our key, return it
                return secondMid; 
            } 
            else if (key < array[firstMid]) { // If the key is smaller than first middle element, search the lowest third
                end = firstMid - 1; 
            } 
            else if (key > array[secondMid]) { // If the key is larger than the first middle element, search the last third
                start = secondMid + 1; 
            } 
            else { // Else, search the middle third
                start = firstMid + 1; 
                end = secondMid - 1; 
            } 
        } 
        return -1; // If we get to this point, the key was not found
    } 

    // Standard driver program. Time computations are done using nanoTime()
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Integer> data = readArray("data.txt");
		int[] intData = data.stream().mapToInt(i -> i).toArray();
		int findCounter = 0;
		long startTime1 = System.nanoTime();
		for (int i = 2; i <= 100000;i++) {
			if (i % 2 == 0) {
				int index = binarySearch(intData,i);
				if (index != -1) {
					findCounter++;
					// System.out.println(i + " was found!: " + "at " +  index + "th position");
				}
			}
		}
		long endTime1 = System.nanoTime();
		long binaryTime = endTime1 - startTime1;
		System.out.println(findCounter + " numbers found with binary search!");
		System.out.println("Aggregate binary search time: " + binaryTime + " nanoseconds");

		int findCounterT = 0;
		long startTime2 = System.nanoTime();
		for (int i = 2; i <= 100000;i++) {
			if (i % 2 == 0) {
				int indexT = binarySearch(intData,i);
				if (indexT != -1) {
					findCounterT++;
					// System.out.println(i + " was found!: " + "at " +  indexT + "th position");
				}
			}
		}
		long endTime2 = System.nanoTime();
		long ternaryTime = endTime2 - startTime2;
		System.out.println(findCounterT + " numbers found with ternary search!");
		System.out.println("Aggregate ternary search time: " + ternaryTime + " nanoseconds");
	}	
}

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Triangles {

	// Standard function to read in data file into 2-D integer array
	private static int[][] readTriangle(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("triangles.txt"));
		int[][] triangle = new int[100][100];
		int rows = 0; 
		int cols = 0;  
		while(scan.hasNextLine() == true) {
			String line = scan.nextLine(); 
			String [] values = line.split(" "); 
			int length = values.length;
			int i = 0;
			while(i < length)
			{
				triangle[rows][cols] = Integer.parseInt(values[i]);
				cols = cols + 1; 
				i++;
			}
			cols = 0; 
			rows = rows + 1; 
		}
		return triangle;
	}

	// Calculate maximum path through the triangle
	public static int findMaxPath(int[][] triangle) {
		int rownum = 98; // Rows to be traversed
		while (rownum >= 0) { // Loop through the rows
			int max = 0; // Initialize maximum variable for greedy approach
			int colnum = 0; // Initialize column number variable
			int nextRow = rownum + 1; // Initialize variable for subsequent row
			while (colnum < nextRow) { // Loop through the triangle
				if (triangle[nextRow][colnum] < triangle[nextRow][colnum + 1]) { // The next five lines pick the maximum between the current column value and next column values
					max = triangle[nextRow][colnum + 1];
				}
				else {
					max = triangle[nextRow][colnum];
				}
				triangle[rownum][colnum] = triangle[rownum][colnum] + max; // Greedy approach: append the maximum value from adjacent numbers to traversal sum
				colnum = colnum + 1; // Increment the column
			}
		rownum = rownum -1; // Decrement the row
		}

		return triangle[0][0]; //Return the max sum of traversal
	}

	// Driver program to test above code with given data
	public static void main(String[] args) throws FileNotFoundException {
		int[][] triangle = readTriangle("triangles.txt");
		int result = findMaxPath(triangle);
		System.out.println("The result is: " + result);
	}

}

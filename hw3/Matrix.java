import java.util.Scanner;
import java.io.*;

public class Matrix {
	
	// Standard function to parse list of comma separated values into a 2-D array (matrix)
	public static int[][] readMatrix(String filepath) throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(new File("matrix.txt"));
		int[][] matrix = new int[80][80];
		int rows = 0; 
		int cols = 0;  
		while(scan.hasNextLine() == true) {
			String line = scan.nextLine(); 
			String [] values = line.split(","); 
			int length = values.length;
			int i = 0;
			while(i < length)
			{
				matrix[rows][cols] = Integer.parseInt(values[i]);
				cols = cols + 1; 
				i++;
			}
			cols = 0; 
			rows = rows + 1; 
		}
		return matrix;
	}			

	// Calculate minimal sum path through the matrix
	public static int minPath(int[][] matrix) {
		int min = 0; // Initialize minimum variable for greedy approach
		int rangeRow = 79; // Rows to be traversed
		while (rangeRow >= 0) { // Traverse the range of the matrix row-wise
			int rangeCol = 79; // Columns to be traversed
		    while (rangeCol >= 0) { // Traverse the range of the matrix column-wise
		    	if (rangeRow < 79 && rangeCol < 79) { // Ensure that we are not at the edges of the matrix
		    		int nextRow = matrix[rangeRow + 1][rangeCol]; // Next row variable
		    		int nextCol = matrix[rangeRow][rangeCol + 1]; // Next column variable
		    		if (nextRow < nextCol) { // The next 5 lines pick the minimum value 
		    			min = nextRow;
		    		}

		    		else {
		    			min = nextCol;
		    		}
		            matrix[rangeRow][rangeCol] = matrix[rangeRow][rangeCol] + min; // Dynamic approach: append the minimum value that can be reached
		        } 
		        else if (rangeCol < 79) { // Row-edge of matrix
		        	int nextCol = matrix[rangeRow][rangeCol + 1]; // Next column variable
		        	matrix[rangeRow][rangeCol] = matrix[rangeRow][rangeCol] + nextCol; // Append value of next column, since no more rows are avaliable to move to
		        }
		        else if (rangeRow < 79) { // Column-edge of matrix 
		        	int nextRow = matrix[rangeRow + 1][rangeCol]; // Next row variable
		            matrix[rangeRow][rangeCol] = matrix[rangeRow][rangeCol] + nextRow; // Append value of next row, since no more columns are avaliable to move to 
		        } 
		        rangeCol = rangeCol - 1; // Decrement columns
		    }
		    rangeRow = rangeRow - 1; // Decrement rows
		}		 
		return matrix[0][0]; // Return the minimum path sum
	}

	// Driver program to test the code with given data
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int[][] matrix = readMatrix("matrix.txt");
		int result = minPath(matrix);
		System.out.println("The result is: " + result);
	}
}

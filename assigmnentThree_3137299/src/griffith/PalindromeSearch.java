/**
 * Point.java
 *
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PalindromeSearch {
	
	// Declaring variables 
	private final static int ROWS = 10;
	private final static int COLUMNS = 10;
	
	// Creating a  matrix of Rows X Columns 
	public static char[][] matrix = new char[ROWS][COLUMNS];
	
	// Method to generate the array with random chars 
	public static char[][] matrixGenerator() {
		Random random = new Random();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j< COLUMNS; j++) {
				// Generates a random number from 0 - 26 on which when added to 'a' gets me letters from a-z
				matrix[i][j] = (char) ('a' + random.nextInt(26));
			}
		}
		// Calls the print Matrix to show the created matrix
		printMatrix();
		return matrix;
	}
	// Method that prints the array ( Used to check array creation and chars ) 	
	public static void printMatrix() {
		 for (int i = 0; i < ROWS; i++) {
	            for (int j = 0; j < COLUMNS; j++) {
	                System.out.print(matrix[i][j] + " ");
	            }
	            System.out.println();
	        }
	}
	// Method that validates if it's a palindrome 
	public boolean isPalindrome(String str) {
		
		int length = str.length();
		
		for (int i = 0; i < length / 2; i++) {
			if (str.charAt(i)!= str.charAt(length - i - 1)) {
				return false;
			}
		}
		return true;
		
	}
	// Getters for rows and Columns 
	public int getRows() {
		return ROWS;
	}
	public int getColumns() {
		return COLUMNS;
	} 
	
	public static void main(String[] args) {
		
		matrixGenerator();

		
	}
}
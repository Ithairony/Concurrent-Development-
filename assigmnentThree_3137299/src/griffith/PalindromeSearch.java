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
import java.util.concurrent.atomic.AtomicInteger;

public class PalindromeSearch {

	// Declaring variables 
	private final static int ROWS = 10;
	private final static int COLUMNS = 10;

	// Creating a  matrix of Rows X Columns 
	public static char[][] matrix = new char[ROWS][COLUMNS];
	private static AtomicInteger count = new AtomicInteger(0);

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

	// Search palindromes in all directions
	public static void searchPalindromes(int n, int nThreads) {

	}
	
	// Method that benchmarks the execution time of the threads with adjustable number of chars per substring to be tested by isPalindrome 
	public static void benchmarkPalindromes(int minLetters, int maxLetters) {
		
		for (int palindromeLength = minLetters; palindromeLength <= maxLetters; palindromeLength++) {
			System.out.println("Searching for palindromes of length " + palindromeLength);

			int nThreads = Runtime.getRuntime().availableProcessors(); // Gets the available number of threads ( Varies according to each machine)

			for (int t = 1; t <= nThreads; t++) {
				count.set(0);
				long startTime = System.nanoTime();
				searchPalindromes(palindromeLength, t);
				long endTime = System.nanoTime();
				double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;

				System.out.printf("%d palindromes of size %d found in %.6f s using %d thread(s)%n",
						count.get(), palindromeLength, elapsedSeconds, t);
			}
			System.out.println();
		}
		
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
		benchmarkPalindromes(3,6);		

		


		// Search LEFT - RIGHT --> matrix[i][j + n - 1]
		//if ( j + n < COLUMNS) {

		// Search UP - DOWN  --> matrix[i + n - 1][j]
		// Search diagonally UP - DOWN --> matrix[i + n - 1][j + n - 1]
	}
}
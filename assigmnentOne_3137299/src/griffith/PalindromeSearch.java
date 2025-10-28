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
	private final static int ROWS = 1000;
	private final static int COLUMNS = 1000;

	// Creating a matrix of Rows X Columns 
	public static char[][] matrix = new char[ROWS][COLUMNS];
	// An atomic integer is a thread-safe integer that supports atomic operations without explicit locking.
	private static AtomicInteger count = new AtomicInteger(0);

	// Method to generate the array with random chars 
	public static char[][] matrixGenerator() {
		Random random = new Random();
		// Loops to populate ROWS and COLUMNS
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j< COLUMNS; j++) {
				// Generates a random number from 0 - 26 on which when added to 'a' gets me letters from a-z
				matrix[i][j] = (char) ('a' + random.nextInt(26));
			}
		}
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
	public static boolean isPalindrome(String str) {
		// Gets the length of the given string 
		int length = str.length();
		// Compares first and last, second and second last, third and third last ...
		for (int i = 0; i < length / 2; i++) {
			if (str.charAt(i)!= str.charAt(length - i - 1)) {
				return false;
			}
		}
		return true;
	}

	// Search palindromes in all directions, in this method I want to create a pool and divide the tasks within threads 
	public static void searchPalindromes(int n, int nThreads) {
		ExecutorService pool = Executors.newFixedThreadPool(nThreads); // Instantiate fixed-size thread pool according to the number of threads
		int rowsPerThread = ROWS / nThreads; // Divides the rows with the available threads 
		// Loops through each thread
		for (int i = 0; i < nThreads; i++) {
			final int threadIndex = i;
			int startRow = threadIndex * rowsPerThread;  // Starting row for this thread
			int endRow = (threadIndex == nThreads - 1) ? ROWS : startRow + rowsPerThread; // Make sure last thread covers any remaining rows

			// Submits a task to the pool for execution
			pool.submit(() -> {

				// Iterate through the assigned section of the matrix
				for (int row = startRow; row < endRow; row++) {
					for (int col = 0; col < COLUMNS; col++) {

						// LEFT → RIGHT
						if (col + n <= COLUMNS) {
							StringBuilder sb = new StringBuilder();
							for (int k = 0; k < n; k++) {
								sb.append(matrix[row][col + k]);
							}
							// If palindrome, increment the counter
							if (isPalindrome(sb.toString())) {
								count.incrementAndGet();
							}
						}

						// TOP → BOTTOM
						if (row + n <= ROWS) {
							StringBuilder sb = new StringBuilder();
							for (int k = 0; k < n; k++) {
								sb.append(matrix[row + k][col]);
							}
							if (isPalindrome(sb.toString())) { 
								count.incrementAndGet();
							}
						}

						// DIAGONAL 
						if (row + n <= ROWS && col + n <= COLUMNS) {
							StringBuilder sb = new StringBuilder();
							for (int k = 0; k < n; k++) {
								sb.append(matrix[row + k][col + k]);
							}
							if (isPalindrome(sb.toString())) {
								count.incrementAndGet(); // incrementAndGet is a method of the AtomicInteger class that Atomically decrements the current value, and returns its updated value
							}
						}
					}
				}
			});
		}
		// Prevents new tasks from being submitted and begins shutdown process
		pool.shutdown();
		try {
			// Waits for all threads to finish execution
			pool.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Method that benchmarks the execution time of the threads with adjustable number of chars per substring to be tested by isPalindrome 
	public static void benchmarkPalindromes(int minLetters, int maxLetters) {
		
		// Iterates over the range of palindrome lengths to test
		for (int palindromeLength = minLetters; palindromeLength <= maxLetters; palindromeLength++) {
			System.out.println("Searching for palindromes of length " + palindromeLength);

			int nThreads = Runtime.getRuntime().availableProcessors(); // Gets the available number of threads ( Varies according to each machine)
			
			// Tests performance for each possible thread count (1 to max cores)
			for (int i = 1; i <= nThreads; i++) {
				count.set(0);	// Resets the atomic palindrome counter
				long startTime = System.nanoTime();
				searchPalindromes(palindromeLength, i); 	// Executes search with 'i' threads
				long endTime = System.nanoTime();
				double totalTime = (endTime - startTime) / 1000000000.0;	// Convertsto seconds

				System.out.printf("%d palindromes of size %d found in %.6f s using %d thread(s)%n",
						count.get(), palindromeLength, totalTime, i);
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
	}
}
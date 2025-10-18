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
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		int rowsPerThread = ROWS / nThreads; // Divides the rows with the available threads 
	THis bit need attetipn so it does not fall on plagiarism 
//		for (int i = 0; i < nThreads; i++) {
//			final int threadIndex = i;
//			int startRow = threadIndex * rowsPerThread;
//		    int endRow = (threadIndex == nThreads - 1) ? ROWS : startRow + rowsPerThread;
//		    
//		    pool.submit(() -> {
//		        for (int row = startRow; row < endRow; row++) {
//		            for (int col = 0; col < COLUMNS; col++) {
//
//		                // LEFT → RIGHT
//		                if (col + n <= COLUMNS) {
//		                    StringBuilder sb = new StringBuilder();
//		                    for (int k = 0; k < n; k++) sb.append(matrix[row][col + k]);
//		                    if (isPalindrome(sb.toString())) count.incrementAndGet();
//		                }
//
//		                // TOP → BOTTOM
//		                if (row + n <= ROWS) {
//		                    StringBuilder sb = new StringBuilder();
//		                    for (int k = 0; k < n; k++) sb.append(matrix[row + k][col]);
//		                    if (isPalindrome(sb.toString())) count.incrementAndGet();
//		                }
//
//		                // DIAGONAL ↘
//		                if (row + n <= ROWS && col + n <= COLUMNS) {
//		                    StringBuilder sb = new StringBuilder();
//		                    for (int k = 0; k < n; k++) sb.append(matrix[row + k][col + k]);
//		                    if (isPalindrome(sb.toString())) count.incrementAndGet();
//		                }
//
//		                // DIAGONAL ↙ (opcional)
//		                if (row + n <= ROWS && col - n >= -1) {
//		                    StringBuilder sb = new StringBuilder();
//		                    for (int k = 0; k < n; k++) sb.append(matrix[row + k][col - k]);
//		                    if (isPalindrome(sb.toString())) count.incrementAndGet();
//		                }
//		            }
//		        }
//		    });
//		}
		
		pool.shutdown();
	    try {
	        pool.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		// Search UP - DOWN  --> matrix[i + n - 1][j]
		// Search diagonally UP - DOWN --> matrix[i + n - 1][j + n - 1]
	}
	
	// Method that benchmarks the execution time of the threads with adjustable number of chars per substring to be tested by isPalindrome 
	public static void benchmarkPalindromes(int minLetters, int maxLetters) {
		
		for (int palindromeLength = minLetters; palindromeLength <= maxLetters; palindromeLength++) {
			System.out.println("Searching for palindromes of length " + palindromeLength);

			int nThreads = Runtime.getRuntime().availableProcessors(); // Gets the available number of threads ( Varies according to each machine)

			for (int i = 1; i <= nThreads; i++) {
				count.set(0);
				long startTime = System.nanoTime();
				searchPalindromes(palindromeLength, i);
				long endTime = System.nanoTime();
				double totalTime = (endTime - startTime) / 1_000_000_000.0;

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
package griffith;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EvenCounter {

	public static void main(String[] args) {

		int n = 1000000;
		int nThreads = Runtime.getRuntime().availableProcessors(); // Gets the available number of threads ( Varies according to each machine)  
		int index[] = new int[nThreads + 1];

		// This loop populates the array with random numbers and according to the index number n  
		int data[] = new int[n];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random()*100000);
		}

		// This loop divides the data[] equally within the available threads
		for (int i = 0; i <= nThreads; i++) {
			index[i] = (i*n) / nThreads;
		}

		// Creating a threadpool according to the number of threads available 
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		System.out.println("Thread pool created with " + nThreads + " threads.\n" + pool); // Checking if pool was created
		long startTime = System.currentTimeMillis();

		// List of futures that will hold the results from each callable
		ArrayList<Future<int[]>> future = new ArrayList<Future<int[]>>();
		for (int i = 0; i < nThreads; i++) {
			Search searchTask = new Search(data, index[i], index[i+1]);
			future.add(pool.submit(searchTask));
		}

		System.out.println("Threadpool details " + pool); // Checking if pool was created

		// This bit is being replaced by the callable interface applied already in Search

		// Thread[] workers	 = new Thread[nThreads];
		//		for (int j = 0; j <nThreads; j++) {
		//			// Creates individuals threads for each partition 
		//			workers[j] = new Search(data, index[j], index[j+1]);
		//			workers[j].start();
		//		}
		//
		//		for(int j = 0; j < nThreads; j++) {
		//			try {
		//				workers[j].join();
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}
		//
		//		int freq = 0;
		//		for (int i = 0; i < nThreads; i++) {
		//			freq += ((Search) workers[i]).frequency();
		//		}
		
		// Shuts the pool down so it stops working 
		pool.shutdown();
		
		long endTime =  System.currentTimeMillis();
		long runningTime = endTime - startTime;

		//		System.out.println("Even frequency: " + freq);
		//		System.out.println("Highest value is : " + highestValue);
		System.out.println("Running time (ms): " + runningTime);
		System.out.println("Seconds: " + (runningTime / 1000.0));

	}

}
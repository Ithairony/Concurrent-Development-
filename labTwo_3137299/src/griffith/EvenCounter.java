package griffith;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenCounter implements Callable<Integer>{

	public static void main(String[] args) {

		int n = 1000000;
		int nThreads = Runtime.getRuntime().availableProcessors();
		int index[] = new int[nThreads + 1];
		
		
		// Creating a threadpool
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		System.out.println(pool); // Checking if it was created

		for (int j = 0; j <= nThreads; j++) {
			index[j] = (j*n) / nThreads;
		}

		int data[] = new int[n];
		for (int j = 0; j < data.length; j++) {
			data[j] = (int) (Math.random()*100000);
		}

		Thread[] workers	 = new Thread[nThreads];

		long startTime = System.currentTimeMillis();

		for (int j = 0; j <nThreads; j++) {
			workers[j] = new Search(data, index[j], index[j+1]);
			workers[j].start();
		}

		for(int j = 0; j < nThreads; j++) {
			try {
				workers[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int freq = 0;
		for (int i = 0; i < nThreads; i++) {
			freq += ((Search) workers[i]).frequency();
		}

		long endTime =  System.currentTimeMillis();
		long runningTime = endTime - startTime;

		System.out.println("Even frequency: " + freq);
		System.out.println("Running time (ms): " + runningTime);
		System.out.println("Seconds: " + (runningTime / 1000.0));

	}
	// Call method implemented by the Callable interface 
	@Override
	public Integer call() throws Exception {
		
		return null;
	}
}
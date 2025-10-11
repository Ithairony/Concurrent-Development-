package griffith;

public class EvenCounter {

	public static void main(String[] args) {

		int n = 1000000;
		int nThreads = Runtime.getRuntime().availableProcessors();
		int index[] = new int[nThreads + 1];

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
}
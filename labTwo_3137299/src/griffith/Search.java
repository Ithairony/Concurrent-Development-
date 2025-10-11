package griffith;

import java.util.concurrent.Callable;

// In order to use Callable I need to get rid of the Runnable stuff
public class Search implements Callable<int[]> { // Returns a int [ freq, highestValue ]

	// Declaring variables 
	int data[];
	int lower, upper;

	// Constructor 
	public Search(int data[], int lower, int upper ) {
		this.data = data;
		this.lower = lower;
		this.upper = upper;
	}

	@Override
	public int[] call() {
		
		int freq = 0;
		int highestValue = 0;
		// Loop through the array to find the frequency and the highestValue 
		for ( int i = lower; i < upper; i++) {
			// if x % 2 == 0  means its an even number --> 4%2 == 0  || 5%2 == 1
			if (data[i] % 2 == 0) 
				freq++;
			if (data[i] > highestValue) {
				highestValue = data[i];
			}
		}
		return new int[] {freq, highestValue};
	}

}

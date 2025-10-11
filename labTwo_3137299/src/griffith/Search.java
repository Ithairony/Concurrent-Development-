package griffith;

public class Search extends Thread {
	
	int data[];
	int freq;
	int lower, upper;
	int i;
	
	// COnstructor 
	public Search(int d[], int a, int b ) {
		data = d;
		lower = a;
		upper = b;
	}
		
	public void run() {
		freq = 0;
		// Loop through the array to find the freq	of even numbers
		for ( int j = lower; j < upper; j++) {
			// if x % 2 == 0  means its an even number --> 4%2 == 0  || 5%2 == 1
			if (data[j] % 2 == 0) 
				freq++;
		}
	}
	
	public int frequency() {
		return freq;
	}
}

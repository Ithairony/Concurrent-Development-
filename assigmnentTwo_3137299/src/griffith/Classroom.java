/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Classroom {
	
	// Declaring class properties 
	String name;
	private int capacity ;
	Lecturer lecturer = null;
	boolean inSession = false;
	int students = 0;
	int visitors = 0;
	
	
	// Synchronization 
	public final Semaphore capacitySemaphore;
	public final Semaphore lecturerSemaphore = new Semaphore(1); // Allows only 1 teacher at a time for each classroom
	public final ReentrantLock lock = new ReentrantLock();
	
	public Classroom(String name , int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.capacitySemaphore = new Semaphore(capacity);
	}
	
	
	// Getters and Setters
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}

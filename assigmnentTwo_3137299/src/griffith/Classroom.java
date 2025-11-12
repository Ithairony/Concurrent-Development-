/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Classroom {
	
	// Declaring Classroom properties 
	String name;
	private int capacity ;	// Declares class capacity ( Students + visitor should not exceed its capacity) 
	Lecturer lecturer = null;	// Each classroom should have a Lecturer assigned to it 
	boolean inSession = false;	// If a class is taking place this variable should be set to true
	int students = 0;	// keeps track of the number of active students in the classroom	
	int visitors = 0;	// keeps track of the number of active visitor in the classroom	
	
	
	// Synchronization 
	public final Semaphore capacitySemaphore;
	public final Semaphore lecturerSemaphore = new Semaphore(1); // Allows only 1 teacher at a time for each classroom
	public final ReentrantLock lock = new ReentrantLock();	// Declares a lock 
	
	// Constructor 
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

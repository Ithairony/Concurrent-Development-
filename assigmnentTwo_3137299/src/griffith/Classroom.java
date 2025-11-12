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


	// Synchronization primitives 
	public final Semaphore capacitySemaphore;	// Sets a count Semaphore 
	public final Semaphore lecturerSemaphore; // Sets a binary Semaphore 
	public final ReentrantLock lock = new ReentrantLock();	// Declares a lock 

	// Constructor 
	public Classroom(String name , int capacity) {
		this.name = name;
		this.capacity = capacity; 

		// Initialize semaphores *after* capacity is known
		this.capacitySemaphore  =  new Semaphore(capacity);
		this.lecturerSemaphore  =   new Semaphore(1);
	}


	// Getters and Setters
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


}

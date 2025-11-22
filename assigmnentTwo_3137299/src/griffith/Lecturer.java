/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.List;
import java.util.Random;

public class Lecturer extends Thread {

	// Declaring variables of Lecturer 
	String name;
	boolean inSession = false;
	Classroom assignedClassroom;
	// Shared (static) references
	private static List<Classroom> allClassrooms;
	private static final Random random = new Random();

	// Constructor 
	public Lecturer(String name, Classroom classroom) {
		this.name = name;
		this.assignedClassroom = classroom;
	}
	// Allows list of all classrooms to be shared with this class
	public static void setClassrooms(List<Classroom> classrooms) {
		allClassrooms = classrooms;
	}

	@Override
	public void run() {
		try {
			while (true) {
				enter();				// Lecturer enter the classroom 
				startLecture();		// Lecture starts his class 
				Thread.sleep(4000); 	// simulate lecture time to 4s 
				leave();				// Lecture leaves the classroom
				Thread.sleep(2000); 	// rest 2s before moving to another classroom
				circulate();       	// Move to a new random classroom
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Method that makes sure lecturers takes circular classrooms
	private void circulate() {
		// First check if there's classrooms available to enter 
		if ( allClassrooms == null || allClassrooms.isEmpty()) {
			return;
		}
		// Start with the currently assigned classroom
		Classroom nextClassroom = assignedClassroom;
		// pick a new classroom different from current
		while (nextClassroom == assignedClassroom && allClassrooms.size() > 1) {
			nextClassroom = allClassrooms.get(random.nextInt(allClassrooms.size()));
		}
		// Set the chosen classroom as the new assigned classroom
		assignedClassroom = nextClassroom;

	}
	// Method for lecturer entering the classroom
	public void enter() throws InterruptedException {
		// Acquire the lecturer semaphore (allows only one lecturer)
		assignedClassroom.lecturerSemaphore.acquire();
		assignedClassroom.lock.lock();	// Lock the classroom to modify its state safely

		try {
			// Wait till session is finished 
			while (assignedClassroom.inSession) {
				assignedClassroom.lock.unlock();	// unlock before entering
				Thread.sleep(100);				// wait for 100ms
				//System.out.println(name + " entered room : " + assignedClassroom.name);
				assignedClassroom.lock.lock();	// Lock again
			}
			assignedClassroom.lecturer = this;	// Assign the lecturer to the classroom
			Thread.sleep(2000); 					// Allow 2s for students to enter before starting
		} finally {
			// Always unlock the classroom, even if an error occurs
			assignedClassroom.lock.unlock();
		}
	}
	// Method to start a lecture in the assigned classroom
	public void startLecture() {
		assignedClassroom.lock.lock();	// Locking it before altering its state
		try {
			assignedClassroom.inSession = true;	// classroom is now inSession
			inSession = true;					// Lecturer is also inSession
			//System.out.println(name + " started lecture in room : " + assignedClassroom.name);
		} finally {
			assignedClassroom.lock.unlock();		// unlock as always 
		}
	}
	// Method for lecturer leaving the classroom
	public void leave() {
		assignedClassroom.lock.lock();	// Lock the classroom to modify its state safely
		try {
			assignedClassroom.inSession = false;		// once it leaves mark the inSession to false 
			inSession = false;						// Makes lecturer available for other classrooms 
			//System.out.println(name + " left " + assignedClassroom.name);
			assignedClassroom.lecturer = null;		// Removes lectures reference 
		} finally {
			assignedClassroom.lock.unlock();					// Unlock the classroom 
			assignedClassroom.lecturerSemaphore.release();	// Lecturer leaves -> release access to other lecturers 
		}	
	}

}
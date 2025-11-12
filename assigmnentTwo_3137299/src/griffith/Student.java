/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */


package griffith;

public class Student extends Thread {

	// Declaring variables 
	String name;
	int studentID;
	Classroom classroom;

	// Constructor 
	public Student(String name, int id, Classroom classroom) {
		this.name = name;
		this.studentID = id;
		this.classroom = classroom;
	}

	public void enter() {
		try {
			// Wait for available space in the classroom
			classroom.capacitySemaphore.acquire(); // Uses the counting semaphore to keep track of classroom capacity 
			classroom.lock.lock();
			try {
				// Check if class is still happening 
				if (!classroom.inSession) {
					classroom.capacitySemaphore.release();
					return;
				}
				classroom.students++;
				System.out.println(name + " entered " + classroom.name);
			} finally {
				classroom.lock.unlock();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}


	public void leave() {
	    classroom.lock.lock();
	    try {
	        classroom.students--;
	        System.out.println(name + " left " + classroom.name);
	        classroom.capacitySemaphore.release();
	    } finally {
	        classroom.lock.unlock();
	    }
	}

	@Override
	public void run() {
	    try {
	        // Wait until lecture starts
	        while (!classroom.inSession) {
	            Thread.sleep(200);
	        }
	        // Once classroom has started, student enter the room 
	        enter();
	        // Stay until lecture ends
	        while (classroom.inSession) {
	            Thread.sleep(500);
	        }
	        leave();
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	// Getters and Setters
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

}

/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Lecturer extends Thread {
	
	// Declaring variables of Lecturer 
	String name;
	boolean inSession = false;
	Classroom assignedClassroom;
	
	// Constructor 
	public Lecturer(String name, Classroom classroom) {
		this.name = name;
		this.assignedClassroom = classroom;
	}
	
	@Override
	public void run() {
	    try {
	        while (true) {
	            enter();		// Lecturer enter the classroom 
	            startLecture();	// Lecture starts his class 
	            Thread.sleep(4000); // simulate lecture time
	            leave();		// Lecture leaves the classroom
	            Thread.sleep(2000); // rest before moving to another classroom
	        }
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	public void enter() throws InterruptedException {
	    assignedClassroom.lecturerSemaphore.acquire(); // Binary semaphore 
	    assignedClassroom.lecturer = this;
	    System.out.println(name + " entered " + assignedClassroom.name);
	}

	public void startLecture() {
	    assignedClassroom.inSession = true;	// Sets flag that indicates if there is a lecture on to true so that nobody can enter or leave
	    inSession = true;
	    System.out.println(name + " started lecture in " + assignedClassroom.name);
	}

	public void leave() {
	    assignedClassroom.inSession = false;
	    inSession = false;
	    System.out.println(name + " left " + assignedClassroom.name);
	    assignedClassroom.lecturer = null;
	    assignedClassroom.lecturerSemaphore.release();	// Lecturer leaves -> release access to other lecturers 
	}

	
	public void startLecturer() {
		inSession = true;
	}
}
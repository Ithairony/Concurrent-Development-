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
	            enter();
	            startLecture();
	            Thread.sleep(4000); // simulate lecture time
	            leave();
	            Thread.sleep(2000); // rest before moving to another classroom
	        }
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	public void enter() throws InterruptedException {
	    assignedClassroom.lock.lock(); // only one lecturer can enter
	    assignedClassroom.lecturer = this;
	    System.out.println(name + " entered " + assignedClassroom.name);
	}

	public void startLecture() {
	    assignedClassroom.inSession = true;
	    inSession = true;
	    System.out.println(name + " started lecture in " + assignedClassroom.name);
	}

	public void leave() {
	    assignedClassroom.inSession = false;
	    inSession = false;
	    System.out.println(name + " left " + assignedClassroom.name);
	    assignedClassroom.lecturer = null;
	    assignedClassroom.lock.unlock();
	}

	
	
	public void startLecturer() {
		inSession = true;
	}
}
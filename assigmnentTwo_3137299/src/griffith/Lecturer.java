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
				enter();		// Lecturer enter the classroom 
				startLecture();	// Lecture starts his class 
				Thread.sleep(4000); // simulate lecture time
				leave();		// Lecture leaves the classroom
				Thread.sleep(2000); // rest before moving to another classroom
				circulate();       // Move to a new random classroom
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	// Method that makes sure lecturers takes circular classrooms
	private void circulate() {
		
		if ( allClassrooms == null || allClassrooms.isEmpty()) {
			return;
		}
		
		Classroom nextClassroom = assignedClassroom;
		
		// pick a new classroom different from current
        while (nextClassroom == assignedClassroom && allClassrooms.size() > 1) {
            nextClassroom = allClassrooms.get(random.nextInt(allClassrooms.size()));
        }
        
        assignedClassroom = nextClassroom;

	}

	public void enter() throws InterruptedException {
		
		assignedClassroom.lecturerSemaphore.acquire();
		assignedClassroom.lock.lock();
		
		try {
			while (assignedClassroom.inSession) {
				assignedClassroom.lock.unlock();
				Thread.sleep(100);
				//System.out.println(name + " entered room : " + assignedClassroom.name);
				assignedClassroom.lock.lock();
			}
			assignedClassroom.lecturer = this;
			Thread.sleep(2000); // Allow students to enter before starting
		} finally {
			assignedClassroom.lock.unlock();
		}
	}

	public void startLecture() {
		assignedClassroom.lock.lock();
		try {
			assignedClassroom.inSession = true;
			inSession = true;
			//System.out.println(name + " started lecture in room : " + assignedClassroom.name);
		} finally {
			assignedClassroom.lock.unlock();
		}
	}

	public void leave() {
		assignedClassroom.lock.lock();
		try {
			assignedClassroom.inSession = false;
			inSession = false;
			//System.out.println(name + " left " + assignedClassroom.name);
			assignedClassroom.lecturer = null;
		} finally {
			assignedClassroom.lock.unlock();
			assignedClassroom.lecturerSemaphore.release();	// Lecturer leaves -> release access to other lecturers 
		}	
	}

}
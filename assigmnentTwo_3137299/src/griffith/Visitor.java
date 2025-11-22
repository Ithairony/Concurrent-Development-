/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Visitor extends Thread {

	// Variables of Visitor 
	String name;
	int visitorID;	// VAriable to keep track of visitors accessing classroom
	Classroom classroom;

	// Constructor 
	public Visitor(String name, int id, Classroom classroom) {
		this.name = name;
		this.visitorID = id;
		this.classroom = classroom;
	}

	public void enter()	{
		try {
			// Wait for available space in the classroom
			classroom.capacitySemaphore.acquire(); // Uses the counting semaphore to keep track of classroom capacity 
			classroom.lock.lock();
			try {
				// Check if class is happening 
				if (!classroom.inSession) {
					classroom.capacitySemaphore.release();
					return;
				}
				classroom.visitors++; // increments the visitors count 
				// System.out.println(name + " entered " + classroom.name);
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
			if (classroom.students > 0) {
				classroom.visitors--;	// decrements the visitors count
			}
			// System.out.println(name + " left " + classroom.name);
			classroom.capacitySemaphore.release();
		} finally {
			classroom.lock.unlock();
		}
	}

	@Override
	public void run() {
		try {
			while (true) { // Loop forever to attend multiple lectures

				// Wait until lecture starts
				while (!classroom.inSession) {
					Thread.sleep(200);
				}

				// Wait for available space and enter
				enter();

				// Stay until lecture ends
				while (classroom.inSession) {
					Thread.sleep(100);
				}
				// leave the classroom
				leave();

			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Getters and Setters
	public int getVisitorID() {
		return visitorID;
	}

	public void setVisitorID(int visitorID) {
		this.visitorID = visitorID;
	}
}

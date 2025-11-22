/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Monitor class extends Thread to run continuously and display classroom status
public class Monitor extends Thread {

	// Properties of Monitor 
	String name;
	int monitorID;
	List<Classroom> classrooms;

	// Constructor 
	public Monitor(String name, int id, List<Classroom> classrooms) {
		this.name = name;
		this.monitorID = id;
		this.classrooms = classrooms;
	}

	// Thread execution method
	public void run() {
		try {
			while (true) {
				printStatus();			// Print status of classrooms 
				Thread.sleep(2000);  	// update every 2 seconds
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();	// Restore interrupt state if interrupted
		}
	}
	// Synchronized method to ensure only one thread prints at a time
	public synchronized void printStatus() {
		System.out.println("==================================================================================");
		System.out.printf("%-10s %-10s %-10s %-12s %-10s %-10s%n",
				"Classroom", "Capacity", "Lecturer", "InSession", "Students", "Visitors");
		// Loop through all classrooms
		for (Classroom c : classrooms) {
			c.lock.lock();	// Lock classroom to safely access 
			try {
				// Check if a lecturer is inside, if not it will print "None"
				String lecturerName = (c.lecturer != null) ? c.lecturer.name : "None";
				
				// Print formatted classroom status
				System.out.printf("%-10s %-10d %-10s %-12s %-10d %-10d%n",
						c.name,
						c.getCapacity(),
						lecturerName,
						c.inSession,
						c.students,
						c.visitors
						);
			} finally {
				c.lock.unlock();	// Always unlock classroom even if an error occurs
			}
		}
	}

	// Getters and Setters
	public int getMonitorID() {
		return monitorID;
	}

	public void setMonitorID(int monitorID) {
		this.monitorID = monitorID;
	}

	public static void main (String[] args) {

		// Creates a list of classrooms
		List<Classroom> classroomList = new ArrayList<>();
		classroomList.add(new Classroom("W201", 60));
		classroomList.add(new Classroom("W202", 60));
		classroomList.add(new Classroom("JS101", 40));
		classroomList.add(new Classroom("W101", 50));

		// Creates a list of lecturers
		String[] lecturerNames = {"Osama", "Barry", "Tracey", "Gemma", "Thamas", "Ellen"};
		List<Lecturer> lecturers = new ArrayList<>();;
		//Random random = new Random();
		
		// Assign classrooms to Lecturer class 
		Lecturer.setClassrooms(classroomList);
		
		// Create and start lecturer threads according to lecturerNames length 
		for (int i = 0; i < lecturerNames.length; i++) {
			Classroom classroom = classroomList.get(i % classroomList.size());
			Lecturer lecturer = new Lecturer(lecturerNames[i], classroom);	// Declares a lecturer
			lecturers.add(lecturer);		// Add to lecturers list 	
			lecturer.start();			// Start Thread 
		}


		// Creates students
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Student student = new Student("Student" + i, i, classroomList.get(i % classroomList.size()));
			students.add(student);
			student.start();
		}

		// Creates visitors
		List<Visitor> visitors = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Visitor visitor = new Visitor("Visitor" + i, i, classroomList.get(i % classroomList.size()));
			visitors.add(visitor);
			visitor.start();
		}

		// Start the monitor thread
		Monitor monitor = new Monitor("MainMonitor", 1, classroomList);
		monitor.start();
	}
}

/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.ArrayList;
import java.util.List;

public class Monitor extends Thread {

	// Properties of Monitor 
	String name;
	int monitorID;
	static List<Classroom> classrooms;

	// Constructor 
	public Monitor(String name, int id, List<Classroom> classrooms) {
		this.name = name;
		this.monitorID = id;
		Monitor.classrooms = classrooms;
	}

	public void run() {
		try {
			while (true) {
				printStatus();
				Thread.sleep(2000);  // update every 2 seconds
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void printStatus() {
		  System.out.println("==================================================================================");
		    System.out.printf("%-10s %-10s %-10s %-12s %-10s %-10s%n",
		            "Classroom", "Capacity", "Lecturer", "InSession", "Students", "Visitors");
		   
		    for (Classroom c : classrooms) {
		        c.lock.lock();
		        try {
		            String lecturerName = (c.lecturer != null) ? c.lecturer.name : "None";

		            System.out.printf("%-10s %-10d %-10s %-12s %-10d %-10d%n",
		                    c.name,c.getCapacity(),lecturerName, c.inSession, c.students,c.visitors
		            );
		        } finally {
		            c.lock.unlock();
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
	    classroomList.add(new Classroom("W201", 40));
	    classroomList.add(new Classroom("W202", 40));
	    classroomList.add(new Classroom("JS101", 30));
	    classroomList.add(new Classroom("W101", 20));

	    // Creates a list of lecturers 
	    List<Lecturer> lecturerList = new ArrayList<>();
	    lecturerList.add(new Lecturer("Osama", classroomList.get(0)));
	    lecturerList.add(new Lecturer("Barry", classroomList.get(1)));
	    lecturerList.add(new Lecturer("Thamas", classroomList.get(2)));
	    lecturerList.add(new Lecturer("Ellen", classroomList.get(3)));

	    // Start the monitor thread
	    Monitor monitor = new Monitor("MainMonitor", 1, classroomList);
	    monitor.start();
	}
}

/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.List;

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
	
	public void printStatus(){
		 System.out.println("==================================================================================");
	     System.out.println("Classroom\tCapacity\tLecturer\tinSession\tStudents\tVisitors");
	     System.out.println("==================================================================================");
	        
	     // Loop through classrooms printing info for each classroom
	     for ( Classroom c : classrooms) {
	    	 c.lock.lock();
	    	 try {
	    		 if ( c.lecturer != null) {
	    			 System.out.println("%-10s\t%-8d\t%-10s\t%-10s\t%-8d\t%-8d%n",
	                            c.name, c.getCapacity(), c.lecturer, c.inSession, c.students, c.visitors);
	    		 } else {
	    			 System.out.printf("%-10s\t%-10s\t%-10s\t%-8d\t%-8d%n",
	                            c.name, "None", c.inSession, c.students, c.visitors); 
	    		 }
	    	 } finally {
	    		 c.lock.unlock();	    	 }
	     }
	}
	// Getters and Setters
	public int getMonitorID() {
		return monitorID;
	}

	public void setMonitorID(int monitorID) {
		this.monitorID = monitorID;
	}
}

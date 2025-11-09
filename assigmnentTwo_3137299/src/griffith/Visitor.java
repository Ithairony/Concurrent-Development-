/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Visitor extends Thread {
	
	// Variables of Visitor 
	String name;
	int visitorID;
	Classroom classroom;
	
	// Constructor 
	public Visitor(String name, int id, Classroom classroom) {
		this.name = name;
		this.visitorID = id;
		this.classroom = classroom;
	}
	
	// Getters and Setters
	public int getVisitorID() {
		return visitorID;
	}

	public void setVisitorID(int visitorID) {
		this.visitorID = visitorID;
	}
}

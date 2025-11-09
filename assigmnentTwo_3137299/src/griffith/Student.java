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
	
	public static void enter()
	{
		// Student enters classroom
	}
	
	public static void leave() {
		
	}
	
	// Getters and Setters
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
}

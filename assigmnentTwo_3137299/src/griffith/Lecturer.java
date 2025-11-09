/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */


package griffith;

public class Lecturer extends Thread {
	
	// Declaring variables of Lecturer 
	String name;
	static boolean inSession = false;
	
	// Constructor 
	public Lecturer(String name) {
		this.name = name;
	}
	
	
	public static void enter()
	{
		// Lecturer enters classroom
	}
	
	public static void leave() {
		
	}
	
	public static void startLecturer() {
		inSession = true;
	}
}
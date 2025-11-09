/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Classroom {
	
	String name;
	private int capacity ;
	String lecturer = null;
	boolean inSession = false;
	int students = 0;
	int visitors = 0;
	
	public Classroom(String name , int capacity) {
		this.setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}

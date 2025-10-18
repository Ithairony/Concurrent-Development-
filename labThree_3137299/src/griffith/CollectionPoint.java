/**
 * Point.java
 *
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */
package griffith;

import java.util.*;

class CollectionPoint{
	// ArrayList to store points
	List<Point> listOfPoints = new ArrayList<>();

	public void add(Point p){
		// I could check if the point is already in, but I guess that having two or more points with similar coordinates shouldn't be a problem 
		listOfPoints.add(p);
	}
	
	public boolean search(Point p){
		// Loop through the array searching for the point/points 
		for (int i = 0; i < listOfPoints.size(); i++ ) {
			if (listOfPoints.get(i) .equals(p)) {
				return true;
			} 
		}
		// If not found 
		return false;
		
	}
	
	public List<Point> getAllX(int x){
		
		List<Point> result = new ArrayList<>();
		
		for (int i = 0; i < listOfPoints.size(); i++) {
			Point current = listOfPoints.get(i);
			if (current.getX() == x) {
				result.add(current);
				}
		}
		
		return result;
	}
	
	public void replace(Point p1, Point p2){
		// To replace a point we first need to make sure it is in the array 	
		int index = listOfPoints.indexOf(p1); // Gets the index of p1, if existing 
		if (index != -1) {
			// Sets the new point to the same position index where the p1 was 
			listOfPoints.set(index, p2);
		}
	}


	public String toString(){
		return listOfPoints.toString();
	}
	
}
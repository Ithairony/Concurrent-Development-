/**
 * Point.java
 *
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */
package griffith;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CollectionPoint{
	// ArrayList to store points
	// For synchronization it is better to have the list as private and final 
	private final List<Point> listOfPoints = new ArrayList<>();

	// Add keyword 'synchronized' to each method so that we ensure that only one thread at a time can execute any of this methods
	public synchronized void add(Point p){
		// I could check if the point is already in, but I guess that having two or more points with similar coordinates shouldn't be a problem 
		listOfPoints.add(p);
	}
	
	public synchronized boolean search(Point p){
		// Loop through the array searching for the point/points 
		for (int i = 0; i < listOfPoints.size(); i++ ) {
			if (listOfPoints.get(i) .equals(p)) {
				return true;
			} 
		}
		// If not found 
		return false;
		
	}
	
	public synchronized List<Point> getAllX(int x){
		
		List<Point> result = new ArrayList<>();
		
		for (int i = 0; i < listOfPoints.size(); i++) {
			Point current = listOfPoints.get(i);
			if (current.getX() == x) {
				result.add(current);
				}
		}
		
		return result;
	}
	
	public synchronized void replace(Point p1, Point p2){
		// To replace a point we first need to make sure it is in the array 	
		int index = listOfPoints.indexOf(p1); // Gets the index of p1, if existing 
		if (index != -1) {
			// Sets the new point to the same position index where the p1 was 
			listOfPoints.set(index, p2);
		}
	}

	@Override
	public String toString(){
		// You can choose to either make a method or a block synchronized 
		 synchronized (listOfPoints) {
		        return listOfPoints.toString();
		    }
	}
	
	public static void main(String[] args ) {
		
		CollectionPoint cp = new CollectionPoint();
		int nThreads = Runtime.getRuntime().availableProcessors(); // Gets the available number of threads ( Varies according to each machine)
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		
		// Task 1: Add points
	    Runnable addTask = () -> {
	        for (int i = 0; i < 5; i++) {
	            Point p = new Point(i, i * 2);
	            cp.add(p);
	            System.out.println(Thread.currentThread().getName() + " added: " + p);
	            try { Thread.sleep(50); } catch (InterruptedException e) {}
	        }
	    };
	    
	    // Task 2: Search points
	    Runnable searchTask = () -> {
	        for (int i = 0; i < 5; i++) {
	            Point p = new Point(i, i * 2);
	            boolean found = cp.search(p);
	            System.out.println(Thread.currentThread().getName() + " searching " + p + ": " + found);
	            try { Thread.sleep(70); } catch (InterruptedException e) {}
	        }
	    };
	    
	 // Task 3: Replace points
	    Runnable replaceTask = () -> {
	        for (int i = 0; i < 5; i++) {
	            Point oldP = new Point(i, i * 2);
	            Point newP = new Point(i + 10, i + 20);
	            cp.replace(oldP, newP);
	            System.out.println(Thread.currentThread().getName() + " replaced " + oldP + " with " + newP);
	            try { Thread.sleep(90); } catch (InterruptedException e) {}
	        }
	    };
	    
	 // Submit multiple tasks to the pool
	    pool.submit(addTask);
	    pool.submit(searchTask);
	    pool.submit(replaceTask);

		
		// Shuts the pool down so it stops working 
		pool.shutdown();
		 while (!pool.isTerminated()) {
		        try { Thread.sleep(100); } catch (InterruptedException e) {}
		    }

		    // Print final list of points
		    System.out.println("Final list of points: " + cp);
		
	}
}
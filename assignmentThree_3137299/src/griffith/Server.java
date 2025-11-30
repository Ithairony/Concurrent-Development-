/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Server {

	// Declaring Variables 
	private List<Car> carsInStock = Collections.synchronizedList(new ArrayList<>());

	private final ExecutorService threadPool = Executors.newFixedThreadPool(50);
	private Semaphore semaphore = new Semaphore(50);	// Adds a semaphore that limits the user to 50 users 

	public void addCar(Car car) {
		synchronized (carsInStock) {
			carsInStock.add(car);	// Adds a car to the stock 
			System.out.println(car.toString() + " added succesfully.");
		}
	}

	public void sellCar(Car carToSell) {
		synchronized (carsInStock) {
			// First check if car exists in the list and its for sale 
			for (Car car : carsInStock) {
				if ( car.getRegistration().equals(carToSell.getRegistration()) && car.isForSale()) {
					car.setForSale(false); // Turn its for sale to false 
					System.out.println( carToSell.toString() + " sold succesfully.");
					return; // Stops the loop 
				}
			}
		}
	}

	public void calculateTotalValueOfSales() {
		synchronized (carsInStock) {
			
		}
	}
	
	/**
	 * See: https://www.baeldung.com/find-list-element-java
	 * Article explaining ways to find an element in a Java List.
	 */
	public void listCarsForSale() {
		synchronized (carsInStock) {
			System.out.println(" Cars available for sale :");
			for (Car car : carsInStock) {
				if ( car.isForSale()) {
					System.out.println(car.toString());
				}
			}
		}
	}
	
	// Reference for find element in list using streams:
	// https://www.baeldung.com/find-list-element-java
	public void listCarsByMake(String make) {
		synchronized (carsInStock) {
			carsInStock.stream()
					.filter(car -> car.getMake().equalsIgnoreCase(make))
					.forEach(System.out::println);
		}
	}
}

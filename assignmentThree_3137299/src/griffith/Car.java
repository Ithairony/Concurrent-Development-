/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Car {
	
	// Car variables 
	private String registration;
	private String make;
	private double price;
	private int mileage;
	private boolean forSale;
	
	// Constructor 
	public Car(String registration, String make, double price, int mileage, boolean forSale) {
		this.registration = registration;
		this.make = make;
		this.price = price;
		this.mileage = mileage;
		this.forSale = forSale;
	}

	// Getter and setters
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public boolean isForSale() {
		return forSale;
	}
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "Car [registration=" + registration + ", make=" + make + ", price=" + price + ", mileage=" + mileage
				+ ", forSale=" + forSale + "]";
	}
	
}

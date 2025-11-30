/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */
package griffith;

public class AddCarRequest implements Request {
	
	private Car car;
	
	public AddCarRequest(Car car) {
		this.car = car;
	}
	
	@Override
	public void processRequest(Server server) {
		server.addCar(car);
	}
}
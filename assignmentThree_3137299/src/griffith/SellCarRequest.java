/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */
package griffith;

public class SellCarRequest implements Request {
	
	private final String registration;
	
	public SellCarRequest(String registration) {
		this.registration = registration;
	}
	@Override
	public void processRequest(Server server) {
		server.sellCar(registration);
		
	}
	
}
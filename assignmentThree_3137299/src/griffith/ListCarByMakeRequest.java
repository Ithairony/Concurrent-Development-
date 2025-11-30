/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class ListCarByMakeRequest implements Request {
	private final String make;

	public ListCarByMakeRequest(String make) {
		this.make = make;
	}

	@Override
	public void processRequest(Server server) {
		server.listCarsByMake(make);
	}

}
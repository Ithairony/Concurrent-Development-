/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Client {
	public static void main(String[] args) {

		Server server = new Server();

		// Adding cars to the server 
		server.submitRequest(new AddCarRequest(new Car("16L1234", "Ferrari", 120000, 1000, true)));
		server.submitRequest(new AddCarRequest(new Car("01LH1234", "Ford", 1000, 1000, true)));
		server.submitRequest(new AddCarRequest(new Car("02D1234", "Porsche", 11000, 2000, true)));
		server.submitRequest(new AddCarRequest(new Car("03WW1234", "Ford", 12000, 3000, true)));
		server.submitRequest(new AddCarRequest(new Car("05KK1234", "Tesla", 14000, 5000, true)));
		server.submitRequest(new AddCarRequest(new Car("06CW1234", "Ford", 15000, 6000, true)));
		server.submitRequest(new AddCarRequest(new Car("07LS1234", "Mercedes", 16000, 7000, true)));
		server.submitRequest(new AddCarRequest(new Car("08KE1234", "Ford", 17000, 8000, true)));
		server.submitRequest(new AddCarRequest(new Car("10WM1234", "Toyota", 19000, 10000, true)));
		server.submitRequest(new AddCarRequest(new Car("11M1234", "Toyota", 20000, 11000, true)));
		server.submitRequest(new AddCarRequest(new Car("12T1234", "Honda", 18000, 9000, true)));
		server.submitRequest(new AddCarRequest(new Car("13Y1234", "Toyota", 15000, 8000, true)));
		server.submitRequest(new AddCarRequest(new Car("14H1234", "Toyota", 30000, 15000, true)));
		server.submitRequest(new AddCarRequest(new Car("15CE1234", "Mitsubishi", 24000, 15000, true)));
		server.submitRequest(new AddCarRequest(new Car("17C1234", "Nissan", 22000, 13000, true)));

		// The thread needs to sleep for a while so that it has time for all addRequests to execute 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Send Request to display cars for sale 
		server.submitRequest(new ListCarRequest());

		// The thread needs to sleep for a while so that it has time for all addRequests to execute 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		server.submitRequest(new ListCarByMakeRequest("Ferrari"));
		server.submitRequest(new ListCarByMakeRequest("Ford"));

		// The thread needs to sleep for a while so that it has time for all addRequests to execute 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nSelling car: 15CE1234");
		server.submitRequest(new SellCarRequest("15CE1234"));
		
		// The thread needs to sleep for a while so that it has time for all addRequests to execute 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Send Request to display cars for sale 
		server.submitRequest(new ListCarRequest());
		server.submitRequest(new TotalValueOfSalesRequest());
	}

}

/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */
package griffith;

public class TotalValueOfSalesRequest implements Request {
	
	  @Override
	    public void processRequest(Server server) {
	        server.calculateTotalValueOfSales();
	    }
}
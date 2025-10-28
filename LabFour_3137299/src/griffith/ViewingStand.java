/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

import java.util.LinkedHashSet;
import java.util.Set;

public class ViewingStand {

	private final Set<Integer> availableSeats;

	// Constructor 
	public ViewingStand(int numSeats) {
		// Declaring a LinkedHashSet to store the available sits (Prevents duplicates and keeps insertion order ) 
		availableSeats = new LinkedHashSet<>();
		// Iterate adding the seat number to the available Seats Hash Set 
		for (int i = 0; i < numSeats; i++) {
			availableSeats.add(i); // Adds a seat number to the HashSet available Seats according to the given number of seats
		}
	}

	public synchronized int findSeat(int attendeeNumber) {
		// Checks if there is seats 
		if (!availableSeats.isEmpty()) {
			// Gets the first available seat
			int seat = availableSeats.iterator().next();
			availableSeats.remove(seat);
			System.out.println(" Attendee : " + attendeeNumber + " Took Seat Number : " + seat);
			return seat;
		}	else {
			return -1;
		}

	}

	public synchronized void leaveSeat(int seat, int attendeeNumber) {
		availableSeats.add(seat); // Makes the seat available again 
		System.out.println(" Attendee : " + attendeeNumber + " left seat :" + seat);
	}

	public Set<Integer> getAvailableSeats() {
		return availableSeats;
	}	

	public static void main(String[] args) {
		// Testing the class 
		ViewingStand stand = new ViewingStand(10); // 10 seats, for example

		int numberOfAttendees = 100;

		for (int i = 1; i <= numberOfAttendees; i++) {
			new Attendee( i, stand).start();
		}
	}
}

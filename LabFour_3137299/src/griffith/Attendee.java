/* 
 * Student Name: Igor Thairony Oliveira Martins
 * Student Number: 3137299
 */

package griffith;

public class Attendee extends Thread {

	private final int attendeeNumber;
	private final ViewingStand stand;

	public Attendee(int i, ViewingStand stand) {
		this.attendeeNumber = i;
		this.stand = stand;
	}
	@Override
	public void run() {
		
		while (true) {
			int seat = stand.findSeat(attendeeNumber);

			if (seat != -1) {
				// Simulate viewing time (1 to 3 seconds)
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Leave the seat after viewing
				stand.leaveSeat(seat, attendeeNumber);
				break; // Done viewing, exit the loop
			} else {
				// If there is no seats available, wait 1s before trying again
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getAttendeeName() {
		return attendeeNumber;
	}
	public ViewingStand getStand() {
		return stand;
	}



}
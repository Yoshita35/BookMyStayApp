import java.util.LinkedList;
import java.util.Queue;

/**
 * Reservation class represents a guest's booking request.
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

/**
 * BookingRequestQueue manages incoming reservation requests using FIFO.
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /**
     * Add a booking request to the queue
     */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    /**
     * Display all pending requests
     */
    public void displayQueue() {

        System.out.println("\nCurrent Booking Request Queue:");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

/**
 * BookMyStayApp – Application Entry Point
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStayApp\n");

        // Initialize booking request queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submitting booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue (FIFO order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("\nRequests stored in arrival order.");
        System.out.println("No room allocation performed at this stage.");
    }
}
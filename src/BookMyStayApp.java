import java.util.*;

/**
 * Reservation represents a guest booking request
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
}

/**
 * InventoryService manages room availability
 */
class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

/**
 * BookingService processes reservations and assigns rooms
 */
class BookingService {

    private InventoryService inventoryService;

    // Map room type → allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    // Global set of all assigned room IDs
    private Set<String> allAssignedRoomIds;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        allocatedRooms = new HashMap<>();
        allAssignedRoomIds = new HashSet<>();
    }

    /**
     * Process booking requests in FIFO order
     */
    public void processBookings(Queue<Reservation> requestQueue) {

        while (!requestQueue.isEmpty()) {

            Reservation reservation = requestQueue.poll();
            String roomType = reservation.getRoomType();

            System.out.println("\nProcessing booking for: " + reservation.getGuestName());

            int available = inventoryService.getAvailability(roomType);

            if (available > 0) {

                String roomId = generateRoomId(roomType);

                // Ensure uniqueness using Set
                if (!allAssignedRoomIds.contains(roomId)) {

                    allAssignedRoomIds.add(roomId);

                    allocatedRooms
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    // Update inventory immediately
                    inventoryService.decrementRoom(roomType);

                    System.out.println("Booking Confirmed!");
                    System.out.println("Guest: " + reservation.getGuestName());
                    System.out.println("Room Type: " + roomType);
                    System.out.println("Assigned Room ID: " + roomId);

                }

            } else {
                System.out.println("Booking Failed - No rooms available for " + roomType);
            }
        }
    }

    /**
     * Generate unique room ID
     */
    private String generateRoomId(String roomType) {
        String prefix = roomType.substring(0, 2).toUpperCase();
        return prefix + (allAssignedRoomIds.size() + 1);
    }

    /**
     * Display allocated rooms
     */
    public void displayAllocations() {

        System.out.println("\nAllocated Rooms:");

        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {

            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

/**
 * BookMyStayApp - Main Application
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStayApp\n");

        // Initialize services
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        // Create booking request queue
        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.offer(new Reservation("Alice", "Single Room"));
        requestQueue.offer(new Reservation("Bob", "Double Room"));
        requestQueue.offer(new Reservation("Charlie", "Single Room"));
        requestQueue.offer(new Reservation("David", "Suite Room"));
        requestQueue.offer(new Reservation("Emma", "Suite Room")); // should fail

        // Process bookings
        bookingService.processBookings(requestQueue);

        // Show allocations
        bookingService.displayAllocations();

        // Show updated inventory
        inventory.displayInventory();
    }
}
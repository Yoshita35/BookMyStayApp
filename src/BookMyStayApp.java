import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Room class representing common room properties.
 */
abstract class Room {
    protected String type;
    protected double price;
    protected String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price per Night: ₹" + price);
        System.out.println("Amenities: " + amenities);
    }
}

/**
 * Single Room class
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 2000, "1 Bed, Free WiFi, TV");
    }
}

/**
 * Double Room class
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 3500, "2 Beds, Free WiFi, TV, AC");
    }
}

/**
 * Suite Room class
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 6000, "King Bed, WiFi, TV, AC, Mini Bar");
    }
}

/**
 * RoomInventory manages room availability.
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example unavailable room
    }

    // Read-only method to retrieve availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to view full inventory (read-only)
    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

/**
 * SearchService handles read-only room search operations.
 */
class SearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("Available Rooms:\n");

        for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {

            String roomType = entry.getKey();
            int available = entry.getValue();

            // Validation: show only available rooms
            if (available > 0) {

                Room room = null;

                if (roomType.equals("Single Room")) {
                    room = new SingleRoom();
                } else if (roomType.equals("Double Room")) {
                    room = new DoubleRoom();
                } else if (roomType.equals("Suite Room")) {
                    room = new SuiteRoom();
                }

                if (room != null) {
                    room.displayDetails();
                    System.out.println("Available Rooms: " + available);
                    System.out.println("--------------------------");
                }
            }
        }
    }
}

/**
 * BookMyStayApp – Application Entry Point
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStayApp\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Guest searches for available rooms
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory);

        System.out.println("\nSearch completed. System state unchanged.");
    }
}
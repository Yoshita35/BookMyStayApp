import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory
 *
 * This class manages room availability using a centralized HashMap.
 * It acts as the single source of truth for room inventory in the system.
 *
 * @author Yoshita
 * @version 1.0
 */
class RoomInventory {

    // HashMap to store room type and available count
    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes the room inventory.
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        // Registering room types with availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 7);
        inventory.put("Suite Room", 3);
    }

    /**
     * Method to get availability of a specific room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Method to update availability of a room type
     */
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found.");
        }
    }

    /**
     * Method to display current inventory
     */
    public void displayInventory() {
        System.out.println("Current Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}


/**
 * BookMyStayApp
 *
 * Entry point of the application demonstrating centralized
 * room inventory management using HashMap.
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        System.out.println("Welcome to BookMyStayApp\n");

        // Display current inventory
        inventory.displayInventory();

        // Retrieve availability
        System.out.println("\nChecking availability for Double Room:");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        // Update inventory
        System.out.println("\nUpdating availability after booking...");
        inventory.updateAvailability("Double Room", 5);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}
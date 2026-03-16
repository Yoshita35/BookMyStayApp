abstract class Seat {
    protected String category;
    protected int capacity;
    protected double price;

    public Seat(String category, int capacity, double price) {
        this.category = category;
        this.capacity = capacity;
        this.price = price;
    }

    // Method to display seat details
    public void displayDetails() {
        System.out.println("Seat Category: " + category);
        System.out.println("Capacity: " + capacity);
        System.out.println("Price: ₹" + price);
    }
}

// Regular seat class
class RegularSeat extends Seat {
    public RegularSeat() {
        super("Regular", 100, 150.0);
    }
}

// Premium seat class
class PremiumSeat extends Seat {
    public PremiumSeat() {
        super("Premium", 60, 250.0);
    }
}

// VIP seat class
class VIPSeat extends Seat {
    public VIPSeat() {
        super("VIP", 30, 400.0);
    }
}

// Main application class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Creating seat objects (Polymorphism)
        Seat regular = new RegularSeat();
        Seat premium = new PremiumSeat();
        Seat vip = new VIPSeat();

        // Simple availability variables
        int regularAvailable = 75;
        int premiumAvailable = 40;
        int vipAvailable = 20;

        System.out.println("Welcome to BookMyShowApp");
        System.out.println("Available Seat Categories\n");

        System.out.println("---- Regular Seat ----");
        regular.displayDetails();
        System.out.println("Available Seats: " + regularAvailable);

        System.out.println("\n---- Premium Seat ----");
        premium.displayDetails();
        System.out.println("Available Seats: " + premiumAvailable);

        System.out.println("\n---- VIP Seat ----");
        vip.displayDetails();
        System.out.println("Available Seats: " + vipAvailable);

        System.out.println("\nApplication terminated.");
    }
}
import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Destination> itinerary) {
        this.itinerary = itinerary;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("  Activity: " + activity.getName() +
                        ", Cost: " + activity.getCost() +
                        ", Capacity: " + activity.getCapacity() +
                        ", Description: " + activity.getDescription());
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name +
                ", Passenger Capacity: " + passengerCapacity +
                ", Enrolled Passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("  Passenger: " + passenger.getName() +
                    ", Number: " + passenger.getNumber());
        }
    }

    public void printIndividualPassengerDetails(Passenger passenger) {
        System.out.println("Passenger Details: ");
        System.out.println("  Name: " + passenger.getName());
        System.out.println("  Number: " + passenger.getNumber());
        if (passenger instanceof Standard) {
            System.out.println("  Balance: " + ((Standard) passenger).getBalance());
        }
        System.out.println("  Activities:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("    " + activity.getName() +
                    " at " + findDestinationForActivity(activity).getName() +
                    ", Cost: " + activity.getCost());
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                int remainingCapacity = activity.getCapacity() - countEnrolledPassengers(activity);
                if (remainingCapacity > 0) {
                    System.out.println("  Activity: " + activity.getName() +
                            " at " + destination.getName() +
                            ", Remaining Capacity: " + remainingCapacity);
                }
            }
        }
    }

    private int countEnrolledPassengers(Activity activity) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getActivities().contains(activity)) {
                count++;
            }
        }
        return count;
    }

    private Destination findDestinationForActivity(Activity activity) {
        for (Destination destination : itinerary) {
            if (destination.getActivities().contains(activity)) {
                return destination;
            }
        }
        return null;
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class Passenger {
    private String name;
    private int number;
    private List<Activity> activities;

    public Passenger(String name, int number) {
        this.name = name;
        this.number = number;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void signUpForActivity(Activity activity) {
        activities.add(activity);
    }
}

class Standard extends Passenger {
    private double balance;

    public Standard(String name, int number, double balance) {
        super(name, number);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void signUpForActivity(Activity activity) {
        if (balance >= activity.getCost()) {
            activities.add(activity);
            balance -= activity.getCost();
        } else {
            System.out.println("Insufficient balance to sign up for the activity.");
        }
    }
}

class Gold extends Passenger {
    private double balance;

    public Gold(String name, int number, double balance) {
        super(name, number);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void signUpForActivity(Activity activity) {
        double discountedCost = 0.9 * activity.getCost();
        if (balance >= discountedCost) {
            activities.add(activity);
            balance -= discountedCost;
        } else {
            System.out.println("Insufficient balance to sign up for the activity.");
        }
    }
}

class Premium extends Passenger {
    public Premium(String name, int number) {
        super(name, number);
    }

    @Override
    public void signUpForActivity(Activity activity) {
        activities.add(activity);
    }
}

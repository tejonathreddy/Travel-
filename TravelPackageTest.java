import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

public class TravelPackageTest {
    @Test
    public void testAddDestination() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Destination destination = new Destination("Paris");

        travelPackage.addDestination(destination);

        assertEquals(1, travelPackage.getItinerary().size());
        assertEquals(destination, travelPackage.getItinerary().get(0));
    }

    @Test
    public void testAddPassenger() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Passenger passenger = new Passenger("John Doe", 1);

        travelPackage.addPassenger(passenger);

        assertEquals(1, travelPackage.getPassengers().size());
        assertEquals(passenger, travelPackage.getPassengers().get(0));
    }

    @Test
    public void testSignUpForActivityStandard() {
        Standard standardPassenger = new Standard("Alice", 101, 1000.0);
        Activity activity = new Activity("Sightseeing", "City Tour", 50.0, 20);

        standardPassenger.signUpForActivity(activity);

        assertEquals(1, standardPassenger.getActivities().size());
        assertEquals(activity, standardPassenger.getActivities().get(0));
        assertEquals(950.0, standardPassenger.getBalance(), 0.001);
    }

    @Test
    public void testSignUpForActivityGold() {
        Gold goldPassenger = new Gold("Bob", 201, 1500.0);
        Activity activity = new Activity("Adventure", "Hiking", 100.0, 15);

        goldPassenger.signUpForActivity(activity);

        assertEquals(1, goldPassenger.getActivities().size());
        assertEquals(activity, goldPassenger.getActivities().get(0));
        assertEquals(1400.0, goldPassenger.getBalance(), 0.001);
    }

    @Test
    public void testSignUpForActivityPremium() {
        Premium premiumPassenger = new Premium("Charlie", 301);
        Activity activity = new Activity("Relaxation", "Spa Day", 80.0, 10);

        premiumPassenger.signUpForActivity(activity);

        assertEquals(1, premiumPassenger.getActivities().size());
        assertEquals(activity, premiumPassenger.getActivities().get(0));
    }

    @Test
    public void testPrintItinerary() {
        // Implement test cases to check if the itinerary is printed correctly
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Destination destination1 = new Destination("Paris");
        Activity activity1 = new Activity("Sightseeing", "City Tour", 50.0, 20);
        destination1.addActivity(activity1);
        travelPackage.addDestination(destination1);

        Destination destination2 = new Destination("Rome");
        Activity activity2 = new Activity("Historical Tour", "Colosseum Visit", 40.0, 15);
        destination2.addActivity(activity2);
        travelPackage.addDestination(destination2);

        // Redirect standard output to capture printed content
        System.setOut(new java.io.ByteArrayOutputStream());

        travelPackage.printItinerary();

        // Reset standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String printedOutput = new String(((ByteArrayOutputStream) System.out).toByteArray());

        // Add assertions based on the printed output
        assertTrue(printedOutput.contains("Travel Package: Europe Tour"));
        assertTrue(printedOutput.contains("Destination: Paris"));
        assertTrue(printedOutput.contains("Activity: Sightseeing"));
        assertTrue(printedOutput.contains("Cost: 50.0"));
        assertTrue(printedOutput.contains("Capacity: 20"));
        assertTrue(printedOutput.contains("Description: City Tour"));
        assertTrue(printedOutput.contains("Destination: Rome"));
        assertTrue(printedOutput.contains("Activity: Historical Tour"));
        assertTrue(printedOutput.contains("Cost: 40.0"));
        assertTrue(printedOutput.contains("Capacity: 15"));
        assertTrue(printedOutput.contains("Description: Colosseum Visit"));
    }

    @Test
    public void testPrintPassengerList() {
        // Implement test cases to check if the passenger list is printed correctly
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Passenger passenger1 = new Passenger("John Doe", 1);
        travelPackage.addPassenger(passenger1);

        Passenger passenger2 = new Passenger("Jane Doe", 2);
        travelPackage.addPassenger(passenger2);

        // Redirect standard output to capture printed content
        System.setOut(new java.io.ByteArrayOutputStream());

        travelPackage.printPassengerList();

        // Reset standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String printedOutput = new String(System.out.toByteArray());

        // Add assertions based on the printed output
        assertTrue(printedOutput.contains("Travel Package: Europe Tour"));
        assertTrue(printedOutput.contains("Passenger Capacity: 50"));
        assertTrue(printedOutput.contains("Enrolled Passengers: 2"));
        assertTrue(printedOutput.contains("Passenger: John Doe"));
        assertTrue(printedOutput.contains("Number: 1"));
        assertTrue(printedOutput.contains("Passenger: Jane Doe"));
        assertTrue(printedOutput.contains("Number: 2"));
    }

    @Test
    public void testPrintIndividualPassengerDetails() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Passenger passenger1 = new Passenger("John Doe", 1);
        travelPackage.addPassenger(passenger1);

        Passenger passenger2 = new Passenger("Jane Doe", 2);
        travelPackage.addPassenger(passenger2);

        // Redirect standard output to capture printed content
        System.setOut(new java.io.ByteArrayOutputStream());

        travelPackage.printPassengerList();

        // Reset standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String printedOutput = new String(System.out.toByteArray());

        assertTrue(printedOutput.contains("Travel Package: Europe Tour"));
        assertTrue(printedOutput.contains("Passenger Capacity: 50"));
        assertTrue(printedOutput.contains("Enrolled Passengers: 2"));
        assertTrue(printedOutput.contains("Passenger: John Doe"));
        assertTrue(printedOutput.contains("Number: 1"));
        assertTrue(printedOutput.contains("Passenger: Jane Doe"));
        assertTrue(printedOutput.contains("Number: 2"));
    }

    @Test
    public void testPrintAvailableActivities() {
        // Implement test cases to check if available activities are printed correctly
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Standard standardPassenger = new Standard("Alice", 101, 1000.0);
        Activity activity = new Activity("Sightseeing", "City Tour", 50.0, 20);
        standardPassenger.signUpForActivity(activity);
        travelPackage.addPassenger(standardPassenger);

        // Redirect standard output to capture printed content
        System.setOut(new java.io.ByteArrayOutputStream());

        travelPackage.printIndividualPassengerDetails(standardPassenger);

        // Reset standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String printedOutput = new String(System.out.toByteArray());

        // Add assertions based on the printed output
        assertTrue(printedOutput.contains("Passenger Details:"));
        assertTrue(printedOutput.contains("Name: Alice"));
        assertTrue(printedOutput.contains("Number: 101"));
        assertTrue(printedOutput.contains("Balance: 950.0"));
        assertTrue(printedOutput.contains("Activities:"));
        assertTrue(printedOutput.contains("Sightseeing at Europe Tour, Cost: 50.0"));
    }

    @Test
    public void testPrintAvailableActivities() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 50);
        Destination destination = new Destination("Paris");
        Activity activity1 = new Activity("Sightseeing", "City Tour", 50.0, 20);
        Activity activity2 = new Activity("Shopping", "Local Market Visit", 30.0, 10);
        destination.addActivity(activity1);
        destination.addActivity(activity2);
        travelPackage.addDestination(destination);

        // Redirect standard output to capture printed content
        System.setOut(new java.io.ByteArrayOutputStream());

        travelPackage.printAvailableActivities();

        // Reset standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String printedOutput = new String(System.out.toByteArray());

        // Add assertions based on the printed output
        assertTrue(printedOutput.contains("Available Activities:"));
        assertTrue(printedOutput.contains("Activity: Sightseeing at Paris, Remaining Capacity: 20"));
        assertTrue(printedOutput.contains("Activity: Shopping at Paris, Remaining Capacity: 10"));
    }
}

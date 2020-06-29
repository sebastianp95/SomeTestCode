import org.junit.Test;

public class MainTest {
    @Test
    public void vehicleTest() {
        Vehicle corolla = new Vehicle("Toyota", "Corolla", 2014, false, 2000, 25);
        corolla.printVehicle();
        Car ferrari = new Car("Ferrari", "California", 2014, true, 2000, 25, true);
        ferrari.printVehicle();
        Truck cyberTruck = new Truck("Tesla", "CyberTuck", 2025, false, 50000, 100, true, 5000);
        cyberTruck.printVehicle();
    }

}

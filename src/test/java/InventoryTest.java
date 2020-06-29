import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp(){
        inventory = new Inventory();
        inventory.add(new Vehicle("Nissan", "sentra", 2018, false,5000,24));
        inventory.add(new Vehicle("Toyota", "corolla", 2010, false,10000,24));
        inventory.add(new Car("Ferrari", "California", 2014, true, 200, 25, true));

    }

    @Test
    public void testConstructor(){
       Vehicle v = new Vehicle("a","b", 1, false,1,1);

    }
    @Test
    public void testAdd(){
        Vehicle sentra = new Vehicle("Nissan", "sentra", 2018, false,1000,24);
        Car ferrari = new Car("Ferrari", "California", 2014, true, 2000, 25, true);
        Truck cyberTruck = new Truck("Tesla", "CyberTuck", 2025, false, 50000, 100, true, 5000);
        Inventory inventory = new Inventory();
        inventory.add(sentra);
        inventory.add(ferrari);
        inventory.add(cyberTruck);
        Assert.assertEquals(3, inventory.size());
    }
    @Test
    public void testRemove(){
        Vehicle sentra = new Vehicle("Nissan", "sentra", 2018, false,1000,24);
        Car ferrari = new Car("Ferrari", "California", 2014, true, 2000, 25, true);
        Truck cyberTruck = new Truck("Tesla", "CyberTuck", 2025, false, 50000, 100, true, 5000);
        Inventory inventory = new Inventory();
        inventory.add(sentra);
        inventory.add(ferrari);
        inventory.add(cyberTruck);
        inventory.remove(sentra);
        Assert.assertEquals(2, inventory.size());
        inventory.remove(ferrari);
        Assert.assertEquals(1, inventory.size());
        inventory.remove(cyberTruck);
        Assert.assertEquals(0, inventory.size());

    }
    @Test
    public void findCheapestVehicle(){
        Vehicle sentra = new Vehicle("Nissan", "sentra", 2018, false,1000,24);
        Car ferrari = new Car("Ferrari", "California", 2014, true, 2000, 25, true);
        Truck cyberTruck = new Truck("Tesla", "CyberTuck", 2025, false, 50000, 100, true, 5000);
        Inventory inventory = new Inventory();
        inventory.add(sentra);
        inventory.add(ferrari);
        inventory.add(cyberTruck);
        Vehicle cheapest= inventory.findCheapestVehicle();
        Assert.assertEquals(sentra.getMake(), cheapest.getMake());
    }
    @Test
    public void findMostExpensiveVehicle(){
        Vehicle sentra = new Vehicle("Nissan", "sentra", 2018, false,1000,24);
        Car ferrari = new Car("Ferrari", "California", 2014, true, 2000, 25, true);
        Truck cyberTruck = new Truck("Tesla", "CyberTuck", 2025, false, 50000, 100, true, 5000);
        Inventory inventory = new Inventory();
        inventory.add(sentra);
        inventory.add(ferrari);
        inventory.add(cyberTruck);
        Vehicle mostExpensive= inventory.findMostExpensiveVehicle();
        Assert.assertEquals(cyberTruck.getMake(), mostExpensive.getMake());
    }

    @Test(expected = InventoryException.class)
    public void testEmptyInventory(){
        Inventory inv = new Inventory();
//        inv.add(new Vehicle("Nissan", "sentra", 2018, false,1000,24));
        inv.findCheapestVehicle();
    }
    @Test
    public void testAveragePrice (){
        Vehicle v = new Vehicle("a","b",1,false,200,1);
        Car c= new Car("b","b",1,false,1000,1,false);
        Truck t=new Truck("c","b",1,false,300,1,false,1);
        Inventory inventory = new Inventory();
        inventory.add(v);
        inventory.add(c);
        inventory.add(t);
        Assert.assertEquals(100,inventory.getAveragePriceOfAllVehicles(),0.1);

    }
}

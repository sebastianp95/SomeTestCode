import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InventoryExceptionTest {
    @Test(expected = VehicleTooCheapException.class)
    public void testTooCheap(){
        Inventory inv = new Inventory();
        Vehicle veh= new Vehicle("toyota", "Camry", 2018, false, 4999,30);
        inv.add(veh);

    }
    @Test(expected = VehicleTooOldException.class)
    public void testTooOld(){
        Inventory inv = new Inventory();
        Vehicle veh= new Vehicle("Nissan", "Sentra", 1999, false, 4999,30);
        inv.add(veh);
    }
    @Test
    public void testYear(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        Assert.assertEquals(2020, year);
    }
}

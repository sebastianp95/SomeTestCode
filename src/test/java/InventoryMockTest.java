import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class InventoryMockTest {
    private InventoryInterface inv= Mockito.mock(InventoryInterface.class);
    @Test
    public void testCheapest(){
        Vehicle v = new Vehicle("Honda", "Civic", 2005, false, 6000.0, 20);

        Mockito.when(inv.findCheapestVehicle()).thenReturn(v);
        Assert.assertEquals("Honda Civic", inv.findCheapestVehicle().getMake() + ' ' + inv.findCheapestVehicle().getModel());
    }

    @Test
    public void testMostExpensive(){
        Vehicle v = new Vehicle("Bugatti", "Veron", 2020, false, 99999.9, 24);
        Mockito.when(inv.findMostExpensiveVehicle()).thenReturn(v);
        Assert.assertEquals("Bugatti Veron", inv.findMostExpensiveVehicle().getMake() + ' ' + inv.findMostExpensiveVehicle().getModel());
    }

    @Test
    public void testAverage(){
        Mockito.when(inv.getAveragePriceOfAllVehicles()).thenReturn(90000.0);
        Assert.assertEquals(90000.0, inv.getAveragePriceOfAllVehicles(),0.1);
    }
}

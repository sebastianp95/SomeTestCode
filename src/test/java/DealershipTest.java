import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DealershipTest {

    private Dealership dealership;
    private Inventory inventory;
    private static Connection connection;

    @BeforeClass
    public static void setUpDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DealershipTest() {
    }

    @Before
    public void setUp() throws Exception {
        dealership = new Dealership();

    }

    @Test
    public void findCheapestVehicle() {

        Assert.assertEquals("Genesis", dealership.findCheapestVehicle());
    }

    @Test
    public void findMostExpensiveVehicle() {

        Assert.assertEquals("Bugatti", dealership.findMostExpensiveVehicle());
    }

    @Test
    public void testAveragePrice() {
        Inventory inv = dealership.getInventory();
        Assert.assertEquals(495666, inv.getAveragePriceOfAllVehicles(), 1);

    }

    @Test
    public void testStoreJSON() {
        try {
            dealership.storeInventoryIntoJSONFile("vehicles.json");
            File file = new File("vehicles.json");
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            Assert.assertEquals(9, lines.size());
        } catch (Exception e) {
        }
    }

    @Test
    public void testLoadJSON() {
        try {
            dealership.loadInventoryFromJSONFile("vehicles.json");
            Assert.assertEquals(9, dealership.getInventory().size());
        } catch (Exception e) {
        }
    }


    private int getTotalRows(Connection c) throws Exception {
        Statement s = connection.createStatement();
        ResultSet set = s.executeQuery("select count(*) from vehicles");
        int rows = set.getInt("count(*)");

        return rows;

    }

    @Test
    public void testAddVehicleToDB() {
        Vehicle v = new Vehicle("test", "test", 2020, true, 2000, 30);
        try {
            int before = getTotalRows(connection);
            dealership.addVehicleToDatabase(connection, v);
            int after = getTotalRows(connection);
            Assert.assertEquals(before + 1, after);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateVehicleToDB() {
        Vehicle v = new Vehicle("HondaUpdate", "civic", 2019, false, 250000, 35);
        try {
            Statement s = connection.createStatement();
            Statement s2 = connection.createStatement();
            int randomId = RandomUtils.nextInt(1, 7);
            ResultSet set = s.executeQuery("select * from vehicles WHERE id = " + randomId + ";");

            int id = 0;

            id = set.getInt("id");
            String make = set.getString("make");
            if (id != 0) {

                System.out.println("Random vehicle id: "+id+" make: "+ make);
                Vehicle vnew = dealership.updateVehicleInDatabase(connection, v, id);
                ResultSet set2 = s2.executeQuery("select * from vehicles WHERE id = " + id + ";");
                make = set2.getString("make");
            }
            s.close();
            System.out.println("Random vehicle "+ make);
            Assert.assertEquals("HondaUpdate", make);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanUp() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

import org.junit.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {

    private static Connection c;
    private static String URL = "jdbc:sqlite:vehicles.db";
    private static Dealership dealership;

    @BeforeClass
    public static void connectDatabase() throws Exception {
        c = DriverManager.getConnection(URL);
        dealership = new Dealership();
    }

    public int countRows() {
        int count = 0;
        try {
            Statement s = c.createStatement();
            ResultSet set =
                    s.executeQuery("select count(*) from vehicles");
            count = set.getInt("count(*)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    @Test
    public void testAddVehicleToDB(){
        int current=countRows();
        Vehicle v = new Vehicle("Subaru","Forester",2020,true, 30000,20);
        dealership.addVehicleToDatabase(c,v);
        Assert.assertEquals(current+1,countRows());
    }

    @Ignore
    @Test
    public void testTable() throws Exception {
        Statement st = c.createStatement();
        String sql = " select * from vehicles";
        ResultSet set = st.executeQuery(sql);
        Assert.assertNotNull(set);
        while (set.next()) {
            System.out.println(set.getString("make"));
            System.out.println(set.getString("price"));
        }
        ResultSet set2 = st.executeQuery("select count(*) from vehicles");
        set2.next();
        Assert.assertEquals(4, set2.getInt("count(*)"));
        st.close();
    }

    @AfterClass
    public static void disconnectDatabase() throws Exception {
        if (c != null) {
            c.close();
        }
    }
}

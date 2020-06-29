import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PellGrantTest {

    private static Connection c;
    private static String URL = "jdbc:sqlite:institution.db";
    private static PellGrant pellGrant;
    File file = new File("pell-answers.txt");

    @BeforeClass
    public static void connectDatabase() throws Exception {
        c = DriverManager.getConnection(URL);
        pellGrant = new PellGrant();


    }

    @Test
    public void download() throws Exception {

        new PellGrant();
    }

    @Test
    public void testTable() throws Exception {
        Statement st = c.createStatement();
        String sql = " select * from ins";
        ResultSet set = st.executeQuery(sql);
        Assert.assertNotNull(set);
        while (set.next()) {
            System.out.println(set.getString("id"));
            System.out.println(set.getString("name"));
        }
//        ResultSet set2 = st.executeQuery("select count(*) from vehicles");
//        set2.next();
//        Assert.assertEquals(4, set2.getInt("count(*)"));
        st.close();
    }

    @Test
    public void highest() throws Exception {
        Statement st = c.createStatement();
        String sql = " select name, averageAmount from ins order by averageAmount desc LIMIT 10";
        ResultSet set = st.executeQuery(sql);


        FileUtils.writeStringToFile(file, "Highest\n", "UTF-8", true);

        while (set.next()) {
            FileUtils.writeStringToFile(file, set.getString("name") + "\nAverage award: " + set.getString("averageAmount") + "\n", "UTF-8", true);
        }
        Assert.assertNotNull(set);
        st.close();
    }

    @Test
    public void lowest() throws Exception {
        Statement st = c.createStatement();
        String sql = " select name, averageAmount from ins order by averageAmount  LIMIT 10";
        ResultSet set = st.executeQuery(sql);

        FileUtils.writeStringToFile(file, "\nLowest\n", "UTF-8", true);
        while (set.next()) {
            FileUtils.writeStringToFile(file, set.getString("name") + "\nAverage award: " + set.getString("averageAmount") + "\n", "UTF-8", true);
        }
        Assert.assertNotNull(set);

        st.close();
    }

    @Test
    public void mostRecipients() throws Exception {
        Statement st = c.createStatement();
        String sql = " select name, numStudents from ins order by numStudents desc  LIMIT 10";
        ResultSet set = st.executeQuery(sql);

        FileUtils.writeStringToFile(file, "\nMost Recipients\n", "UTF-8", true);
        while (set.next()) {
            FileUtils.writeStringToFile(file, set.getString("name") + "\nRecipients: " + set.getString("numStudents") + "\n", "UTF-8", true);
        }
        Assert.assertNotNull(set);
        st.close();
    }

    public int countRows() {
        int count = 0;
        try {
            Statement s = c.createStatement();
            ResultSet set =
                    s.executeQuery("select count(*) from ins");
            count = set.getInt("count(*)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Test
    public void testAddInstitutionToDB() {
        pellGrant.addInstitutionToDatabase(c);
        Assert.assertEquals(5158, countRows());
    }

    @AfterClass
    public static void disconnectDatabase() throws Exception {
        if (c != null) {
            c.close();
        }
    }

}
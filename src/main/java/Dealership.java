import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Dealership {

    Inventory inventory = new Inventory();

    private static final String INVENTORY_URL = "https://goo.gl/phaEbQ";

    public Dealership() {
        inventory = new Inventory();
        loadInventoryFromWeb(INVENTORY_URL);

    }

    public void addVehicleToDatabase(Connection c, Vehicle v) {
        try {
            Statement s = c.createStatement();
            String query = "insert into vehicles values (null, " +
                    "\"" + v.getMake() + "\"" + "," +
                    "\"" + v.getModel() + "\"" + "," +
                    v.getYear() + "," +
                    v.isFourWheel() + ","
                    + v.getMpg() + ");";
            s.executeUpdate(query);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadInventoryFromWeb(String url) {

        try {
            URL url1 = new URL(url);
            InputStream is = url1.openStream();
            String str = IOUtils.toString(is, "UTF-8");
            Scanner sc = new Scanner(str);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.substring(1, line.length() - 1);
                String[] words = line.split(",");
                String[] makeModel = words[0].split(" ", 2);
                String make = makeModel[0];
                String model = makeModel[1];
                int year = Integer.parseInt(words[1]);
                double price = Double.parseDouble(words[2]);
                boolean isFourWheel = words[3].equals("TRUE") ? true : false;
                Vehicle v = new Vehicle(make, model, year, isFourWheel, price, 0);
                inventory.add(v);
//                v.printVehicle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String findCheapestVehicle() {

        Vehicle veh = inventory.findCheapestVehicle();

        return veh.getMake();
    }

    public String findMostExpensiveVehicle() {

        Vehicle veh = inventory.findMostExpensiveVehicle();

        return veh.getMake();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void loadInventoryFromJSONFile(String filename) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> lines = FileUtils.readLines(new File(filename), "UTF-8");
        inventory = new Inventory();
        for (String line : lines) {
            Vehicle v = mapper.readValue(line, Vehicle.class);
            inventory.add(v);
        }
    }

    public void storeInventoryIntoJSONFile(String filename) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);
        FileUtils.deleteQuietly(new File("vehicles.json"));
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle temp = inventory.get(i);
            String str = mapper.writeValueAsString(temp);
            FileUtils.writeStringToFile(file, str + "\n", "UTF-8", true);
        }
    }

    public Vehicle updateVehicleInDatabase(Connection c, Vehicle v, int id) throws Exception {

        Statement s = c.createStatement();
        Statement s2 = c.createStatement();
        String sql = "update vehicles" +
                "\nset make = '" + v.getMake() + "', " +
                "model = '" + v.getModel() + "'," +
                "year = '" + v.getYear() + "',"+
                "price = '" + v.getPrice() + "',"+
                "mpg = '" + v.getMpg() + "'"

                + "\nWHERE id = "+ id+";";
        s.executeUpdate(sql);
        String sql2 ="select * from vehicles WHERE id = "+ id+";";
        ResultSet set = s2.executeQuery(sql2);
        int idnew=set.getInt("id");
        String make = set.getString("make");
        String model =set.getString("model");;
        int year= set.getInt("year");
        double price=set.getDouble("price");
        int mpg= set.getInt("mpg");
        Vehicle vnew = new Vehicle(make, model, year, false, price, mpg);
        s.close();
        s2.close();
        return vnew;
    }

      public void serializeObj(Vehicle myVehicle) throws IOException {

            File file = FileUtils.getFile("./", "inventory2.obj");

           byte[] data = SerializationUtils.serialize((Serializable) myVehicle);

           FileUtils.writeByteArrayToFile(file, data);

          }



      public String deserializeObj() throws IOException {

           File file = FileUtils.getFile("./", "inventory2.obj");

            byte[] dataToDeserialize = FileUtils.readFileToByteArray(file);

          String deserializedObj = SerializationUtils.deserialize(dataToDeserialize);

           return deserializedObj;

         }
}

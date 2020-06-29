import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JSONParseExample {

    @Test
    public void serialization() throws Exception {
        City paris = new City(42.3, 56.2, 80);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(paris);
        cities.add(paris);
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("paris.json");
        mapper.writeValue(file, cities);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void deserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("paris.json");

        City city = mapper.readValue(file, City.class);
        System.out.println(city);
    }

    @Test
    public void deserializeTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("paris.json");
        City city = mapper.readValue(file, City.class);
        System.out.println(city);
    }
    @Test
    public void readJSONTest () throws Exception{
        File file = new File("cities.json");
        ObjectMapper mapper= new ObjectMapper();
        List<String> lines = FileUtils.readLines(file, "UTF-8");
//        Inventory inv= new Inventory();
        ArrayList<City> citites = new ArrayList<City>();
        for ( String line : lines ) {
            City temp = mapper.readValue(line, City.class);
            citites.add(temp);
        }
        Assert.assertEquals(3, citites.size());
    }

    @Test
    public void storeJSONTest() throws Exception {
        City paris = new City(12.2, 12.23, 70.540);
        City atlanta = new City(24.1,56.2,10.7);
        City singapore = new City(78.23,12.3,90.3);
        List<City> cities = new ArrayList<>();
        cities.add(paris);
        cities.add(atlanta);
        cities.add(singapore);
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(cities));
        File file = new File("cities.json");
        for (int i = 0; i < cities.size(); i++) {
            String str = mapper.writeValueAsString(cities.get(i));
            FileUtils.writeStringToFile(file, str +"\n", "UTF-8", true);
        }
    }

    @Test
    public void getTempTest() throws Exception {
        URL url = new URL("https://api.darksky.net/forecast/ac3fe411198e681a8d9c06a88859dc2a/37.8267,-122.4233");
        InputStream is = url.openStream();
        String str = IOUtils.toString(is, "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        MapType type = mapper.getTypeFactory().constructMapType(
                Map.class, String.class, Object.class);

        Map<String, Object> map = mapper.readValue(str, type);
        Assert.assertTrue((double) map.get("latitude") > 0);
        System.out.println(map.get("longitude"));
        Map<String, Object> currently = (Map<String, Object>) map.get("currently");
        System.out.println(currently.get("temperature"));

    }
}

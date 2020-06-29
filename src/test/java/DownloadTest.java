import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DownloadTest {


    @Test
    public void complexDownload() throws IOException {

        URL url = new URL("http://www.ggc.edu");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = null;

    }

    @Test
    public void simpleDownload() throws Exception {
        URL url = new URL("https://www.cnn.com");
        InputStream is = url.openStream();
        String str = IOUtils.toString(is, "UTF-8");
        System.out.println(str);
        Assert.assertNotNull(str);
    }





    @Test
    public void downloadCSV() throws Exception {
        URL url = new URL("https://goo.gl/phaEbQ");
        InputStream is = url.openStream();
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
            v.printVehicle();


        }


    }

}

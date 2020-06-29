import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PellGrant {
    private static final String INSTITUTIONS_URL = "https://gist.githubusercontent.com/tacksoo/b32d630c2e5c7dcd8ebeb2fc67e9c7ae/raw/72b6bae956dfb3eeb66fa277f63ce8acb784fd01/pell.tsv";
    public ArrayList<Institution> iList;

    public PellGrant() {
        this.iList = new ArrayList<Institution>();
        loadInventoryFromWeb(INSTITUTIONS_URL);
    }
    public void loadInventoryFromWeb(String link) {
        try {
            URL url = new URL(link);
            InputStream is = url.openStream();
            String str = IOUtils.toString(is, "UTF-8");
            Scanner sc = new Scanner(str);
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("ins.json");
            for (int i = 0; i < 5; i++) {
                sc.nextLine();
            }

            TreeMap<String, ArrayList> map = new TreeMap<>();


            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split("\t");


                if (words.length < 7)
                    continue;

                if (sc.hasNextInt()==false)
                    sc.nextLine();


                int id = Integer.parseInt(words[0]);
                String name = words[1];
                name = name.replaceAll("\"", "");
                String city = words[2];
                city = city.replaceAll("\"", "");
                String state = words[3];
                ArrayList<String> st = new ArrayList<String>();
                st.add(state);
                String type = words[4];
                String totalGrant = words[5];
                totalGrant = totalGrant.replaceAll("\"", "");
                totalGrant = totalGrant.replace("$", "");
                totalGrant = totalGrant.replaceAll(",", "");

                if (totalGrant.contains("."))
                    totalGrant = totalGrant.substring(0, totalGrant.indexOf("."));
                if (totalGrant.contains("\"\""))
                    totalGrant = totalGrant.replace("\"\"", "");
                String numStudents = words[6];
                numStudents = numStudents.replaceAll("\"", "");
                numStudents = numStudents.replaceAll(",", "");

                if (totalGrant.contains(".")) { // NEEDS WORK////
                    totalGrant = totalGrant.replace(".0", "");
                }
                int TGrant = Integer.parseInt(totalGrant);
                int students = Integer.parseInt(numStudents);
                double averageAmount = 0;
                if (students != 0)
                    averageAmount = TGrant / students;
                Institution i = new Institution(id, name, city, state, type, TGrant, students, averageAmount);
                iList.add(i);
                map.put(i.getState(), iList);
            }
            Collections.sort(iList, Comparator.comparing(Institution::getState));

            Map<String, List<Institution>> m = iList.stream()
                    .collect(Collectors.groupingBy(Institution::getState, TreeMap::new, Collectors.toList()));
            String e = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(m);

            FileUtils.writeStringToFile(file, e + "\n", "UTF-8", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addInstitutionToDatabase(Connection c){

        try{
        for (Institution v : iList) {

            Statement s = c.createStatement();
            String query="insert into ins values ("+v.getId()+","+
                    "\""+v.getName()+"\""+","+
                    "\""+v.getCity()+"\""+","+
                    "\""+v.getState()+"\""+","+
                    "\""+v.getType()+"\""+","+
                    v.getTotalGrant()+","+
                    v.getNumStudents()+","+
                    v.getAverageAmount()+");";
            s.executeUpdate(query);
            s.close();
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    public void loadInventory(String filename) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        List<String> lines = FileUtils.readLines(new File(filename), "UTF-8");
//        PellGrant pellGrant = new PellGrant();
//        for (String line : lines) {
//            Institution v = mapper.readValue(line, Institution.class);
//            iList.add(v);
//        }
//    }
}
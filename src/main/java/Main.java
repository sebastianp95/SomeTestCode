import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Vehicle newVeh= new Car("toyota", "corolla", 2020, false, 20000, 30, false);

        Set<String> names = new HashSet<String>();

        ObjectMapper mapper = new ObjectMapper();

        String str = mapper.writeValueAsString(newVeh);
        System.out.println(str);

        Map<String, Vehicle> map= new HashMap<String, Vehicle>();
        map.put("dr. Im", newVeh);
        map.put("Trump", new Car("Big car", "1", 2020, true,100000, 40, true));
        System.out.println(mapper.writeValueAsString(map));

    }
}

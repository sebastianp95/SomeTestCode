import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class SystemTest {
    @Test
    public void firstTest(){
        Assert.assertEquals("1 should be 1" ,1,1);
    }
    @Test
    public void fileTest() throws Exception{
        File file= new File("docs/readme.txt");
        List<String> lines = FileUtils.readLines(file , "UTF-8");
        Assert.assertEquals("Should be 4 lines" ,4,lines.size());
    }
}

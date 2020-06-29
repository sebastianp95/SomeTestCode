import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {


    private CacheManager cache = Mockito.mock(CacheManager.class);
    private DiskManager disk = Mockito.mock(DiskManager.class);

    @Test
    public void neitherOne() throws NullPointerException{

        Person person = new Person(cache, disk);

        person.setPerson(6789, "Benjamin","Graham");


        Mockito.when(disk.getPerson(6789)).thenReturn(null);
        Mockito.when(cache.getPerson(6789)).thenReturn(null);

        Assert.assertEquals(null,disk.getPerson(6789));
        Assert.assertEquals(null,cache.getPerson(6789));

        Mockito.verify(disk).getPerson(6789);
        Mockito.verify(cache).getPerson(6789);


    }
//    public boolean doStuff() {
//        try {
////            loadDataFromWeb()
//        } catch (Exception e) {
//            return false;
//        } finally {
//            System.out.println("End of doStuff");
//        }
//    }
//    @Test
//    public void doStuffTest() throws Exception {
//        Mockito.when(loadDataFromWeb()).thenThrow(Exception.class);
//                Assert.assertEquals(false,doStuff());
//    }

    @Test
    public void cache() {

        Person person = new Person(cache, disk);

        person.setPerson(12345, "Carlos", "Santana");

        Mockito.when(cache.getPerson(12345)).thenReturn(person);

        Assert.assertEquals("Carlos Santana",cache.getPerson(12345).getFullName());

        Mockito.verify(cache, Mockito.times(2)).getPerson(12345);


    }
    @Test
    public void disk() {

        Person person = new Person(cache, disk);

        person.setPerson(12345, "Sebastian", "Perez");


        Mockito.when(cache.getPerson(12345)).thenReturn(null);
        Mockito.when(disk.getPerson(12345)).thenReturn(person);

        Assert.assertEquals(null,cache.getPerson(12345));
        Assert.assertEquals("Sebastian Perez",disk.getPerson(12345).getFullName());

        Mockito.verify(cache, Mockito.times(2)).getPerson(12345);
        Mockito.verify(disk, Mockito.times(2)).getPerson(12345);


    }
}

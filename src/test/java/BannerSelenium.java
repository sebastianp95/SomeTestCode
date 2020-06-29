import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BannerSelenium {
    private static WebDriver driver;



    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver= new ChromeDriver();

    }


    @Test
    public void loginTest(){
        driver.get("https://ggc.gabest.usg.edu/pls/B400/twbkwbis.P_WWWLogin");
        WebElement login = driver.findElement(By.id("UserID"));
        WebElement pin = driver.findElement(By.name("PIN"));
        WebElement submit= driver.findElement(By.xpath("/html/body/div[3]/form/p/input[1]"));
        login.sendKeys("900172967");
        pin.sendKeys("199507");
        submit.click();

    }

    @Test
    public void finalFirst(){
        driver.get("https://www.ssa.gov/OACT/population/longevity.html");
        WebElement gender = driver.findElement(By.id("sex"));
        WebElement month = driver.findElement(By.id("monthofbirth"));
        WebElement day = driver.findElement(By.id("dayofbirth"));
        WebElement year = driver.findElement(By.id("yearofbirth"));
        WebElement submit= driver.findElement(By.xpath("//*[@id=\"content\"]/section[2]/div/div[2]/form/input"));


        Select dropdown = new Select(gender);
        Select dropdown2 = new Select(month);
        Select dropdown3 = new Select(day);
        Select dropdown4 = new Select(year);

        dropdown.selectByIndex(2);
        dropdown2.selectByIndex(1);
        dropdown3.selectByIndex(1);
        dropdown4.selectByValue("1996");
        submit.click();

        boolean b = driver.getPageSource().contains("88.4");
        Assert.assertTrue(b);


    }



    // @AfterClass

    public static void cleanUp(){
        if(driver!=null){
            driver.close();
        }
    }

}

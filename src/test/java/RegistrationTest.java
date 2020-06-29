import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
@Test
    public void testIHG(){
        driver.get("https://ihgrewardsclubdining.rewardsnetwork.com/join.htm");
        //boolean b = driver.getPageSource().contains("virus");
       // Assert.assertTrue(b);
    WebElement element = driver.findElement(By.name("firstName"));
    element.sendKeys("Sebastian");
    WebElement element1 = driver.findElement(By.name("lastName"));
    element1.sendKeys("Perez");
    WebElement element3 = driver.findElement(By.name("zipcode"));
    element3.sendKeys("30096");
    WebElement element4 = driver.findElement(By.name("email"));
    element4.sendKeys("sebs@hot.com");
    WebElement element5 = driver.findElement(By.name("emailConfirm"));
    element5.sendKeys("sebs@hot.com");
    WebElement element6 = driver.findElement(By.name("acceptDFFTerms"));
    element6.click();

    element6.submit();

    WebElement test = driver.findElement(By.id("address1"));
    Assert.assertNotNull(test);
    }
}

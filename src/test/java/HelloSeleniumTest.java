import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;

public class HelloSeleniumTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void testGGCMainPage(){
        driver.get("https://www.ggc.edu");
        boolean b = driver.getPageSource().contains("virus");
        Assert.assertTrue(b);
    }

    @Test
    public void testMagnifier(){
        driver.get("https://www.ggc.edu");
        WebElement element = driver.findElement(By.className("toggle-search"));
        Assert.assertNotNull(element);

    }
    @Test
    public void testGoogle(){
        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Will I survive the coronavirus?");
        element.submit();

    }



}

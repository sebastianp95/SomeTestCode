import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TuitionCalculatorTest {
    private static WebDriver driver, driver1;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver1 = new ChromeDriver();
        driver1.manage().window().maximize();

    }


    @Test
    public void inState() throws InterruptedException {
        driver.get("https://www.usnews.com/best-colleges/georgia-gwinnett-college-41429");

        WebDriverWait myDynamicElement = new WebDriverWait(driver, 2);
        myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[1]/div/a[2]")));
        WebElement tuition = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[1]"));
        String s = tuition.getText();
        int index = s.indexOf('$');
        String s1 = s.substring(index + 1);
        int index2 = s1.indexOf(' ');
        String strOut = s1.substring(0, index2);
        strOut = strOut.replaceAll(",", "");
        System.out.println(strOut);


        driver1.get("http://www.ggc.edu/admissions/tuition-and-financial-aid-calculators/index.html#");

        WebElement element = driver1.findElement(By.cssSelector("input#inorout1"));

        driver1.findElement(By.xpath("//*[@id=\"main-content\"]/div/article/div/div[2]/form/div[1]/div/div/div[1]/fieldset/div/label[1]")).click();

        Thread.sleep(200);
        WebElement total = driver1.findElement(By.xpath("//*[@id=\"totalcost\"]"));
        String s2 = (String) ((JavascriptExecutor) driver1).executeScript("return document.getElementById('totalcost').value");
        s2 = s2.substring(1, s2.indexOf("."));
        System.out.println(s2);

        System.out.println("Instate\nUSNews: "+ Integer.parseInt(strOut)+" GGC: "+ Integer.parseInt(s2)+" assertNotEqual");
        Assert.assertNotEquals(Integer.parseInt(strOut), Integer.parseInt(s2));


    }
    @Test
    public void outState() throws InterruptedException {
        driver.get("https://www.usnews.com/best-colleges/georgia-gwinnett-college-41429");

        WebDriverWait myDynamicElement = new WebDriverWait(driver, 2);
        myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[1]/div/a[2]")));
        WebElement tuition = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr[2]"));
        String s = tuition.getText();
        int index = s.indexOf('$');
        String s1 = s.substring(index + 1);
        int index2 = s1.indexOf(' ');
        String strOut = s1.substring(0, index2);
        strOut = strOut.replaceAll(",", "");
        System.out.println(strOut);


        driver1.get("http://www.ggc.edu/admissions/tuition-and-financial-aid-calculators/index.html#");

        WebElement element = driver1.findElement(By.cssSelector("input#inorout1"));

        driver1.findElement(By.xpath("//*[@id=\"main-content\"]/div/article/div/div[2]/form/div[1]/div/div/div[1]/fieldset/div/label[2]")).click();

        Thread.sleep(200);
        WebElement total = driver1.findElement(By.xpath("//*[@id=\"totalcost\"]"));
        String s2 = (String) ((JavascriptExecutor) driver1).executeScript("return document.getElementById('totalcost').value");
        s2 = s2.substring(1, s2.indexOf("."));
        System.out.println(s2);


        System.out.println("outState\nUSNews: "+ Integer.parseInt(strOut)+" GGC: "+ Integer.parseInt(s2)+" assertNotEqual");
        Assert.assertNotEquals(Integer.parseInt(strOut), Integer.parseInt(s2));


    }

    @AfterClass

    public static void cleanUp() throws InterruptedException {

        if (driver != null || driver1 != null) {
            Thread.sleep(200);
            driver.close();
            driver1.quit();
        }
    }

}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Box24Test {
    final String URL = "https://box24.com.ua/";

    static {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void quite() {
        driver.quit();
    }

    @Test
    public void testSearchForm() throws InterruptedException {

        String expectedH1 = "Результати пошуку «plane»";
        String input = "plane";

        driver.get(URL);
        driver.findElement(By.cssSelector(".search__input")).sendKeys(input);

        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".search__button")).click();

        String actualH1 = driver.findElement(
                By.cssSelector("#j-catalog-header")).getText();

        Assert.assertEquals(actualH1, expectedH1);
    }


    @Test
    public void testBasketTitle() {
        String expectedRes = "Мій кошик";
        driver.get(URL);
        String actualRes = driver.findElement(By.cssSelector(".basket__title"))
                .getText();

        Assert.assertEquals(actualRes, expectedRes);
    }

    @Test
    public void testBasketItems() {
        int items = 0;
        driver.get(URL);
        String actualResult = driver.findElement(By.cssSelector(".basket__items"))
                .getText();

        Assert.assertEquals(actualResult, String.valueOf(items));
    }
}

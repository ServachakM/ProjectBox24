import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class Box24Test {

    final String URL = "https://box24.com.ua/";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

    @Test
    public void testSearchForm() {

        final String input = "plane";
        final String expectedH1 = "Результати пошуку «plane»";

        driver.findElement(By.cssSelector(".search__input")).sendKeys(input);

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search__button")));

        driver.findElement(By.cssSelector(".search__button")).click();

        String actualH1 = driver.findElement(By.cssSelector("#j-catalog-header")).getText();

        Assert.assertEquals(actualH1, expectedH1);
    }

    @Test
    public void testBasketTitle() {
        String expectedBasketTitle = "Мій кошик";

        String actualBasketTitle = driver.findElement(By.cssSelector(".basket__title")).getText();

        Assert.assertEquals(actualBasketTitle, expectedBasketTitle);
    }

    @Test
    public void testBasketItems() {
        int items = 0;

        String actualResult = driver.findElement(By.cssSelector(".basket__items")).getText();

        Assert.assertEquals(actualResult, String.valueOf(items));
    }

    @Test
    public void testBox24Language() {
        final String expectedLanguage = "Укр";

        String actualLanguage = driver.findElement(By.xpath("//div[@class='lang-menu__button']")).getText();

        Assert.assertEquals(actualLanguage, expectedLanguage);
    }

    @Test
    public void testBox24LogInPositive() {
        final String email = "nameName@gmail.com";
        final String password = "nameName111";
        final String expectedUserName = "Name Name";

        WebElement loginUser = driver.findElement(By.cssSelector(".userbar__button-text"));

        loginUser.click();
        driver.findElement(By.xpath("//form[@id='login_form_id']//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//form[@id='login_form_id']//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//form[@id='login_form_id']//input[@type='submit']")).click();
        driver.navigate().refresh();

        String actualUserName = driver.findElement(By.cssSelector(".userbar__button-text")).getText();

        Assert.assertEquals(actualUserName, expectedUserName);
    }
}
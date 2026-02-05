import Pages.MainPage;
import Pages.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;
    String browser = System.getProperty("browser","chrome");
    public BaseTest(String browser) {
        if(browser.equals("chrome")){
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();

        }
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }
    public void startBrowserChrome(){
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }
    public void startBrowserFirefox(){
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }


    @After
    public void tearDown(){
        driver.quit();
    }




}

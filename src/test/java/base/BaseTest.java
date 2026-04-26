package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    ConfigReader config=new ConfigReader();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        String browser=config.getBrowser();
        boolean isHeadless=config.isHeadless();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();

            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080"); 
            }

            driver.set(new ChromeDriver(options));
        }else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options=new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver.set(new FirefoxDriver(options));
        }else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
        
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        getDriver().get(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver()!=null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
package commons;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class AbstractTest {
    protected enum Brower{
        CHROME,FIREFOX,CHROMEHEADLESS,FIREFOXHEADLESS;
    }
    private WebDriver driver;
    private String path = System.getProperty("user.dir");

    protected WebDriver getBrowserDriver(String browerName) {
        Brower brower =  Brower.valueOf(browerName.toUpperCase());
        switch (brower) {
            case FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.gecko.driver", path + "//WebDriver//geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case CHROME:
//                WebDriverManager.chromedriver().setup();
//                WebDriverManager.chromedriver().version("77.0.36").setup();
                System.setProperty("webdriver.chrome.driver", path + "//WebDriver//chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case CHROMEHEADLESS:
                System.setProperty("webdriver.chrome.driver", path + "//WebDriver//chromedriver.exe");
                ChromeOptions chromeopt = new ChromeOptions();
                chromeopt.addArguments("headless");
                chromeopt.addArguments("window-size=1920*1080");
                driver = new ChromeDriver(chromeopt);
                break;
            case FIREFOXHEADLESS:
                System.setProperty("webdriver.chrome.driver", path + "//WebDriver//chromedriver.exe");
                FirefoxOptions fireOpt = new FirefoxOptions();
                fireOpt.addArguments("headless");
                fireOpt.addArguments("window-size=1920*1080");
                driver = new FirefoxDriver(fireOpt);
                break;
            default:
                throw new RuntimeException("Please chose new browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://google.com");
        return driver;
    }

}

package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractTest {
	private WebDriver driver;
	private String path = System.getProperty("user.dir");

	protected WebDriver getBrowserDriver(String browerName) {
		switch (browerName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", path + "//WebDriver//geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", path + "//WebDriver//chromedriver.exe");
			driver = new ChromeDriver();
			break;

		default:
			throw new RuntimeException("Please chose new browser");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://facebook.com");
		return driver;
	}

}

package bank.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObject.LoginPageObject;
import PageObject.NewCustomerPageObject;
import commons.AbstractTest;
import commons.PageGeneratorManager;

public class Test_01_Login extends AbstractTest{
	WebDriver driver;
	LoginPageObject loginpage;
	NewCustomerPageObject newcustome;
	String path = System.getProperty("user.dir");
  @Test
  public void test() {
	 loginpage = PageGeneratorManager.getLoginPage(driver);
	 System.out.println(driver.toString());
//	 doing...
//	 loginpage.clickToHereLink();
	 newcustome = PageGeneratorManager.getNewCustomerPage(driver);
	 System.out.println(driver.toString());
//	 doing...
  }
  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browserName) {
	  System.out.println("Browser name: "+ browserName);
	  driver = getBrowserDriver(browserName);
//	  if (browserName.contains("firefox")) {
//		  
//		  System.setProperty("webdriver.gecko.driver", path + "//WebDriver/geckodriver.exe");
//		  driver = new FirefoxDriver();
//	  }
//	  
//	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	  driver.get("http://google.com");
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
	  
	  
  }
}

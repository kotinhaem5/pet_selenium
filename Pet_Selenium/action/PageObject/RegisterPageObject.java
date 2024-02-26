package PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import PageUI.BankGuru.RegisterPageUI;
import commons.AbstractPage;
//import commons.PageGeneratoManager;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToEmailTextBox(String emailValue) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);

	}

	public void clickToSubmitButton() {
		waitToElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);

	}

	public String getUserIDText() {

		waitToElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getElementText(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {

		waitToElementVisible(driver, RegisterPageUI.PASSWORD_ID_TEXT);
		return getElementText(driver, RegisterPageUI.PASSWORD_ID_TEXT);

	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
	}

//	public LoginPageObject openLoginPage(String loginPageUrl) {
//		// TODO Auto-generated method stub
//		openUrl(driver, loginPageUrl);
//		return PageGeneratoManager.getLoginPage(driver);
//		
//	}

}

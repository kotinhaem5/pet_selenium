package PageObject;

import org.openqa.selenium.WebDriver;

import PageUI.BankGuru.NewCustomerPageUI;
import commons.AbstractPage;
//import commons.PageGeneratoManager;

public class NewCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		System.out.println("New Customer PageObject:----  " + driver.toString() + "----");
	}

	public void inputToNameTextbox(String namevalue) {

		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.NAME_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.NAME_TEXTBOX, namevalue);
	}

	public void inputToDateOfBirthTextbox(String dateOfBirth) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);

		removeAttributeInDOM(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, "type");
		sendKeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
	}

	public void inputToAddessTextbox(String valueAddess) {
	
		waitToElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTAREA);
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTAREA, valueAddess);

	}

	public void inputToCityTextbox(String valueCity) {

		waitToElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, valueCity);
	}

	public void inputToStateTextbox(String valueState) {

		waitToElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, valueState);

	}

	public void inputToPinTextbox(String valuePin) {

		waitToElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, valuePin);

	}

	public void inputToPhoneTextbox(String valuePhone) {

		waitToElementVisible(driver, NewCustomerPageUI.PHONE_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PHONE_TEXTBOX, valuePhone);
	}

	public void inputToEmailTextbox(String valueEmail) {
		
		waitToElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, valueEmail);

	}

	public void inputToPasswordTextbox(String valuePassword) {
		
		waitToElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, valuePassword);

	}

	public void clickToSubmitButton() {
		waitToElementClickAble(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	}

	public String getSuccessMessage() {
	
		waitToElementVisible(driver, NewCustomerPageUI.NEW_SUCCESS_MESSAGE);
		return getElementText(driver, NewCustomerPageUI.NEW_SUCCESS_MESSAGE);
	}

	public void clickToLogoutLink() {

		waitToElementClickAble(driver, NewCustomerPageUI.LOGOUT_LINK);
		clickToElement(driver, NewCustomerPageUI.LOGOUT_LINK);
		waitToAlertPresent(driver);
		acceptAlert(driver);
//	return new LoginPageObject(driver);
//		return PageGeneratoManager.getLoginPage(driver);
	}

//	public DeposittPageObject openDepositPage() {

//		waitToElementClickable(driver, DepositPageUI.DEPOSIT_LINK);
//		clickToElement(driver, DepositPageUI.DEPOSIT_LINK);
//		return PageGeneratoManager.getDeposittPage(driver);
//	}

}

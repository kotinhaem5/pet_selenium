package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import PageObject.DeposittPageObject;
import PageObject.EditCustomerPageObject;
import PageObject.FundTransferPageObject;
import PageObject.LoginPageObject;
import PageObject.NewCustomerPageObject;
import PageUI.BankGuru.AbstractPageUI;
import PageUI.BankGuru.DepositPageUI;
import PageUI.BankGuru.EditCustomerPageUI;
import PageUI.BankGuru.FundTransferPageUI;
import PageUI.BankGuru.LoginPageUI;
import PageUI.BankGuru.MainPageUI;

public abstract class AbstractPage {
	private WebDriverWait explicitWait;
	private Alert alert;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	private WebElement element;
	private long timeout = 30;
//	action must have perform()

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refeshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {

		driver.navigate().forward();
	}

	public void waitToAlertPresent(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();

	}

	public void cancelAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();

	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		alert = driver.switchTo().alert();
		alert.sendKeys(value);

	}

	public String getTextAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();

	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By byLocator(String Locatortype) {
		By by = null;
		System.out.println("locator type = " + Locatortype);
		if (Locatortype.startsWith("id=") || Locatortype.startsWith("ID=") || Locatortype.startsWith("Id=")) {
			by = By.id(Locatortype.substring(3));
		} else if (Locatortype.startsWith("class=") || Locatortype.startsWith("Class=")
				|| Locatortype.startsWith("CLASS=")) {
			by = By.className(Locatortype.substring(6));
		} else if (Locatortype.startsWith("Name=") || Locatortype.startsWith("name=")
				|| Locatortype.startsWith("sNAME=")) {
			by = By.name(Locatortype.substring(6));
		} else if (Locatortype.startsWith("Css=") || Locatortype.startsWith("CSS=") || Locatortype.startsWith("css=")) {
			by = By.cssSelector(Locatortype.substring(4));
		} else if (Locatortype.startsWith("xpath=") || Locatortype.startsWith("XPATH=")
				|| Locatortype.startsWith("Xpath=") || Locatortype.startsWith("XPath=")) {
			by = By.xpath(Locatortype.substring(6));
		}
		else {
			throw new RuntimeException("Locator type is not supporting");
		}
		return by;
	}
	public String castToRestParameter(String loactor, String... value) {
		loactor = String.format(loactor, (Object[]) value);
		return loactor;

	}

	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(byLocator(locator));
	}

	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byLocator(locator));
	}
	public List<WebElement> finds(WebDriver driver, String locator,String...value) {
		return driver.findElements(byLocator(castToRestParameter(locator, value)));
	}

	public void clickToElement(WebDriver driver, String Locator) {
		find(driver, Locator).click();
	}

	public void clickToElement(WebDriver driver, String Locator, String... values) {
		find(driver, castToRestParameter(Locator, values)).click();
	}

	public void sendKeyToElement(WebDriver driver, String Locator, String value) {
		find(driver, Locator).clear();
		find(driver, Locator).sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String Locator, String value, String... values) {
		find(driver, castToRestParameter(Locator, values)).clear();
		find(driver, castToRestParameter(Locator, values)).sendKeys(value);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);

	}

	public String getFirstItemInDropdown(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();

	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {

		find(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byLocator(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attribute) {
		return find(driver, locator).getAttribute(attribute);
	}

	public String getElementText(WebDriver driver, String locator) {
		return find(driver, locator).getText();
	}

	public int countElementSize(WebDriver driver, String locator) {
		return finds(driver, locator).size();
	}
	public int countElementSize(WebDriver driver, String locator,String...values) {
		return finds(driver, castToRestParameter(locator, values)).size();
	}

	public void checkToCheckBox(WebDriver driver, String locator) {
		if (!find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	public void unCheckToCheckBox(WebDriver driver, String locator) {
		if (find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}

	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		return find(driver, locator).isDisplayed();
	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
		return find(driver, castToRestParameter(locator, values)).isDisplayed();
	}

	public boolean isControlEnable(WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}

	public boolean isControlSelect(WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
	}

	public boolean isControlSelect(WebDriver driver, String locator, String values) {
		return find(driver, castToRestParameter(locator, values)).isSelected();
	}

	public boolean isControlSelect(WebDriver driver, String locator, String... values) {
		return find(driver, castToRestParameter(locator, values)).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(find(driver, locator));
	}

	public void switchToDefaultPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator)).perform();

	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator)).perform();

	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locator)).perform();

	}

	public void dragAndDropElement(WebDriver driver, String sourcelocator, String targetlocator) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, sourcelocator), find(driver, targetlocator)).perform();
	}

	public void sendKeyBroadToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator), key).perform();
	}
	public void sendKeyBroadToElement(WebDriver driver, String locator, Keys key,String...value) {
		action = new Actions(driver);
		action.sendKeys(find(driver, castToRestParameter(locator, value)), key).perform();
	}
	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitToElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byLocator(locator)));
	}

	public void waitToElementPresence(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait
				.until(ExpectedConditions.presenceOfElementLocated(byLocator(castToRestParameter(locator, values))));
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(byLocator(castToRestParameter(locator, values))));
	}

	public void waitToElementInVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator(locator)));
	}

	public void waitToElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
	}

	public void waitToElementClickAble(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byLocator(castToRestParameter(locator, values))));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, locator));
	}

	public NewCustomerPageObject openCustomerPage(WebDriver driver) {
		waitToElementClickAble(driver, MainPageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, MainPageUI.NEW_CUSTOMER_LINK);
		return PageGeneratorManager.getNewCustomerPage(driver);

	}

	public DeposittPageObject openDepositePage(WebDriver driver) {
		waitToElementClickAble(driver, DepositPageUI.DEPOSIT_LINK);
		clickToElement(driver, DepositPageUI.DEPOSIT_LINK);
		return PageGeneratorManager.getDepositePage(driver);

	}

	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		waitToElementClickAble(driver, EditCustomerPageUI.EDIT_CUSTOMER_LINK);
		clickToElement(driver, EditCustomerPageUI.EDIT_CUSTOMER_LINK);
		return PageGeneratorManager.getEditCustomerPage(driver);

	}

	public FundTransferPageObject openFundTransferPage(WebDriver driver) {
		waitToElementClickAble(driver, FundTransferPageUI.FUND_TRANSFER_LINK);
		clickToElement(driver, FundTransferPageUI.FUND_TRANSFER_LINK);
		return PageGeneratorManager.getFundTransferPage(driver);

	}

	public LoginPageObject clickToLogoutLinl(WebDriver driver) {
		waitToElementClickAble(driver, LoginPageUI.LOGOUT_LINK);
		clickToElement(driver, LoginPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getLoginPage(driver);
//		LoginPageObject loginpage;
//		NewCustomerPageObject newcustomePge;
//		loginpage = newcustomePge.ClicklogoutLink(driver);
	}

	

	/* so luong page it: 10-20 */
//	open dynamic pageMenu
	public AbstractPage openMenuPageByNamePage(WebDriver driver, String pageName) {
		waitToElementClickAble(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
		if (pageName.equals("New Customer")) {
			return PageGeneratorManager.getNewCustomerPage(driver);
		} else {
			throw new RuntimeException();
		}

	}

	/* so luong page nhieu: 10-20 */
	public void openMenuPagesByNamePage(WebDriver driver, String pageName) {
		waitToElementClickAble(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
	}
}

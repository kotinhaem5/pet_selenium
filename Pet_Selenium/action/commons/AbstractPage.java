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

	public By byXpath(String Locator) {
		return By.xpath(Locator);

	}

	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void clickToElement(WebDriver driver, String Locator) {
		find(driver, Locator).click();
	}
	public void clickToElement(WebDriver driver, String Locator,String...values) {
		find(driver, castToRestParameter(Locator, values)).click();
	}

	public void sendKeyToElement(WebDriver driver, String Locator, String value) {
		find(driver, Locator).clear();
		find(driver, Locator).sendKeys(value);
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
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

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
	
	public boolean isControlDisplay(WebDriver driver, String locator) {
		return find(driver, locator).isDisplayed();
	}

	public boolean isControlEnable(WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}

	public boolean isControlSelect(WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
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
	public void dragAndDropElement(WebDriver driver,String sourcelocator,String targetlocator) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, sourcelocator), find(driver, targetlocator)).perform();
	}
	public void sendKeyBroadToElement(WebDriver driver,String locator,Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator),key).perform();
	}
	public void scrollToElement(WebDriver driver,String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void waitToElementPresence(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}
	public void waitToElementVisible(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	public void waitToElementInVisible(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}
	public void waitToElementClickAble(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
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
//		loginpage = newcustomePge.ClicklogoutLink(driver);
	}
	public String castToRestParameter(String loactor,String... value) {
		loactor = String.format(loactor, value);
		return loactor;
		
	}
	
}

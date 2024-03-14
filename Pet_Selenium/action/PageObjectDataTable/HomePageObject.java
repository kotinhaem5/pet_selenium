package PageObjectDataTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageUI.DataTable.HomePageUI_DataTable;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void openPageByNumber(String pageNumber) {
		waitToElementClickAble(driver, HomePageUI_DataTable.PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI_DataTable.PAGE_NUMBER, pageNumber);
		
	}
	public void enterHeaderByLabel(String header, String value) {
		waitToElementVisible(driver, HomePageUI_DataTable.HEADER, header);
		sendKeyToElement(driver, HomePageUI_DataTable.HEADER, value, header);
		sendKeyBroadToElement(driver, HomePageUI_DataTable.HEADER, Keys.ENTER, header);
	

	
	}
	public boolean isPageNumberActive(String pageNumber) {
		waitToElementVisible(driver, HomePageUI_DataTable.PAGE_NUMBER_ACTIVE, pageNumber);
		return isElementDisplay(driver, HomePageUI_DataTable.PAGE_NUMBER_ACTIVE, pageNumber);
	}
	
	public List<String> getValueEachRowAllPage() {
		
		int totalPage = countElementSize(driver, HomePageUI_DataTable.TOTAL_PAGEINATION);
		System.out.println("Total size: "+ totalPage);
		ArrayList<String> allRowAllPage = new ArrayList<String>();
		Set<String> allRowValueUniquePage = new HashSet<String>();
		
		for(int index = 1;index <= totalPage;index++) {
			clickToElement(driver, HomePageUI_DataTable.PAGEINATION_BY_INDEX, String.valueOf(index));
			sleepInSecond(1);
			List<WebElement> allRowEachPage = finds(driver, HomePageUI_DataTable.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowEachPage) {
				allRowAllPage.add(eachRow.getText());	
			}
		}
		return allRowAllPage;
	}
public List<String> getValueEachRowUniqueAllPage() {
		
		int totalPage = countElementSize(driver, HomePageUI_DataTable.TOTAL_PAGEINATION);
		System.out.println("Total size: "+ totalPage);
		ArrayList<String> allRowAllPage = new ArrayList<String>();
		Set<String> allRowValueUniquePage = new HashSet<String>();
		
		for(int index = 1;index <= totalPage;index++) {
			clickToElement(driver, HomePageUI_DataTable.PAGEINATION_BY_INDEX, String.valueOf(index));
			sleepInSecond(1);
			List<WebElement> allRowEachPage = finds(driver, HomePageUI_DataTable.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowEachPage) {
				allRowValueUniquePage.add(eachRow.getText());	
			}
		}
		for(String x:allRowValueUniquePage) {
			System.out.println("------");
			System.out.println(x);
		}
		return allRowAllPage;
	}

public List<String> getValueEachRowAllPageBy(String columName) {
	
	int totalPage = countElementSize(driver, HomePageUI_DataTable.TOTAL_PAGEINATION);
	System.out.println("Total size: "+ totalPage);
	ArrayList<String> allRowAllPage = new ArrayList<String>();

	
	for(int index = 1;index <= totalPage;index++) {
		clickToElement(driver, HomePageUI_DataTable.PAGEINATION_BY_INDEX, String.valueOf(index));
		sleepInSecond(1);
		List<WebElement> allRowEachPage = finds(driver, HomePageUI_DataTable.ALL_ROW_EACH_PAGE_BY, columName);
		for (WebElement eachRow : allRowEachPage) {
			allRowAllPage.add(eachRow.getText());	
		}
	}
	for(String x:allRowAllPage) {
		System.out.println("------");
		System.out.println(x);
	}
	return allRowAllPage;
}
	


}

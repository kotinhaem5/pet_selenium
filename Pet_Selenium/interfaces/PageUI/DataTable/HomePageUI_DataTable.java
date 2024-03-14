package PageUI.DataTable;

public class HomePageUI_DataTable {
	public static final String PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String HEADER = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String PAGE_NUMBER_ACTIVE = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text() ='%s']";
	public static final String TOTAL_PAGEINATION = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page']";
	public static final String PAGEINATION_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String ALL_ROW_EACH_PAGE = "xpath=//tr";
	public static final String ALL_ROW_EACH_PAGE_BY = "xpath=//tr/td[@data-key='%s']";
}

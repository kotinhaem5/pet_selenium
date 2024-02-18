package com.java.basic;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_01_Login {
	WebDriver driver;

	@Test(description = "check description")
	public void f() {
//	  driver.findElement(By.xpath("")).sendKeys(null);
		System.out.println(String.valueOf("dfsd") + System.getProperty("user.dir"));
	}

	@BeforeClass
	public void beforeClass() {

	}

	@AfterClass
	public void afterClass() {

	}
}

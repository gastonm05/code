package com.evercoding.pageobjects;

import org.openqa.selenium.*;
import org.testng.Assert;

public class SearchUserPage {

	private static WebElement element = null;

	public static WebElement insertEmpID(WebDriver driver) {

		element = driver.findElement(By.id("employee_identification"));
		Assert.assertTrue(element.isDisplayed());

		return element;

	}

	public static WebElement findEmployeeButton(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='new_employee']/div[3]/input"));
		Assert.assertTrue(element.isDisplayed());

		return element;

	}
}

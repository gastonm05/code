package com.evercoding.pageobjects;

import org.openqa.selenium.*;
import org.testng.Assert;

public class SearchResultsPage {

	private static WebElement element = null;

	public static WebElement content(WebDriver driver) {

		element = driver.findElement(By.xpath(".//*[@id='content']/h1"));
		Assert.assertTrue(element.isDisplayed());

		return element;

	}
}

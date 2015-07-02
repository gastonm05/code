package com.evercoding.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;

public class EmployeeInformation {

	private final static Logger LOGGER = Logger
			.getLogger(EmployeeInformation.class);

	private static WebElement element = null;

	public static WebElement notice(WebDriver driver) {

		element = driver.findElement(By.xpath(".//*[@id='notice']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Notice text box found");
		}

		return element;

	}

	public static WebElement content(WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='content']/p[1]"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Content text box found");
		}

		return element;
	}

	public static WebElement logOutLink(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='user_information']/span/a"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Log out link found");
		}

		return element;
	}
}

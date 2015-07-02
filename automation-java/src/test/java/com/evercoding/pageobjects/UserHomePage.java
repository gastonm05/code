package com.evercoding.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;

public class UserHomePage {

	private final static Logger LOGGER = Logger.getLogger(UserHomePage.class);

	private static WebElement element = null;

	public static WebElement createNewEmployeeLink(WebDriver driver) {

		element = driver.findElement(By.xpath(".//*[@id='content']/p[2]/a"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Create Employee link found");
		}

		return element;

	}

	public static WebElement logOutLink(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='user_information']/span/a"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Log Out out link found");
		}

		return element;
	}

}
package com.evercoding.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;

public class LoginPage {

	private final static Logger LOGGER = Logger.getLogger(LoginPage.class);

	private static WebElement element = null;

	public static WebElement userName(WebDriver driver) {

		element = driver.findElement(By.id("user_email"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Username text box found");
		}

		return element;

	}

	public static WebElement password(WebDriver driver) {

		element = driver.findElement(By.id("user_password"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Password text box found");
		}

		return element;

	}

	public static WebElement signInButton(WebDriver driver) {

		element = driver.findElement(By.cssSelector(".submit"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Sign In Button found");
		}

		return element;

	}

	public static WebElement publicSite(WebDriver driver) {

		element = driver.findElement(By.xpath(".//*[@id='content']/p/a"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("PublicSite Link found");
		}

		return element;

	}

}

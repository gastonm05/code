package com.evercoding.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;

public class NewUserPage {

	private final static Logger LOGGER = Logger.getLogger(NewUserPage.class);

	private static WebElement element = null;

	public static WebElement firstName(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_first_name']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Firstname text box found");
		}
		return element;

	}

	public static WebElement lastName(WebDriver driver) {

		element = driver
				.findElement(By.xpath(".//*[@id='employee_last_name']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("LastName text box found");
		}

		return element;

	}

	public static WebElement email(WebDriver driver) {

		element = driver.findElement(By.xpath(".//*[@id='employee_email']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Email text box found");
		}

		return element;

	}

	public static WebElement identification(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_identification']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Identification text box found");
		}

		return element;

	}

	public static WebElement leaderName(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_leader_name']"));
		LOGGER.info("Leader Name text box found");
		Assert.assertTrue(element.isDisplayed());

		return element;
	}

	public static WebElement year(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_start_working_on_1i']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Year dropdown box found");
		}

		return element;

	}

	public static WebElement month(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_start_working_on_2i']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Month dropdown box found");
		}

		return element;

	}

	public static WebElement day(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='employee_start_working_on_3i']"));
		Assert.assertTrue(element.isDisplayed());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Day dropdown box found");
		}

		return element;
	}

	public static WebElement createEmployeeButton(WebDriver driver) {

		element = driver.findElement(By
				.xpath(".//*[@id='new_employee']/div[8]/input"));
		LOGGER.info("Create Employee Button found");
		if (LOGGER.isDebugEnabled()) {
			Assert.assertTrue(element.isDisplayed());
		}

		return element;
	}

}

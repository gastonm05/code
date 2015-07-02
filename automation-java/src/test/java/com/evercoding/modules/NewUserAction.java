package com.evercoding.modules;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.evercoding.pageobjects.NewUserPage;

public class NewUserAction {

	private final static Logger LOGGER = Logger.getLogger(NewUserAction.class);

	public final void fillInForm(WebDriver driver, String sFirstName,
			String sLastName, String sEmail, String sIdentification,
			String sLeaderName) {

		NewUserPage.firstName(driver).sendKeys(sFirstName);
		LOGGER.info(sFirstName + " entered in the FirstName text box");

		NewUserPage.lastName(driver).sendKeys(sLastName);
		LOGGER.info(sLastName + " entered in the Lastname text box");

		NewUserPage.email(driver).sendKeys(sEmail);
		LOGGER.info(sEmail + " entered in the Email text box");

		NewUserPage.identification(driver).sendKeys(sIdentification);
		LOGGER.info(sIdentification + " entered in the Identification text box");

		NewUserPage.leaderName(driver).sendKeys(sLeaderName);
		LOGGER.info(sLeaderName + " entered in the LeaderName text box");

		NewUserPage.year(driver).click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_1i']"))
				.click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_1i']/option[text()='2010']"))
				.click();
		LOGGER.info("Year entered in the Year dropdown box");

		NewUserPage.month(driver).click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_2i']"))
				.click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_2i']/option[text()='January']"))
				.click();
		LOGGER.info("Month entered in the Month dropdown box");

		NewUserPage.day(driver).click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_3i']"))
				.click();
		driver.findElement(
				By.xpath("//select [@id='employee_start_working_on_3i']/option[text()='21']"))
				.click();
		LOGGER.info("Day entered in the Month dropdown box");
		LOGGER.info("Create new user form filled");

		NewUserPage.createEmployeeButton(driver).click();
		LOGGER.info("Click action performed on Create Employee button");
	}
}
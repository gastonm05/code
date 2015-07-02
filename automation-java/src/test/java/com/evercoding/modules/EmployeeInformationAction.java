package com.evercoding.modules;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.log4j.Logger;

import com.evercoding.pageobjects.EmployeeInformation;
import com.evercoding.pageobjects.UserHomePage;

public class EmployeeInformationAction {

	private final static Logger LOGGER = Logger
			.getLogger(EmployeeInformationAction.class);

	public final void verifyNewEmployeeText(WebDriver driver) {

		Assert.assertTrue(EmployeeInformation.notice(driver).getText()
				.equals("Employee was successfully created."));

		LOGGER.info("New Employee successfully created");
	}

	public final void clickLogOutLink(WebDriver driver) {

		UserHomePage.logOutLink(driver).click();
		LOGGER.info("Click action performed on Log Out link");
	}

}

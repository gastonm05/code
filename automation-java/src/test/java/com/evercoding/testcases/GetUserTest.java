package com.evercoding.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.evercoding.modules.GetUserAction;
import com.evercoding.testdata.Constant;

public class GetUserTest {

	private final static Logger LOGGER = Logger.getLogger(GetUserTest.class);

	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		DOMConfigurator.configure("log4j.xml");

		LOGGER.info("########## Scenario_03_GetUserTest ##########");

		driver = new FirefoxDriver();

		LOGGER.info("New driver instantiated");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LOGGER.info("Implicit wait applied on the driver for 10 seconds");

		driver.get(Constant.URL);

	}

	@Test
	public void main() {

		final GetUserAction guAction = new GetUserAction();
		guAction.clickPublicSite(driver);

		guAction.searchForUser(driver, Constant.NU_ID);

		guAction.verifyUserExists(driver, Constant.NU_COMPLETE_NAME);

	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
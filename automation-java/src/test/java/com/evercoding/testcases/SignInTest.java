package com.evercoding.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.evercoding.modules.HomePageAction;
import com.evercoding.modules.SignInAction;
import com.evercoding.testdata.Constant;

public class SignInTest {

	private final static Logger LOGGER = Logger.getLogger(CreateUserTest.class);

	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		DOMConfigurator.configure("log4j.xml");

		LOGGER.info("########## Scenario_01_SignInTest ##########");

		driver = new FirefoxDriver();

		LOGGER.info("New driver instantiated");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LOGGER.info("Implicit wait applied on the driver for 10 seconds");

		driver.get(Constant.URL);

	}

	@Test
	public void main() {

		// Sign In
		final SignInAction siAction = new SignInAction();
		siAction.signIn(driver, Constant.USER_NAME, Constant.USER_PASSWORD);

	}

	@AfterMethod
	public void afterMethod() {

		// Log Out
		final HomePageAction hpAction = new HomePageAction();
		hpAction.clickLogOutLink(driver);

		driver.quit();
	}

}
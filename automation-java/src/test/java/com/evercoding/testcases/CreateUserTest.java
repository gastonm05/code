package com.evercoding.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.evercoding.modules.EmployeeInformationAction;
import com.evercoding.modules.HomePageAction;
import com.evercoding.modules.NewUserAction;
import com.evercoding.modules.SignInAction;
import com.evercoding.testdata.Constant;

public class CreateUserTest {

	private final static Logger LOGGER = Logger.getLogger(CreateUserTest.class);

	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		DOMConfigurator.configure("log4j.xml");

		LOGGER.info("########## Scenario_02_CreateUserTest ##########");

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

		// Create New Employee
		final HomePageAction hpAction = new HomePageAction();
		hpAction.clickCreateNewEmployeeLink(driver);

		// New User Form Filling
		final NewUserAction nuAction = new NewUserAction();
		nuAction.fillInForm(driver, Constant.NU_FIRST_NAME,
				Constant.NU_LAST_NAME, Constant.NU_EMAIL, Constant.NU_ID,
				Constant.NU_LEADER_NAME);

		// Verify User was created
		final EmployeeInformationAction eiAction = new EmployeeInformationAction();
		eiAction.verifyNewEmployeeText(driver);

	}

	@AfterMethod
	public void afterMethod() {

		final EmployeeInformationAction eiAction = new EmployeeInformationAction();
		eiAction.clickLogOutLink(driver);

		driver.quit();
	}

}
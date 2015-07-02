package com.evercoding.modules;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.evercoding.pageobjects.EmployeeInformation;
import com.evercoding.pageobjects.LoginPage;

public class SignInAction {

	private final static Logger LOGGER = Logger.getLogger(SignInAction.class);

	public final void signIn(WebDriver driver, String sUsername,
			String sPassword) {

		LoginPage.userName(driver).sendKeys(sUsername);
		LOGGER.info("Username entered in the Username text box");

		LoginPage.password(driver).sendKeys(sPassword);
		LOGGER.info("Password entered in the Password text box");

		LoginPage.signInButton(driver).click();
		LOGGER.info("Click action performed on Sign In button");

		Assert.assertTrue(EmployeeInformation.content(driver).getText()
				.equals("Signed in successfully."));
		LOGGER.info("Sign in Successfully");
	}
}
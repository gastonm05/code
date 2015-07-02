package com.evercoding.modules;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.evercoding.pageobjects.LoginPage;
import com.evercoding.pageobjects.SearchResultsPage;
import com.evercoding.pageobjects.SearchUserPage;

public class GetUserAction {

	private final static Logger LOGGER = Logger.getLogger(GetUserAction.class);

	public final void searchForUser(WebDriver driver, String sUserId) {

		SearchUserPage.insertEmpID(driver).sendKeys(sUserId);
		LOGGER.info("Username entered in the Username text box");

		SearchUserPage.findEmployeeButton(driver).click();
		LOGGER.info("Click action performed on FindEmployee button");

	}

	public final void verifyUserExists(WebDriver driver, String sNUCompleteName) {
		Assert.assertTrue(SearchResultsPage.content(driver).getText()
				.contains(sNUCompleteName));
		LOGGER.info("User Found");

	}

	public final void clickPublicSite(WebDriver driver) {
		LoginPage.publicSite(driver).click();
	}
}
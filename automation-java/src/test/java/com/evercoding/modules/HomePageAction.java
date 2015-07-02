package com.evercoding.modules;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.evercoding.pageobjects.UserHomePage;
import com.evercoding.testdata.Constant;

public class HomePageAction {

	private final static Logger LOGGER = Logger.getLogger(HomePageAction.class);
	private static final String TABLEPATH = "//div[@id='content']/table/tbody/tr";

	public final void clickCreateNewEmployeeLink(WebDriver driver) {

		UserHomePage.createNewEmployeeLink(driver).click();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Click action performed on Create New Employee link");
		}
	}

	public final void clickLogOutLink(WebDriver driver) {

		UserHomePage.logOutLink(driver).click();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Click action performed on Log out link");
		}
	}

	public final void delete(WebDriver driver) {

		int rowCount = driver.findElements(By.xpath(TABLEPATH)).size() + 1;
		final int empCount = rowCount - 2;
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("The Employee number is: " + empCount);
		}
		Assert.assertFalse(empCount == 0);

		for (int i = 2; i < rowCount; i++) {

			final String cFirstName = driver.findElement(
					By.xpath(TABLEPATH + "[" + i + "]/td[1]")).getText();
			final String cLastName = driver.findElement(
					By.xpath(TABLEPATH + "[" + i + "]/td[2]")).getText();
			final String cIdentification = driver.findElement(
					By.xpath(TABLEPATH + "[" + i + "]/td[3]")).getText();

			final String rowData = cFirstName + cLastName + cIdentification;
			final String empToDeleteInfo = Constant.NU_FIRST_NAME
					+ Constant.NU_LAST_NAME + Constant.NU_ID;

			if (cFirstName.equalsIgnoreCase(Constant.NU_FIRST_NAME)
					&& cLastName.equalsIgnoreCase(Constant.NU_LAST_NAME)
					&& cIdentification.equalsIgnoreCase(Constant.NU_ID)) {
				driver.findElement(By.xpath(TABLEPATH + "[" + i + "]/td[9]/a"))
						.click();
				driver.switchTo().alert().accept();
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("Employee from row "
							+ i
							+ " with Information "
							+ rowData
							+ " matches with the Employee to delete information "
							+ empToDeleteInfo + " and it will be deleted");
					LOGGER.info("Employee from row " + i + " with Information "
							+ rowData + " was deleted successfully");

				}
				i = i - 1;
				rowCount = rowCount - 1;
			} else {
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("The Employee from row "
							+ i
							+ " with Information "
							+ rowData
							+ " does not match the Employee to delete description");
				}
			}

			LOGGER.info("There are no more employees to review / delete matching the description");
		}

	}
}

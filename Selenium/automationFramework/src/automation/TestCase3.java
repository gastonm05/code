package automation;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class TestCase3 {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.google.com.ar";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void testExample() throws Exception {
		String searchTerm = "Liquadora Atma";
		String companyLink = "fravega.com.ar";
		driver.get(baseUrl);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(searchTerm);
		searchBox.submit();
		// Look for results excluding ADs
		WebElement searchResults = driver.findElement(By.id("res"));
		// Verify the link is including in the results
		Assert.assertTrue(searchResults.getText().contains(companyLink),
				"" + companyLink + " is not found in the first page of Google results, excluding ADs results");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

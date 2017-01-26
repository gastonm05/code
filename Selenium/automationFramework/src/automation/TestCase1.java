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

public class TestCase1 {
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
		driver.get(baseUrl);
		String companyName = "Frávega";
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(companyName);
		searchBox.submit();
		// Look for the Knowledge box
		WebElement knowledgeBox = driver.findElement(By.id("rhs_block"));
		Assert.assertTrue(knowledgeBox.getText().contains(companyName), "Search result validation failed.");

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

package automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCase2 {
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
		String companyName = "fravega";
		String locator = "div[class='g']";
		driver.get(baseUrl);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(searchTerm);
		searchBox.submit();

		// List results elements
		List<WebElement> results = driver.findElements(By.cssSelector(locator));

		boolean found = false;
		// Loop over the list to verify first 5 results link contains our term
		while (!found) {
			for (int i = 1; i < 6; i++) {
				if (results.get(i).getText().contains(companyName)) {
					System.out.println("Search result validation success at instance [ " + i + " ].");
					found = true;
				} else {
				}
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

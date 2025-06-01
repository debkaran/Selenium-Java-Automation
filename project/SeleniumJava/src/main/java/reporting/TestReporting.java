package reporting;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;

public class TestReporting {

	private WebDriver driver;

	@Test(priority = 1)
	public void testPassed() {

		driver = ChromeBrowserSetup.getInstance().setup();

		driver.get(PropertyReader.getInstance().getProperty("gmail"));
		System.out.println("Successfully executed!!!");
	}

	@Test(priority = 2)
	public void testFailed() {

		Assert.fail("User failed the test");
	}

	@Test(priority = 3)
	public void testSkip() {

		throw new SkipException("Skipping the test case :-(");
	}

	@AfterSuite
	private void quitDriver() {

		driver.quit();
	}
}

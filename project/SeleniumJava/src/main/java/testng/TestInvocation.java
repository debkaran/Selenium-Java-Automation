package testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;

public class TestInvocation {

	@Test(invocationCount = 5, threadPoolSize = 5)
	public void launchBrowser() {

		WebDriver driver = ChromeBrowserSetup.getInstance().setup();

		driver.get(PropertyReader.getInstance().getProperty("googleUrl"));
		System.out.println(driver.getTitle());

		driver.quit();
	}
}
package utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.PropertyReader;

public class WaitUtilities {

	private WaitUtilities() {

	}

	public static void implicitWait(WebDriver driver) {

		// implicit timeout
		driver.manage().timeouts().implicitlyWait(
				Duration.ofSeconds(Long.parseLong(PropertyReader.getInstance().getProperty("implicit_wait"))));

	}

	public static void elementToBeVisible(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void elementToBeClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}

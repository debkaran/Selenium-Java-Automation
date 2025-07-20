package com.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {

	protected static WebDriver driver;
	private static String browser;
	private static Properties config = new Properties();
	private static Properties OR = new Properties();
	private static FileInputStream fis;
	public static final Logger log = LogManager.getLogger(Page.class);
	protected static ExtentReports report = ExtentManager.getInstance();
	protected static ExtentTest extentTest;

	public static TopMenu menu;

	public Page() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/com/test/properties/config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/com/test/properties/OR.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile password_manager_enabled", false);
			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver = new ChromeDriver(options);
				log.debug("Chrome Launched");
			} else if (config.getProperty("browser").equals("safari")) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				log.debug("Safari Launched");
			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			System.out.println("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
			menu = new TopMenu(driver);

			try {
				if (FileUtils.isDirectory(
						new File(System.getProperty("user.dir") + "/target/surefire-reports/html/screenshots"))) {
					FileUtils.cleanDirectory(
							new File(System.getProperty("user.dir") + "/target/surefire-reports/html/screenshots"));
				}
				FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/reports"));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Clicks on a web element identified by the given logical locator key.
	 * 
	 * @param locator the logical key used to fetch the element's CSS selector from
	 *                the Object Repository (OR)
	 * @throws NoSuchElementException if the element is not found using the resolved
	 *                                CSS selector
	 */
	public static void click(String locator) {

		if (locator.contains("CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			extentTest.log(Status.INFO, "Clicking on : " + locator);
		} else {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			extentTest.log(Status.INFO, "Clicking on : " + locator);
		}

	}

	/**
	 * Types a given value into the input field identified by the specified logical
	 * locator key.
	 * 
	 * @param locator the logical key used to retrieve the element's CSS selector
	 *                from the Object Repository (OR)
	 * @param value   the string value to be typed into the input field
	 * @throws NoSuchElementException if the element is not found using the resolved
	 *                                CSS selector
	 */
	public static void type(String locator, String value) {

		WebElement elem = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		elem.click();
		elem.sendKeys(value);
		extentTest.log(Status.INFO, "Typing in the : " + locator + " in the value as : " + value);
	}

	/**
	 * Checks whether a web element specified by the given locator is present and
	 * interactable.
	 * 
	 * @param by the Selenium locator used to identify the web element
	 * @return true if the element is found and clickable, false otherwise
	 */
	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by).click();
			return true;
		} catch (NoSuchElementException noSuchElemExp) {
			return false;
		}
	}

	public void select(String locator, String value) {

		WebElement dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		if (dropdown.isDisplayed()) {
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			extentTest.log(Status.INFO, "Selecting from dropdown : " + locator + " value as : " + value);
		}
	}

	public static void quit() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("Test Execution Completed!!!");
	}
}

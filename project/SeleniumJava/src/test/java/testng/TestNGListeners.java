package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;

public class TestNGListeners {

	@FindBy(how = How.ID, using = "identifierId")
	private WebElement elemEmailTxtBox;
	@FindBy(how = How.CSS, using = "div[id='identifierNext'] button span")
	private WebElement elemNextBtn;

	private WebDriver driver;

	private TestNGListeners() {

	}

	@BeforeSuite
	private void beforeSuite() {

		driver = ChromeBrowserSetup.getInstance().setup();

		driver.get(PropertyReader.getInstance().getProperty("gmail"));
	}

	@Test(priority = 1)
	private void doTest() {

		PageFactory.initElements(driver, this);
		elemEmailTxtBox.sendKeys("debkaransinghania");
		elemNextBtn.click();

	}

	@Test(priority = 2)
	public void composeEmail() {

//		Assert.fail("Error in Composing Email");
	}

	@AfterSuite
	private void quitDriver() {

		driver.quit();
	}
}

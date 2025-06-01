package uiinteraction;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WaitUtilities;
import utility.WebPageUtilities;

public class TestKeyboardEvents {

	@FindBy(id = "identifierId")
	private WebElement elemEmailOrPhone;
	@FindBy(how = How.XPATH, using = "//*[@id='headingSubtext']/span")
	private WebElement elemSigninTxt;

	private static WebDriver driver = null;
	private String strEmail = "debkaransinghania";

	private TestKeyboardEvents() {

		PageFactory.initElements(driver, this);
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("gmail"));
		new TestKeyboardEvents().doKeyboardEvents();
	}

	private void doKeyboardEvents() {

		WebPageUtilities.enterTextInTxtBox(driver, elemEmailOrPhone, strEmail);
		if (elemSigninTxt.isDisplayed()) {
			WaitUtilities.elementToBeClickable(driver, elemSigninTxt);
			elemSigninTxt.click();
			Actions action = new Actions(driver);
			// copy text from UI
			action.keyDown(Keys.COMMAND).sendKeys("a").sendKeys("c").keyUp(Keys.COMMAND).build().perform();
			elemEmailOrPhone.click();
			// paste the copied text in UI
			action.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
			action.sendKeys(Keys.ENTER).perform();
		}
	}
}

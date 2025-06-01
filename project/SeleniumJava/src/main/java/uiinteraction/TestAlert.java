package uiinteraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WaitUtilities;
import utility.WebPageUtilities;

public class TestAlert {

	@FindBy(how = How.CLASS_NAME, using = "signin-btn")
	private WebElement elemLogInBtn;

	public TestAlert() {
		PageFactory.initElements(driver, this);
	}

	private static WebDriver driver = null;

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("alertbox"));
		new TestAlert().handleAlert();
	}

	private void handleAlert() {

		if (elemLogInBtn.isDisplayed()) {
			WaitUtilities.elementToBeClickable(driver, elemLogInBtn);
			elemLogInBtn.click();
			System.out.println("Login button is clicked");
			WebPageUtilities.acceptAlert(driver);
		}
	}
}

package selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WebPageUtilities;

public class TestRelativeLocators {

	private String textBoxName = "Email", textToBeSend = "trainer@way2automation.com", buttonName = "Submit";

	private static WebDriver driver = null;

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("way2automationUrl2"));
		new TestRelativeLocators().submitForm();

		driver.quit();
	}

	public void submitForm() {

		WebPageUtilities.writeTextIntoAFormTextBox(driver, textBoxName, textToBeSend);
//		WebPageUtilities.buttonClickWithInAForm(driver, buttonName);
//		driver.findElement(with(By.tagName("input")).toRightOf(By.linkText("Signin"))).click();
		driver.findElement(with(By.className("button")).below(By.xpath("//*[@id='load_form']/fieldset[7]/input")))
				.click();
	}
}

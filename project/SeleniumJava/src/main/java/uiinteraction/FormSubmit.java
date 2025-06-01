package uiinteraction;

import org.openqa.selenium.WebDriver;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WebPageUtilities;

public class FormSubmit {

	private static WebDriver driver = null;

	private String textBoxName = "Phone", textToBeSend = "xyz", dropdownName = "Country", valueToBeSelected = "Zimbabwe",
			buttonName = "SUBMIT";

	private FormSubmit() {

	}

	private void interactWithinAForm() {

		WebPageUtilities.writeTextIntoAFormTextBox(driver, textBoxName, textToBeSend);
		WebPageUtilities.selectValueFromDropdown(driver, dropdownName, valueToBeSelected);
		WebPageUtilities.buttonClickWithInAForm(driver, buttonName);
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("googleUrl"));
		ChromeBrowserSetup.getInstance().navigate(PropertyReader.getInstance().getProperty("way2automationUrl2"));
		new FormSubmit().interactWithinAForm();
		ChromeBrowserSetup.getInstance().quit();
	}
}

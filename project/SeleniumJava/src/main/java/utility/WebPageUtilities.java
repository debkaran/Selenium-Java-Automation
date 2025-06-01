package utility;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebPageUtilities {

	private WebPageUtilities() {

	}

	public static void writeTextIntoAFormTextBox(WebDriver driver, String textBoxName, String textToBeSend) {

		WebElement elemTextBox = driver.findElement(
				By.xpath("//div[contains(@style, 'visible')]//input[@name='" + textBoxName.toLowerCase() + "']"));
		if (elemTextBox.isDisplayed()) {
			System.out.println(textBoxName + " text box is displayed in UI");
			WaitUtilities.elementToBeClickable(driver, elemTextBox);
			elemTextBox.click();
			elemTextBox.clear();
			elemTextBox.sendKeys(textToBeSend);
			System.out.println(textToBeSend + " is entered into the text box");
		} else {
			System.out.println(textBoxName + " could not be displayed in UI");
		}
	}

	public static void buttonClickWithInAForm(WebDriver driver, String buttonName) {

		WebElement elemButton = driver.findElement(
				By.xpath("//div[contains(@style, 'visible')]//input[@type='" + buttonName.toLowerCase() + "']"));
		if (elemButton.isDisplayed()) {
			WaitUtilities.elementToBeClickable(driver, elemButton);
			elemButton.click();
			System.out.println(buttonName + " button is clicked in UI");
		} else {
			System.out.println(buttonName + " could not be displayed in UI");
		}
	}

	public static void selectValueFromDropdown(WebDriver driver, String dropdownName, String valueToBeSelected) {

		WebElement elemDropdown = driver.findElement(By.xpath("//select[@name='" + dropdownName.toLowerCase() + "']"));
		if (elemDropdown.isDisplayed()) {
			System.out.println(dropdownName + " dropdown is displayed in UI");
			Select dropdown = new Select(elemDropdown);
			dropdown.selectByValue(valueToBeSelected);
			System.out.println(dropdown.getFirstSelectedOption().getText() + " is selected from dropdown successfully");
		} else {
			System.out.println(dropdownName + " dropdown could not be displayed in UI");
		}
	}

	public static void selectChkBox(WebDriver driver, String checkBoxValue) {

		WebElement elemCheckBox = driver
				.findElement(By.xpath("//span[text()='" + checkBoxValue.substring(0, 1).toUpperCase()
						+ checkBoxValue.substring(1).toLowerCase() + "']/preceding-sibling::input[1]"));
		if (elemCheckBox.isDisplayed()) {
			if (!elemCheckBox.isSelected()) {
				elemCheckBox.click();
			}
			System.out.println("Check Box is selected in UI");
		}
	}

	public static void switchToFrame(WebDriver driver, WebElement elemFrame) {

		try {
			driver.switchTo().frame(elemFrame);
		} catch (NoSuchFrameException noSuchFrameExp) {
			System.out.println(noSuchFrameExp.getMessage());
			noSuchFrameExp.printStackTrace();
		}
	}

	public static void switchToFrame(WebDriver driver, String frameNameOrId) {

		try {
			driver.switchTo().frame(frameNameOrId);
		} catch (NoSuchFrameException noSuchFrameExp) {
			System.out.println(noSuchFrameExp.getMessage());
			noSuchFrameExp.printStackTrace();
		}
	}

	public static void windowResize(WebDriver driver, int xOffset, int yOffset) {

		System.out.println("Window size resized to : " + xOffset + " height and " + yOffset + " width");
		driver.manage().window().setSize(new Dimension(xOffset, yOffset));
	}

	public static void performRightClick(WebDriver driver, WebElement elemToBeRightClick) {

		new Actions(driver).contextClick(elemToBeRightClick).perform();
	}

	public static void acceptAlert(WebDriver driver) {

		try {
			Alert alert = driver.switchTo().alert();
			System.out.println("Driver switch to alert box");
			if (!alert.getText().isEmpty()) {
				System.out.println("Alert message : " + alert.getText());
			} else {
				System.out.println("No Alert message found in alert box!!!");
			}
			alert.accept();
			System.out.println("Alert accpted.");
		} catch (NoAlertPresentException noAlertPresent) {
			noAlertPresent.printStackTrace();
		}
	}

	public static void enterTextInTxtBox(WebDriver driver, WebElement elemTxtBox, String strText) {

		if (elemTxtBox.isDisplayed()) {
			WaitUtilities.elementToBeClickable(driver, elemTxtBox);
			elemTxtBox.click();
			elemTxtBox.clear();
			elemTxtBox.sendKeys(strText);
		}
	}

	public static String switchToLastWindow(WebDriver driver) {

		String window = null;
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Total Windows available is : " + windows.size());
		Iterator<String> iterate = windows.iterator();
		while (iterate.hasNext()) {
			window = iterate.next();
		}
		return window;
	}
}

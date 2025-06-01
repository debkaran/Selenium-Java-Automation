package uiinteraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.ConvertionUtils;
import utility.WaitUtilities;
import utility.WebPageUtilities;

public class ReadOperatorAndCalculateTwoValues {

	@FindBy(how = How.XPATH, using = "//form/table/tbody/tr[1]/td[@class='normtxt']")
	private WebElement elemHeaderText;
	@FindBy(how = How.ID, using = "mathq2")
	private WebElement elemMathQns;
	@FindBy(how = How.XPATH, using = "//input[@type='text']")
	private WebElement elemTxtBox;
	@FindBy(how = How.XPATH, using = "//div[@pg='Vote']")
	private WebElement elemVoteBtn;

	private static WebDriver driver = null;

	private String checkBoxValue = "No", answer;

	private ReadOperatorAndCalculateTwoValues(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	private void calculateNumbersAndWriteIntoATextBox() {

		readTextFromUI();
		WebPageUtilities.selectChkBox(driver, checkBoxValue);
		if (elemMathQns.isDisplayed()) {
			String operator = elemMathQns.getText().split(" ")[1];
			int num1 = Integer.parseInt(elemMathQns.getText().split(" ")[0]);
			int num2 = Integer.parseInt(elemMathQns.getText().split(" ")[2]);
			answer = String.valueOf(ConvertionUtils.calculateTwoNumber(operator, num1, num2));
			System.out.println("After did " + operator + " operation of the number " + num1 + " and " + num2
					+ ", answer is " + answer);
			if (elemTxtBox.isDisplayed()) {
				WaitUtilities.elementToBeClickable(driver, elemTxtBox);
				elemTxtBox.click();
				elemTxtBox.clear();
				elemTxtBox.sendKeys(answer);
				System.out.println("Answer successfully send into the text box as " + answer);
				if (elemVoteBtn.isDisplayed()) {
					WaitUtilities.elementToBeClickable(driver, elemVoteBtn);
					elemVoteBtn.click();
					System.out.println("Successfully clicked into the Vote button");
				} else {
					System.out.println("Vote button could not be displayed in UI");
				}
			} else {
				System.out.println("Text box could not be displayed in UI");
			}
		} else {
			System.out.println("Math question could not be displayed in UI");
		}
	}

	private void readTextFromUI() {

		if (elemHeaderText.isDisplayed()) {
			System.out.println("Header part is displayed in UI as : " + elemHeaderText.getText());
		} else {
			System.out.println("Header part could not be displayed in UI : " + elemHeaderText.getText());
		}
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().navigate(PropertyReader.getInstance().getProperty("timesOfIndiaUrl"));
		new ReadOperatorAndCalculateTwoValues(driver).calculateNumbersAndWriteIntoATextBox();
		ChromeBrowserSetup.getInstance().quit();
	}
}

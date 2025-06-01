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

public class TestRightClick {

	@FindBy(how = How.CSS, using = "img[src='data-samples/images/popup_pic.gif']")
	private WebElement elemRightClickImg;
	@FindBy(how = How.CSS, using = "table[id='dm2m1tbl'] > tbody > tr:nth-child(2) > td > table > tbody > tr > td:first-child")
	private WebElement elemProductInfo;
	@FindBy(how = How.CSS, using = "table[id='dm2m2tbl'] > tbody > tr:nth-child(2) > td > table > tbody > tr > td:first-child")
	private WebElement elemInstallation;
	@FindBy(how = How.CSS, using = "table[id='dm2m3tbl'] > tbody > tr:nth-child(2) > td > table > tbody > tr > td")
	private WebElement elemHowToSetup;

	private static WebDriver driver = null;

	private TestRightClick() {
		PageFactory.initElements(driver, this);
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("rightClick"));
		new TestRightClick().doRightClick();
	}

	private void doRightClick() {

		WebPageUtilities.performRightClick(driver, elemRightClickImg);
		if (elemProductInfo.isDisplayed()) {
			WaitUtilities.elementToBeClickable(driver, elemProductInfo);
			elemProductInfo.click();
			if (elemInstallation.isDisplayed()) {
				WaitUtilities.elementToBeClickable(driver, elemInstallation);
				elemInstallation.click();
				if (elemHowToSetup.isDisplayed()) {
					WaitUtilities.elementToBeClickable(driver, elemHowToSetup);
					elemHowToSetup.click();
				}
			}
		}
	}
}

package uiinteraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WebPageUtilities;

public class TestSliders {

	@FindBy(how = How.CSS, using = "span.ui-slider-handle.ui-corner-all.ui-state-default")
	private WebElement elemSlider;
	@FindBy(how = How.CSS, using = "div#slider")
	private WebElement elemMainSlider;
	@FindBy(how = How.CLASS_NAME, using = "demo-frame")
	private WebElement elemFrame;

	private static WebDriver driver = null;

	private TestSliders(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	private void performSlider(int xOffset, int yOffset) {

		WebPageUtilities.switchToFrame(driver, elemFrame);
		if (elemMainSlider.isDisplayed()) {
			int width = elemMainSlider.getSize().getWidth() / 2;
			if (elemSlider.isDisplayed()) {
				new Actions(driver).dragAndDropBy(elemSlider, xOffset, yOffset).perform();
				System.out.println("Slider moved by " + xOffset + " to xOffset and " + yOffset + " to yOffset");
				new Actions(driver).dragAndDropBy(elemSlider, width, yOffset).perform();
				System.out.println("Slider moved by " + width + " to xOffset and " + yOffset + " to yOffset");
			}
		}
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("jqueryslider"));
		new TestSliders(driver).performSlider(100, 0);
	}
}

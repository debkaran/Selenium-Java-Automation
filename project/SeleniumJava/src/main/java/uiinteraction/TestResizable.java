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

public class TestResizable {

	@FindBy(how = How.CSS, using = ".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se")
	private WebElement elemResizable;
	@FindBy(how = How.CLASS_NAME, using = "demo-frame")
	private WebElement elemFrame;

	private static WebDriver driver = null;

	private TestResizable(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	private void performResize(int xOffset, int yOffset) {

		WebPageUtilities.switchToFrame(driver, elemFrame);
		if (elemResizable.isDisplayed()) {
//			int width = elemResizable.getSize().width / 2;
//			int hight = elemResizable.getSize().height / 2;
			new Actions(driver).dragAndDropBy(elemResizable, xOffset, yOffset).perform();
			System.out.println("Slider moved by " + xOffset + " to xOffset and " + yOffset + " to yOffset");
		}
	}
	
	private void performResize() {

		WebPageUtilities.switchToFrame(driver, elemFrame);
		if (elemResizable.isDisplayed()) {
			int width = elemResizable.getSize().width / 2;
			int hight = elemResizable.getSize().height / 2;
			new Actions(driver).dragAndDropBy(elemResizable, width, hight).perform();
			System.out.println("Slider moved by " + width + " to xOffset and " + hight + " to yOffset");
		}
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("jqueryresize"));
		new TestResizable(driver).performResize(200, 200);
		new TestResizable(driver).performResize();
		WebPageUtilities.windowResize(driver, driver.manage().window().getSize().getWidth() / 2,
				driver.manage().window().getSize().getHeight() / 2);
	}
}

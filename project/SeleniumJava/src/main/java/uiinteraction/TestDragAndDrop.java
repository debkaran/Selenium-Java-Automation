package uiinteraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WebPageUtilities;

public class TestDragAndDrop {

	@FindBy(how = How.ID, using = "draggable")
	private WebElement elemDraggable;
	@FindBy(how = How.ID, using = "droppable")
	private WebElement elemDroppable;
	@FindBy(how = How.CLASS_NAME, using = "demo-frame")
	private WebElement elemFrame;

	private static WebDriver driver = null;

	private TestDragAndDrop() {
		PageFactory.initElements(driver, this);
	}

//	public static void main(String[] args) {
//
//		driver = ChromeBrowserSetup.getInstance().setup();
//		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("jquerydroppable"));
//		new TestDragAndDrop().dragAndDrop();
//	}

	@Test
	private void dragAndDrop() {

		WebPageUtilities.switchToFrame(driver, elemFrame);
		new Actions(driver).dragAndDrop(elemDraggable, elemDroppable).perform();
	}
}

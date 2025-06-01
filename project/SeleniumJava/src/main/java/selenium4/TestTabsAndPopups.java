package selenium4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;
import utility.WebPageUtilities;

public class TestTabsAndPopups {

	@FindBy(how = How.XPATH, using = "//a[2]/span[text()='Sign in']")
	private static WebElement elemSingin;

	private static WebDriver driver = null;

	private TestTabsAndPopups() {
		PageFactory.initElements(driver, this);
	}

	public static void main(String[] args) {

		driver = ChromeBrowserSetup.getInstance().setup();
		driver.get(PropertyReader.getInstance().getProperty("googleUrl"));
		System.out.println("First Window : " + driver.getTitle());

		// creating a new tab and switch to it
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(PropertyReader.getInstance().getProperty("gmail"));
		System.out.println("Second Window : " + driver.getTitle());

		new TestTabsAndPopups();
		elemSingin.click();
//		driver.switchTo().window(WebPageUtilities.switchToLastWindow(driver));
		System.out.println("Thrid Window : " + driver.getTitle());

		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get(PropertyReader.getInstance().getProperty("way2automationUrl1"));
		System.out.println("Fourth Window : " + driver.getTitle());

		driver.close();

		driver.switchTo().window(WebPageUtilities.switchToLastWindow(driver));

		driver.close();

		driver.quit();
	}

}

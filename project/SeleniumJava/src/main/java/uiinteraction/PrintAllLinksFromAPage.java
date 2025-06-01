package uiinteraction;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import browsertesting.ChromeBrowserSetup;
import config.PropertyReader;

public class PrintAllLinksFromAPage {

//	@FindBy(how = How.XPATH, using = "//textarea[@title='Search']")
//	private WebElement elemGoogleSearchBar;
	@FindBy(how = How.XPATH, using = "//a | //a[contains(text(), 'Read More') and contains(@href, 'http')]")
	private List<WebElement> lstOfAllTheLink;

	private static WebDriver driver = null;

	private PrintAllLinksFromAPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	private void printAllLinksAndTotalCount() {

//		openWebsite("way2automation", 1);
		getTheTotalLinksCountFromAPage();
		getAllTheLinksAndTheText();
	}

//
//	private void openWebsite(String nameOfWebSite, int linkNumber) {
//
//		List<WebElement> webSiteLink = chromeDriver.findElements(By
//				.xpath("//h3[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
//						+ nameOfWebSite + "')]/.."));
//		if (webSiteLink.get(linkNumber - 1).isDisplayed()) {
//			webSiteLink.get(linkNumber - 1).click();
//		}
//	}

	private int getTheTotalLinksCountFromAPage() {

		System.out.println("Total links are present in UI is : " + lstOfAllTheLink.size());
		return lstOfAllTheLink.size();
	}

	private List<String> getAllTheLinksAndTheText() {

		List<String> temp = new ArrayList<>();
		for (WebElement elemText : lstOfAllTheLink) {
			System.out.println("Links name : " + elemText.getText() + " and URL : " + elemText.getAttribute("href"));
			temp.add("Links name : " + elemText.getText() + " and URL : " + elemText.getAttribute("href"));
		}
		return temp;
	}

	public static void main(String[] args) throws InterruptedException {

		driver = ChromeBrowserSetup.getInstance().setup();
		ChromeBrowserSetup.getInstance().get(PropertyReader.getInstance().getProperty("googleUrl"));
		ChromeBrowserSetup.getInstance().navigate(PropertyReader.getInstance().getProperty("way2automationUrl1"));
		new PrintAllLinksFromAPage(driver).printAllLinksAndTotalCount();
		ChromeBrowserSetup.getInstance().quit();
	}
}

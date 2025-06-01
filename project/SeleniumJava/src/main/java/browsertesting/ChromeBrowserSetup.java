package browsertesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.WaitUtilities;

public class ChromeBrowserSetup {

	private static ChromeBrowserSetup chromeBrowser = new ChromeBrowserSetup();
	
	private static WebDriver chromeDriver;

	private ChromeBrowserSetup() {
		
	}
	
	public static ChromeBrowserSetup getInstance() {
		
		return chromeBrowser;
	}
	
	public WebDriver getDriver() {
		
		return chromeDriver;
	}

	public WebDriver setup() {

//		System.setProperty("webdriver.chrome.driver", "/Users/debkaransinghania/eclipse-workspace/SeleniumJavaFramework/drivers/chromedriver/chromedriver_mac_arm64/chromedriver");
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().window().maximize();
		WaitUtilities.implicitWait(chromeDriver);
		return chromeDriver;
	}
	
	public void get(String url) {
		
		chromeDriver.get(url);
	}
	
	public void close() {
		
		chromeDriver.close();
	}
	
	public void quit() {
		
		chromeDriver.quit();
	}
	
	public void navigate(String url) {

		chromeDriver.navigate().to(url);
	}
}

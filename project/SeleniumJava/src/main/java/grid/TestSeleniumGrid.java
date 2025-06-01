package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import config.PropertyReader;
import utility.ExcelReadWrite;

public class TestSeleniumGrid {

	@Test(dataProvider = "getData")
	public void testLogin(Hashtable<String, String> table) throws MalformedURLException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();
		if (table.get("Browser").equalsIgnoreCase("chrome")) {
//			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
		} else if (table.get("Browser").equalsIgnoreCase("safari")) {
//			cap = DesiredCapabilities.safari();
			cap.setBrowserName("safari");
			cap.setPlatform(Platform.ANY);
		}

		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
		driver.manage().window().maximize();
		driver.get(PropertyReader.getInstance().getProperty("gmail"));
		Thread.sleep(3000);
		driver.findElement(By.id("identifierId")).sendKeys(table.get("Username") + table.get("Password"));
		driver.findElement(By.cssSelector("div[id='identifierNext'] button span")).click();

		driver.quit();
	}

	@DataProvider(parallel = true)
	public Object[][] getData() {

		String filePath = "seleniumgrid.xlsx";
		Object[][] excelData = null;
		Hashtable<String, String> table = null;

		List<List<String>> list = ExcelReadWrite.readExcel(filePath);

		excelData = new Object[list.size() - 1][1];

		for (int i = 1; i < list.size(); i++) {
			table = new Hashtable<>();

			List<String> row = list.get(i);

			for (int j = 0; j < list.get(0).size(); j++) {
				table.put(list.get(0).get(j), row.get(j));
			}

			excelData[i - 1][0] = table;
		}

		return excelData;
	}
}

package testng;

import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTesting {

//	@Parameters({ "browser" })
//	@Test
//	public void doLogin(String browser) throws InterruptedException {
//
//		Date date = new Date();
//		System.out.println("Browser : " + browser + ", date : " + date);
//		Thread.sleep(2000);
//	}

	@Test(dataProvider = "getData")
	public void doTest(String browser) throws InterruptedException {

		Date date = new Date();
		System.out.println("Browser : " + browser + ", date : " + date);
		Thread.sleep(2000);
	}

	@DataProvider(parallel = true)
	public Object[][] getData() {

		Object data[][] = new Object[2][1];
		data[0][0] = "Chrome";
		data[1][0] = "Firefox";

		return data;
	}
}

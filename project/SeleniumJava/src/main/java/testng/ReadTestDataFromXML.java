package testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadTestDataFromXML {

	@Parameters({ "browser", "env" })
	@Test
	public void doTest(String browser, String env) {

		System.out.println("Browser is : " + browser + "-----Env : " + env);
	}
}

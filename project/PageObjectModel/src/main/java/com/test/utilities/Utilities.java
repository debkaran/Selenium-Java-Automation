package com.test.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.test.base.Page;

public class Utilities extends Page {

	public static String screenshotName;

	/**
	 * Captures a screenshot of the current browser window and saves it to the
	 * /target/surefire-reports/html/screenshots directory.
	 *
	 * @throws IOException if an error occurs while saving the screenshot file to
	 *                     the target directory
	 */
	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		screenshotName = "screenshots/" + d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));
	}

	/**
	 * Provides test data to TestNG test methods by reading from an Excel file. This
	 * data provider dynamically determines the Excel sheet name based on the test
	 * method name. It reads the sheet content and returns a two-dimensional Object
	 * array, where each row contains a Hashtable representing a set of key-value
	 * pairs (column name to cell value) for a test iteration.
	 *
	 * For example, if the test method is named loginTest, it will read from the
	 * "LoginTest" sheet in testdata.xlsx.
	 *
	 * @param m the Method object representing the current test method (used to
	 *          derive the sheet name)
	 * @return a two-dimensional Object[][] array where each element is a single
	 *         Hashtable of test data for one iteration
	 */
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String fileName = "src/test/resources/com/test/excel/testdata.xlsx";
		String sheetName = m.getName().substring(0, 1).toUpperCase() + m.getName().substring(1, m.getName().length());
		Object[][] excelData = null;
		Hashtable<String, String> table = null;

		List<List<String>> list = ExcelReader.readExcel(fileName, sheetName);

		System.out.println(list);
		System.out.println("Number of columns present is : " + list.get(0).size());

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

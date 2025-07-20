package com.test.utilities;

import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static String fileName;

	public static String getFileName() {
		return fileName;
	}

	/**
	 * Returns a singleton instance of ExtentReports for generating HTML test
	 * reports.
	 *
	 * @return a singleton instance of ExtentReports
	 */
	public static ExtentReports getInstance() {

		if (extent == null) {
			extent = new ExtentReports();
			Calendar cal = Calendar.getInstance();
			fileName = System.getProperty("user.dir") + "/reports/Extent" + "_" + cal.get(Calendar.YEAR) + "_"
					+ cal.get(Calendar.DATE) + "_" + (cal.get(Calendar.MONTH) + 1) + "_" + cal.get(Calendar.HOUR) + "_"
					+ cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND) + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);

			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setDocumentTitle(fileName);
			reporter.config().setEncoding("utf-8");
			reporter.config().setReportName(fileName);

			extent.attachReporter(reporter);
			extent.setSystemInfo("Automation Tester", "Deb Karan Singhania");
			extent.setSystemInfo("Organization", "My Org");
			extent.setSystemInfo("Build No", "1234");

			return extent;
		}

		return extent;
	}
}

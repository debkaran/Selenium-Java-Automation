package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports createInstance(String fileName) {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
		
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle(fileName);
		reporter.config().setEncoding("utf-8");
		reporter.config().setReportName(fileName);
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Deb Karan Singhania");
		extent.setSystemInfo("Organization", "My Org");
		extent.setSystemInfo("Build No", "1234");
		
		return extent;
	}
}

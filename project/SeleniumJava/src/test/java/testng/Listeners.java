package testng;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import config.TestConfig;
import reporting.ExtentManager;
import sendingmails.MonitoringMail;
import utility.CapturingScreenshot;
import utility.CreateZipFile;

public class Listeners implements ITestListener {

	Calendar cal = Calendar.getInstance();
	String fileName = System.getProperty("user.dir") + "/reports/Extent" + "_" + cal.get(Calendar.YEAR) + "_"
			+ cal.get(Calendar.DATE) + "_" + (cal.get(Calendar.MONTH) + 1) + "_" + cal.get(Calendar.HOUR) + "_"
			+ cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND) + ".html";
	private ExtentReports extent = ExtentManager.createInstance(fileName);
	private ThreadLocal<ExtentTest> testReports = new ThreadLocal<>();

	/**
	 * Invoked each time before a test will be invoked. The <code>ITestResult</code>
	 * is only partially filled with the references to class, method, start millis
	 * and status.
	 *
	 * @param result the partially filled <code>ITestResult</code>
	 * @see ITestResult#STARTED
	 */
	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + " @TestCase: " + result.getMethod().getMethodName());
		testReports.set(test);
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	@Override
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		CapturingScreenshot.captureScreenshot(methodName);
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "<b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReports.get().pass(m);
	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	@Override
	public void onTestFailure(ITestResult result) {

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReports.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred: Click to see"
						+ "</font>" + "</b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		String methodName = result.getName();
		CapturingScreenshot.captureScreenshot(methodName);

		String failureLog = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		testReports.get().log(Status.FAIL, m);
		System.out.println(result.getThrowable().toString());
	}

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	@Override
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " SKIPPED" + "<b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.CYAN);
		testReports.get().skip(m);
		System.out.println(result.getThrowable().toString());
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	/**
	 * Invoked each time a test fails due to a timeout.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 */
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	@Override
	public void onStart(ITestContext context) {

		try {
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/reports"));
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/screenshot"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}
		try {
			CreateZipFile.createZipFromFolder(System.getProperty("user.dir") + "/reports",
					System.getProperty("user.dir") + "/zip/Report.zip");
			CreateZipFile.createZipFromFolder(System.getProperty("user.dir") + "/screenshot",
					System.getProperty("user.dir") + "/zip/Screenshot.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
				Arrays.asList(System.getProperty("user.dir") + "/zip/Screenshot.zip",
						System.getProperty("user.dir") + "/zip/Report.zip"));

	}
}

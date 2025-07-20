package com.test.listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.test.base.Page;
import com.test.utilities.ExtentManager;
import com.test.utilities.MonitoringMail;
import com.test.utilities.TestConfig;
import com.test.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	/**
	 * Invoked each time before a test will be invoked. The <code>ITestResult</code>
	 * is only partially filled with the references to class, method, start millis
	 * and status.
	 *
	 * @param result the partially filled <code>ITestResult</code>
	 * @see ITestResult#STARTED
	 */
	public void onTestStart(ITestResult result) {

		extentTest = report
				.createTest(result.getTestClass().getName() + " @TestCase: " + result.getMethod().getMethodName());
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, result.getName().toUpperCase() + "PASS");
		report.flush();
	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result) {

		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {

			e.printStackTrace();
		}

		extentTest.log(Status.FAIL, result.getName().toUpperCase() + " Failed with exception : "
				+ Arrays.asList(result.getThrowable().getStackTrace()));
		extentTest.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/target/surefire-reports/html/" + Utilities.screenshotName);
		report.flush();
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log(result.getName().toUpperCase() + "Failed with exception : " + result.getThrowable());
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
				+ " height=200 width=200></img></a");
		Reporter.log("<br>");
	}

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	public void onTestSkipped(ITestResult result) {
		// not implemented
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
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked each time a test fails due to a timeout.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	public void onStart(ITestContext context) {

	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	public void onFinish(ITestContext context) {
		// not implemented
	}

	/**
	 * This method is invoked before the SuiteRunner starts.
	 *
	 * @param suite The suite
	 */
	public void onStart(ISuite suite) {
		// not implemented
	}

	/**
	 * This method is invoked after the SuiteRunner has run all the tests in the
	 * suite.
	 *
	 * @param suite The suite
	 */
	public void onFinish(ISuite suite) {

		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
				Arrays.asList(ExtentManager.getFileName()));
	}
}

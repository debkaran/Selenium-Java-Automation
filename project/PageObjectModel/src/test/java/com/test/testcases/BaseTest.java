package com.test.testcases;

import org.testng.annotations.AfterSuite;

import com.test.base.Page;

public class BaseTest {

	/**
	 * Quits the WebDriver instance if it is active and logs the completion of the
	 * test execution.
	 */
	@AfterSuite
	public void tearDown() {
		Page.quit();
	}
}

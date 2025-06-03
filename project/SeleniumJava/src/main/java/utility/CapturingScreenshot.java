package utility;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import browsertesting.ChromeBrowserSetup;

public class CapturingScreenshot {

	public static void captureScreenshot(String methodName) {
		GregorianCalendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);

		try {
			File scrFile = ((TakesScreenshot) ChromeBrowserSetup.getInstance().getDriver())
					.getScreenshotAs(OutputType.FILE);
			String screenshotPath = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + year + "_"
					+ date + "_" + (month + 1) + "_" + day + "_" + min + "_" + sec + ".jpeg";
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package sendingmails;

import java.util.Arrays;

import config.TestConfig;

public class TestMail {

	private TestMail() {

	}

	public static void main(String[] args) {

		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
				Arrays.asList(System.getProperty("user.dir") + "/zip/Screenshot.zip",
						System.getProperty("user.dir") + "/zip/Report.zip"));
	}
}

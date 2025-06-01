package sendingmails;

import config.TestConfig;

public class TestMail {

	private TestMail() {

	}

	public static void main(String[] args) {

		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
				TestConfig.attachmentPath, TestConfig.attachmentName);
	}
}

package com.test.pages;

import org.openqa.selenium.By;

import com.test.base.Page;

public class HomePage extends Page {

	public void goToSignUp() {

		driver.findElement(By.xpath("//div/a[text()='Sign Up']"));
	}

	public LoginPage goToLogin() {

		click("loginlink_xpath");
		return new LoginPage();
	}

	public void goToZohoEdu() {

	}

	public void goToLearnMore() {

	}

	public void goToSupport() {

	}

	public void validateFooterLinks() {

	}
}

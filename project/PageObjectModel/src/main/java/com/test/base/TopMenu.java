package com.test.base;

import org.openqa.selenium.WebDriver;

import com.test.pages.crm.accounts.AccountsPage;

public class TopMenu {

	private WebDriver driver;

	TopMenu(WebDriver driver) {
		this.driver = driver;
	}

	public void gotoHome() {

	}

	public void gotoLeeds() {

	}

	public AccountsPage gotoAccounts() {

		Page.click("accountstab_CSS");
		System.out.println("Successfully navigated to : " + driver.getTitle());

		return new AccountsPage();
	}

	public void gotoContacts() {

	}

	public void signOut() {

	}
}

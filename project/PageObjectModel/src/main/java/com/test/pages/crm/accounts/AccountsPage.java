package com.test.pages.crm.accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.test.base.Page;

public class AccountsPage extends Page {

	public CreateAccountPage gotoCreateAccounts() {

		try {
			click("createaccountbtn_CSS");
		} catch (StaleElementReferenceException staleElemRefExp) {
			WebElement createAccount = driver.findElement(By.cssSelector("button[aria-label='Create Account']"));
			createAccount.click();
		}

		return new CreateAccountPage();
	}

	public void gotoImportAccounts() {

	}
}

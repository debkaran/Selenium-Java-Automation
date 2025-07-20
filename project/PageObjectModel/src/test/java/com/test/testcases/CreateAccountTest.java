package com.test.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.test.base.Page;
import com.test.pages.crm.accounts.AccountsPage;
import com.test.pages.crm.accounts.CreateAccountPage;
import com.test.utilities.Utilities;

public class CreateAccountTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String> data) {

		AccountsPage account = Page.menu.gotoAccounts();

		CreateAccountPage cap = account.gotoCreateAccounts();
		cap.createAccount(data.get("accountname"));
	}
}

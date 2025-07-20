package com.test.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.test.base.Page;
import com.test.pages.HomePage;
import com.test.pages.LoginPage;
import com.test.pages.ZohoAppPage;
import com.test.pages.crm.accounts.AccountsPage;
import com.test.pages.crm.accounts.CreateAccountPage;
import com.test.utilities.Utilities;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) {

		HomePage home = new HomePage();

		LoginPage login = home.goToLogin();

		ZohoAppPage zp = login.doLogin(data.get("username"), data.get("password"));
		zp.gotoCRM();
	}

	public static void main(String[] args) {

		HomePage home = new HomePage();
//		home.goToSignUp();
		LoginPage login = home.goToLogin();

		ZohoAppPage zp = login.doLogin("debkaransinghania@gmail.com", "pas$M0rd");
		zp.gotoCRM();

		AccountsPage account = Page.menu.gotoAccounts();

		CreateAccountPage cap = account.gotoCreateAccounts();
		cap.createAccount("Raman");
	}
}

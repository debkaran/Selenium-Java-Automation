package com.test.pages.crm.accounts;

import com.test.base.Page;

public class CreateAccountPage extends Page {

	public void createAccount(String accountName) {

		type("accountname_CSS", accountName);
	}
}

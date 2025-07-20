package com.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.test.base.Page;

public class ZohoAppPage extends Page {

	public void gotoChat() {

	}

	public void gotoCRM() {

		driver.findElement(By.id("all-apps")).click();
		click("crmlink_CSS");
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
		System.out.println("Successfully switched to tab : " + driver.getTitle());
	}

	public void gotoSalesIQ() {

	}
}

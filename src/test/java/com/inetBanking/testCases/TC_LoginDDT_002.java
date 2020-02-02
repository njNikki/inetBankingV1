package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();
			logger.warn("Login passed");
		}
	}

	public boolean isAlertPresent()// user defined method created to check alert present or not
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int cocount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][cocount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < cocount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		return logindata;
	}

}

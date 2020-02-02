package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException 
	{
	
	LoginPage lp= new LoginPage(driver);
	lp.setUserName(username);
	logger.info("Username is entered");
	
	lp.setPassword(password);
	logger.info("Password is entered");
	lp.clickSubmit();
	
	if(driver.getTitle().equals("Guru99 aBank Manager HomePage"))
			{
		Assert.assertTrue(true);
		logger.info("TestCase passed");
			}
	else {
		
		captureScreen(driver,"loginTest");
		Assert.assertTrue(false);
		logger.info("TestCase Failed");
		
	}
			
	}
	
}

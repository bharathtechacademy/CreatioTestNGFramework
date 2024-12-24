package com.application.tests;

import org.testng.annotations.Test;

import com.framework.base.BaseTest;

public class ApplicationTest extends BaseTest{

	@Test(priority = 1)
	public void verifyLoginPageHeader() {
		loginPage.launchApplication();
		loginPage.verifyLoginHeader();
	}

	@Test(priority = 2,dataProvider="data")
	public void verifyLoginFeature(String username, String password) {
		loginPage.launchApplication();
		loginPage.enterCredentials(username, password);
		loginPage.clickOnLoginButton();
		homePage.verifyLoginIsSuccessful();
	}
	
	@Test(priority = 3,dataProvider="data")
	public void verifySignUpFeature(String username, String password) {
		loginPage.launchApplication();
		loginPage.clickOnSignUpLink();
		signupPage.verifySignUpPageIsLaunched();
		signupPage.enterUserDetails(username, password);
		signupPage.clickOnContinueButton();
		signupPage.enterCompanyDetails();
		signupPage.clickOnSignUpButton();
		homePage.verifySignUpIsSuccessful();
	}

}

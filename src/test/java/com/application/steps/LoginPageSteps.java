package com.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.elements.LoginPageElements;

public class LoginPageSteps extends LoginPageElements{
	
	public LoginPageSteps(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void launchApplication() {		
		PageFactory.initElements(driver,this);
		launchApplication(prop.getProperty("url"));
		Assert.assertEquals(getTitle(), prop.getProperty("title"));
		log("pass","Application is launched successfully");
	}
	
	public void verifyLoginHeader() {
		waitForElement(loginPageHeader, 30);
		Assert.assertEquals(getElementText(loginPageHeader), prop.get("loginHeader"));
		log("pass","Application Login Header is displayed as expected");
	}
	
	public void enterCredentials(String username, String password) {
		waitForElement(businessEmailTxtb, 30);
		enterText(businessEmailTxtb, username);
		enterText(passwordTxtb, password);
		log("info","Credentials are entered successfully");
	}
	
	public void clickOnLoginButton() {
		click(loginBtn);
		log("info","Clicked on the Login Button");
	}
	
	public void clickOnSignUpLink() {
		waitForElement(signUpLink, 30);
		jsClick(signUpLink);
		log("info","Clicked on the SignUp Link");
	}	

}

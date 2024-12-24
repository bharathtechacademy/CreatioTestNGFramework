package com.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.application.elements.SignUpPageElements;

public class SignUpPageSteps extends SignUpPageElements{
	
	public SignUpPageSteps(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void verifySignUpPageIsLaunched() {
		waitForElement(signUpPageHeader, 30);
		log("pass","Application Signup page is launched successfully");
	}
	
	public void enterUserDetails(String email, String password) {
		enterText(businessEmailTxtb, email);
		enterText(passwordTxtb, password);
		log("info","User details are entered successfully");
	}
	
	public void clickOnContinueButton() {
		click(continueBtn);
		log("info","Clicked on the Login Button");
	}
	
	public void enterCompanyDetails() {
		waitForElement(fullNameTxtb, 30);
		enterText(fullNameTxtb, "BharathTechAcademy");
		enterText(companyTxtb, "BharathTechAcademy");
		enterText(countryTxtb, "India");
		waitForElement(countryOption,30);
		click(countryOption);
		wait(5);
		click(phoneTxtb);
		enterText(phoneTxtb, "90"+uniqueId("ddMMhhmm"));
		log("info","Updated Company Details");
	}
	
	public void clickOnSignUpButton() {
		jsClick(SignUpBtn);
		log("info","Clicked on the SignUp Button");
	}

}

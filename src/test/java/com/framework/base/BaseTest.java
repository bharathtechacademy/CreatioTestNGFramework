package com.framework.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.application.steps.HomePageSteps;
import com.application.steps.LoginPageSteps;
import com.application.steps.SignUpPageSteps;
import com.framework.utilities.ReadExcel;

public class BaseTest extends BasePage{

	public LoginPageSteps loginPage = null;
	public HomePageSteps homePage = null;
	public SignUpPageSteps signupPage = null;

	@BeforeMethod(alwaysRun=true,dependsOnMethods="setupBrowser")
	public void initializePages() {
		WebDriver driver = new BasePage().getDriver();
		loginPage = new LoginPageSteps(driver);
		homePage = new HomePageSteps(driver);
		signupPage = new SignUpPageSteps(driver);
	}
	
	@DataProvider(name="data")
	public String [][] testData(Method method){
		String [][] data = ReadExcel.readData(System.getProperty("user.dir")+"\\TestData\\TestData.xlsx", method.getName());
		return data;
	}	

}

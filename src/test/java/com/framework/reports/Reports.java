package com.framework.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	// This class will have common functions related to test results report using extent reports
	
	public static ExtentHtmlReporter html = null;
	public static ExtentReports extent = null;
	public static ExtentTest logger = null;
	
	@BeforeSuite(alwaysRun=true)
	public void setupReport() {
		html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html"); //creating empty report
		extent = new ExtentReports(); //initializing reports
		extent.attachReporter(html);
	}	
	
	public void startReporting(String testcaseName) {
		logger =extent.createTest(testcaseName);
	}
	
	@AfterSuite(alwaysRun=true)
	public void stopReporting() {
		extent.flush();
	}

}

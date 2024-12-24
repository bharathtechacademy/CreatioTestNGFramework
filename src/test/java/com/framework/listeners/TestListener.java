package com.framework.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.base.BasePage;
import com.framework.commons.WebCommons;
import com.framework.reports.Reports;

public class TestListener extends Reports implements ITestListener {
	
	// This class will have common functions related to testNG listeners to monitor events during execution process

	public void onTestStart(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		startReporting(testcaseName);
		logger.info("Test Execution Started for Test Case "+testcaseName);
	}

	public void onTestSuccess(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		logger.pass("Test Execution is Successful for Test Case "+testcaseName);
		stopReporting();
	}

	public void onTestFailure(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		logger.fail("Test Execution is Failed for Test Case "+testcaseName);
		logger.fail("Test Execution is Failed for Test Case due to "+result.getThrowable().getMessage());
		try {
			logger.addScreenCaptureFromPath(WebCommons.takeWindowScreenshot(new BasePage().getDriver(), testcaseName));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		stopReporting();
	}

}

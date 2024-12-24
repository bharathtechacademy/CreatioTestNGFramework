package com.application.tests;

import java.io.IOException;

import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.testng.annotations.Test;

import com.framework.commons.JmeterCommons;

public class PerformanceTest extends JmeterCommons{
	
	@Test
	public void verifyPerformanceOfApp() throws IOException, ConfigurationException, GenerationException {
		validateApiPerformance("GithubTestPlan.jmx");
	}

}

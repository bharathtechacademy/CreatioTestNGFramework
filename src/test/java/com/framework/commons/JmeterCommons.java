package com.framework.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;

public class JmeterCommons {
	
	
	//method to verify performance of application
	public void validateApiPerformance(String jmeterTestPlanName) throws IOException, ConfigurationException, GenerationException {
		String jmeterHome = "src/test/resources/jmeter";
		String testplanPath = Paths.get(jmeterHome, jmeterTestPlanName).toString();
		String resultsPath = Paths.get(jmeterHome, "results", "TestResults.csv").toString();
		String propertyFilePath = Paths.get(jmeterHome, "bin", "jmeter.properties").toString();
		
		//Setup JMETER home directory
		JMeterUtils.setJMeterHome(jmeterHome);
		
		//Load and configure JMETER Properties
		JMeterUtils.loadJMeterProperties(propertyFilePath);
		
		//Load JMETER test plan file 
		HashTree testplan = SaveService.loadTree(new File(testplanPath));
		
		//Configure results folder to generate test results
		ResultCollector results = new ResultCollector();
		results.setFilename(resultsPath);
		
		//Add test results to results file
		testplan.add(testplan.getArray()[0],results);
		
		//Run the scripts
		StandardJMeterEngine jmeter = new StandardJMeterEngine();
		jmeter.configure(testplan);
		jmeter.run();
		
		//Generate Html Report
		ReportGenerator report = new ReportGenerator(resultsPath,null);
		report.generate();		
		
	}
	

}

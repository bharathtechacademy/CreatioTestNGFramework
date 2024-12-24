package com.application.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.framework.utilities.ReadDB;

public class DBTest {
	
	
	@Test
	public void printDBData() {
		List<Map<String,String>> data = ReadDB.readData("Select * From products");
		
		System.out.println(data.get(0).get("product_name"));
		for( Map<String,String> record : data) {
			System.out.println(record);
		}
		
	}

}

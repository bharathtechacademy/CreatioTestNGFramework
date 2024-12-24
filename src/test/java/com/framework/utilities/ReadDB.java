package com.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadDB {
	
	
	public static ResultSet readDb(String query) {		
		ResultSet dataSet = null;
		Properties prop = ReadProp.readData("Config.properties");
		String url = prop.getProperty("DbConnectionUrl");
		String username = prop.getProperty("DbUsername");
		String password = prop.getProperty("DbPassword");
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			dataSet = connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return dataSet;		
	}
	
	
	public static  List<Map<String,String>> readData (String query){
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		ResultSet dataSet = readDb(query);
		
		try {
			while(dataSet.next()) {
				Map<String,String> record = new HashMap<String,String>();
				for (int col =1; col <=dataSet.getMetaData().getColumnCount() ;col++) {
					String columnName = dataSet.getMetaData().getColumnName(col);
					String columnValue = dataSet.getString(col);
					record.put(columnName, columnValue);					
				}
				data.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;		
	}

}

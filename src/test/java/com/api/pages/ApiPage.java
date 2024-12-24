package com.api.pages;

import org.json.JSONObject;

public class ApiPage {
	
	public static String requestBodyForCreateNewRepo = "{\r\n"
			+ "    \"name\":\"SampleRequestFromRestAssured\",\r\n"
			+ "    \"description\":\"Sample Project Repository\",\r\n"
			+ "    \"private\":true\r\n"
			+ "}";
	
	public static String createNewRepo(String repoName, boolean visibility) {
		JSONObject obj = new JSONObject();
		obj.put("name", repoName);
		obj.put("description", "Sample description");
		obj.put("private", visibility);
		return obj.toString();
	}

}

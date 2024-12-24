package com.framework.commons;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.framework.reports.Reports;
import com.framework.utilities.ReadProp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiCommons extends Reports{
	public Properties prop = ReadProp.readData("Config.properties");
	
	// print /log events in the report
	public void log(String status, String message) {
		if (status.equalsIgnoreCase("pass"))
			Reports.logger.pass(message);
		else if (status.equalsIgnoreCase("fail"))
			Reports.logger.fail(message);
		else if (status.equalsIgnoreCase("info"))
			Reports.logger.info(message);
		else if (status.equalsIgnoreCase("warning"))
			Reports.logger.warning(message);
	}
	
	//method to get the response
	public Response getResponse(String requestType, String endPoint, String requestBody) {
//		Response tokenResponse = given().when().get(endPoint);
//		String token2 = tokenResponse.getBody().jsonPath().getString("token");
		Response response = null;
		RestAssured.baseURI = prop.getProperty("baseUrl");
		String token ="Bearer "+ prop.getProperty("token");
		String owner = prop.getProperty("owner");
		requestBody.toUpperCase();
		switch(requestType) {
		
		case "GET" :
			response = given().headers("Authorization",token).when().get(endPoint);
			break;
		case "POST" :
			response = given().headers("Authorization",token).body(requestBody).when().post(endPoint);
			break;
		case "PUT" :
			response = given().headers("Authorization",token).body(requestBody).when().put(endPoint);
			break;
		case "PATCH" :
			response = given().headers("Authorization",token).body(requestBody).when().patch(endPoint);
			break;
		case "DELETE" :
			response = given().headers("Authorization",token).when().delete(endPoint);
			break;
		default:
				Assert.fail("Invalid Request Type");
		}	
		log("info",response.getBody().asPrettyString());
		return response;
	}
	
	//method to verify status code
	public void verifyStatusCode(Response response, int expStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expStatusCode);
		log("pass","Response code is : "+response.getStatusCode());
	}
	
	//method to verify status message
	public void verifyStatusMessage(Response response, String expStatusMessage) {
		Assert.assertTrue(response.getStatusLine().contains(expStatusMessage));
		log("pass","Response Status Line is : "+response.getStatusLine());
	}
	
	//method to verify response time
	public void verifyResponseTime(Response response, int expResponseTime) {
		log("info","Response Time is : "+response.getTimeIn(TimeUnit.SECONDS));
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <=expResponseTime );
		log("pass","Response Time is : "+response.getTimeIn(TimeUnit.SECONDS));
	}
	
	//method to verify response body
	public void verifyResponseBody(Response response, String key, String expValue) {
		String actualValue = response.getBody().jsonPath().getString(key);
		Assert.assertEquals(actualValue, expValue);
		log("pass","Response Body Values for "+key+" is : "+actualValue);
	}
	
	//method to verify key availability in response body
	public void verifyKeyIsAvailable(Response response, String key) {
		Assert.assertNotNull(response.getBody().jsonPath().get(key));
		log("pass","Response Body contains the key "+key);
	}

}

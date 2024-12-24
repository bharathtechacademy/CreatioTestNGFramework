package com.application.tests;

import org.testng.annotations.Test;

import com.api.pages.ApiPage;
import com.framework.commons.ApiCommons;

import io.restassured.response.Response;

public class ApiTest extends ApiCommons{
	
	@Test(priority=1)
	public void createNewRepositoryForAuthorizedUser() {
		Response response = getResponse("POST","/user/repos",ApiPage.createNewRepo("NewRestAssuredRepo", true));
		verifyStatusCode(response, 201);
		verifyStatusMessage(response, "Created");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "NewRestAssuredRepo");
		verifyResponseBody(response, "description", "Sample description");
	}
	
	@Test(priority=2)
	public void getRepositoryForAuthorizedUser() {
		Response response = getResponse("GET","/repos/"+prop.getProperty("owner")+"/NewRestAssuredRepo","");
		verifyStatusCode(response, 200);
		verifyStatusMessage(response, "OK");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "NewRestAssuredRepo");
		verifyResponseBody(response, "description", "Sample description");
	}
	
	@Test(priority=3)
	public void deleteRepositoryForAuthorizedUser() {
		Response response = getResponse("DELETE","/repos/"+prop.getProperty("owner")+"/NewRestAssuredRepo","");
		verifyStatusCode(response, 204);
		verifyStatusMessage(response, "No Content");
		verifyResponseTime(response, 2);		
	}

}

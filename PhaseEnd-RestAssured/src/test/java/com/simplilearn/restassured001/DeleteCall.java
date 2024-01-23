package com.simplilearn.restassured001;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class DeleteCall {
	private static final String BASE_URL = "https://petstore.swagger.io";
	private static final Logger logger = Logger.getLogger(DeleteCall.class);
	
	@Test(description = "Test for executing DELETE request using rest assured")
	public void putRequestTest() { 
		logger.info("Start :: Delete user by {userId} test.");
		int petId = 2003;
		
		logger.info("DELTE : URL "+BASE_URL + "/v2/pet/"+petId);
		// create user post data				
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.delete("/v2/pet/"+petId).then()
		.assertThat().statusCode(200);
		
		logger.info("End :: Delete user test.");
	}

}

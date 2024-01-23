package com.simplilearn.restassured006;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetCall {
	private static final String BASE_URL = "https://petstore.swagger.io";
	private static final Logger logger = Logger.getLogger(GetCall.class);
	
	@Test(description = "Test for executing GET request using rest assured")
	public void getRequestTest() {
		logger.info("Start :: GET All users test.");
		String[] s = {"available", "sold", "pending"};
		logger.info("GET : URL "+BASE_URL + "/v2/user/logout");
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/v2/user/logout").then().statusCode(200)
		.and()
		.assertThat()
		.body("message", equalTo("ok"));
		
		
		String response = RestAssured.given().baseUri(BASE_URL)
		.when().get("/v2/user/logout").getBody().asString();
		
		logger.info("Reponse :: "+response);
		logger.info("End :: GET All users test.");
	}
	


}

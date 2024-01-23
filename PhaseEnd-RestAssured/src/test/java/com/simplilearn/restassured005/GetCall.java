package com.simplilearn.restassured005;

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
		logger.info("GET : URL "+BASE_URL + "/v2/pet/findByStatus");
		RestAssured.given().baseUri(BASE_URL)
		.when().get("v2/pet/findByStatus?status="+s[0]).then().statusCode(200)
		.and()
		.assertThat()
		.body("[0].status", equalTo(s[0]));
		
		String response = RestAssured.given().baseUri(BASE_URL).queryParam ("status",s[0])
		.when().get("/vs/pet/findByStatus?status="+s[0]).getBody().asString();
		
		logger.info("Reponse :: "+response);
		logger.info("End :: GET All users test.");
	}
	

}

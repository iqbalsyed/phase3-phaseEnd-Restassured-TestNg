package com.simplilearn.restassured003;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetCall {
	private static final String BASE_URL = "https://petstore.swagger.io";
	private static final Logger logger = Logger.getLogger(GetCall.class);
	
	@Test(description = "Test for executing GET request using rest assured")
	public void getRequestTest() {
			logger.info("Start :: GET All users test with query parms.");
			// paginated response params
			String name  = "string";
			
			 String response = RestAssured.given().baseUri(BASE_URL)
			.when().get("/v2/user/"+name).getBody().asString();
			 
			 /** System.out.println(response);  **/
			logger.info("GET : URL "+BASE_URL + "/"+name);
			
			RestAssured.given().baseUri(BASE_URL)
			.when().get("/v2/user/"+name).then().statusCode(200)
			.and()
			.assertThat()
			.body("username", equalTo(name)).and()
			.body("email", equalTo(name)).and()
			.body("userStatus", equalTo(0));
			
			logger.info("Reponse :: "+response);
			logger.info("End :: GET All users test with query parms.");
		}

}

package com.simplilearn.restassured001;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostCall {
	private static final String BASE_URL = "https://petstore.swagger.io/v2";
	private static final Logger logger = Logger.getLogger(PostCall.class);

	@Test(description = "Test for executing POST request using rest assured")
	public void postRequestTest() {
		logger.info("Start :: POST request test using rest assured.");

		String response = null;
		try {
			// create user post data
			PostData postData = new PostData(2003, "available");
			logger.info("Request Object:: "+postData);
			
			RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData).post("/pet")
					.then().assertThat().statusCode(200).and().assertThat().body("id", equalTo(postData.id)).and().assertThat().body("status", equalTo(postData.status));

			response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.post("/pet").getBody().asString();
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}
		
		logger.info("Response Object:: "+response);
		logger.info("End :: POST request test using rest assured.");
	}
}

class PostData {
	
	public final int id;
	public final String status;
	

	public PostData(int id, String status) {
		super();
		
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return "PostData [id=" + id + ", status=" + status + "]";
	}

}

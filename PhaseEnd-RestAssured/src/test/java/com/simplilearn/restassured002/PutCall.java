package com.simplilearn.restassured002;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutCall {
	private static final String BASE_URL = "https://petstore.swagger.io/v2";
	private static final Logger logger = Logger.getLogger(PutCall.class);

	@Test(description = "Test for executing PUT request using rest assured")
	public void putRequestTest() {
		logger.info("Start :: PUT request test using rest assured.");

		String response = null;
		
		try {
			
			PostData postData = new PostData(20021,"available_DEV", "available_QA","available_PROD");
			logger.info("Request Object:: "+postData);
			
			RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.put("/pet").then().assertThat().statusCode(200).and().assertThat()
					.body("id", equalTo(postData.id));

			response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.post("/pet").getBody().asString();
			Assert.assertTrue(response.contains("id"));
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}

		logger.info("Response :: " + response);
		logger.info("End :: PUT request test using rest assured.");
	}

}
class PostData {
	
	
	
	public final int id;
	public final String DEV;
	public final String QA;
	public final String PROD;

	public PostData(int id, String DEV, String QA, String PROD) {
		super();
		
		this.id = id;
		this.DEV = DEV;
		this.QA = QA;
		this.PROD = PROD;
		
	}

	@Override
	public String toString() {
		return "PostData [id=" + id + ",DEV=" + DEV +",QA=" + QA +"PROD=" + PROD +"]";
	}
}

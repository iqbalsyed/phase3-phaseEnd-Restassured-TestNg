package com.simplilearn.restassured004;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAuth {
	private static final String BASE_URL = "https://petstore.swagger.io/v2";
	private static final Logger logger = Logger.getLogger(GetAuth.class);
	
	@Test(description = "Test for executing GET request using rest assured")
	public void testAuthenticationToken() { 

		logger.info("Start :: Authentication with rest assured.");
		
		logger.info("POST : URL "+BASE_URL + "/user/login");
		
		// create user post data
		User user = new User("Uname001" ,"@tt!tude");
		String regex = ".*logged in user session:$";
		logger.info("Request Object :: "+user);
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.log().uri()  // request logs
		.get("/user/login?username="+user.username+"&password="+user.password).then()
		.log().body()  // response logs
		.assertThat().statusCode(200);
		
		String response = RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.post("/user/login").getBody().asString();		
		/**  System.out.println(response);  **/
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/user/login").then().statusCode(200)
		.and()
		.assertThat()
		.body("message", notNullValue());
		//.body("message", equalTo(regex));
		
		logger.info("Response Object :: "+response);
		logger.info("End :: Authentication with rest assured.");
}

@Test
class User {
	
	public String username;
	public String password;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}

}

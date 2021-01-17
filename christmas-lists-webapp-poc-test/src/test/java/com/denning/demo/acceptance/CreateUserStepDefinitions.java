package com.denning.demo.acceptance;

import static org.junit.Assert.assertEquals;

import com.denning.demo.model.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateUserStepDefinitions
{

	/** The user */
	private final User user = new User();

	/** The local host */
	private static final String LOCAL_HOST = "http://localhost";

	/** The port */
	private static final int PORT = 8080;

	/** The contentType */
	private static final String APPLICATION_JSON = "application/json";

	/** The create user path */
	private static final String CREATE_USER_POST = "/v1/user";
	
	/** The response */
	private Response response;
	
	
	@Given("^the username is (.+)$")
	public void the_username_is(final String username)
	{
		user.setUsername(username);
	}
	
	@Given("^the password is (.+)$")
	public void the_password_is(final String password)
	{
		user.setPassword(password);
	}
	
	@Given("^the firstName is (.+)$")
	public void the_firstName_is(final String firstName)
	{
		user.setFirstName(firstName);
	}
	
	@When("add user operation is run")
	public void add_user_operation_is_run()
	{
		this.dataCleanup(user.getUsername());
		
		this.response = RestAssured
			.given()
				.baseUri(LOCAL_HOST)
				.port(PORT)
				.contentType(APPLICATION_JSON)
				.basePath(CREATE_USER_POST)
				.body(user)
			.when()
				.post();
		
		response.then().log().ifValidationFails();
			
	}
	
	@Then("user will be available through the get all users request")
	public void user_will_be_available_through_the_get_all_users_request()
	{
		assertEquals(200, this.response.getStatusCode());
		// Here we should call to get all users and confirm our test user is returned.
	}
	
	/**
	 * Helper method to clean the test data from the DB.
	 * 
	 * @param username test data username
	 */
	private void dataCleanup(final String username)
	{
		RestAssured
			.given()
				.baseUri(LOCAL_HOST)
				.port(PORT)
				.contentType(APPLICATION_JSON)
				.basePath(CREATE_USER_POST)
				.queryParam("username", username)
			.when()
				.delete();

	}
	
}

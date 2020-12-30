package com.denning.demo.acceptance;

import io.cucumber.java.en.Given;

public class GivenStepDefinitions {

	@Given("^the username is (.+)$")
	public void the_username_is(final String username)
	{
		System.out.println("Hit the username step!");
		//Now do something meaningful :)
	}
	
	@Given("^the password is (.+)$")
	public void the_password_is(final String password)
	{
		System.out.println("Hit the password step!");
		//Now do something meaningful :)
	}
}

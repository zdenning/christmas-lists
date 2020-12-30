package com.denning.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.denning.demo.mapper.UserMapper;
import com.denning.demo.model.User;

import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-05T14:50:01.382-05:00[America/New_York]")

@Controller
@RequestMapping("${openapi.christmasLists.base-path:/v1}")
public class UserApiController implements UserApi
{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserApiController(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

    /**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public ResponseEntity<List<User>> userAllGet()
	{
		HttpStatus status = HttpStatus.PROCESSING;
		
		final List<User> users = jdbcTemplate.query("select * from USERS", new UserMapper());
		
		status = HttpStatus.OK;
		
        return new ResponseEntity<List<User>>(users, status);
    }

    /**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public ResponseEntity<String> userLoginGet(@NotNull @ApiParam(value = "The user name for login", required = true)
			@Valid @RequestParam(value = "username", required = true) String username,
			@NotNull @ApiParam(value = "The password for login in clear text", required = true)
			@Valid @RequestParam(value = "password", required = true) String password)
	{
		
		final List<User> users = jdbcTemplate.query("select * from USERS", new UserMapper()); //Refactor to query based on 
																							  //username and password. Return if successful.
		
		System.out.println("Input: " + username + "/" + password);
		System.out.println("Output: " + users);
		for (User user : users)
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return ResponseEntity.ok().build();
			
		
        return ResponseEntity.badRequest().body("Invalid username/password");
    }

    /**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public ResponseEntity<Void> userLogoutGet()
	{
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

    }

    /**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public ResponseEntity<String> userPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody final User user)
	{
    	try
    	{
    		jdbcTemplate.update("insert into USERS(USERNAME, PASSWORD, FIRST_NAME) values (?,?,?)",
    				user.getUsername(),
    				user.getPassword(),
    				user.getFirstName());
    	}
    	catch (final Exception e)
    	{
    		return ResponseEntity.badRequest().body("That user already exists!");
    	}
		
        return ResponseEntity.ok().build();
    }
}

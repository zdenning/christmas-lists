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

import com.denning.demo.mapper.ListMapper;
import com.denning.demo.model.ChristmasList;
import com.denning.demo.model.Item;
import com.denning.demo.validator.Validator;

import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.validation.Valid;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-05T14:50:01.382-05:00[America/New_York]")

@Controller
@RequestMapping("${openapi.christmasLists.base-path:/v1}")
public class ListApiController implements ListApi
{
	private JdbcTemplate jdbcTemplate;
	
	private Validator validator;
	
	@Autowired
	public ListApiController(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.validator = new Validator(this.jdbcTemplate);
	}
    
    /**
     * {@inheritDoc}
     */
	@Transactional
    @Override
    public ResponseEntity<ChristmasList> listGet(@ApiParam(value = "Username for the list being retrieved.") @Valid
        	@RequestParam(value = "username", required = true) String username, 
        	@ApiParam(value = "Username for the logged in user.") @Valid
        	@RequestParam(value = "currentUser", required = true) String currentUser)
    {
    	HttpStatus status = HttpStatus.PROCESSING;
    	final ChristmasList christmasList = new ChristmasList();
    	
    	final List<Item> itemList = jdbcTemplate.query("select * from ITEMS where USERNAME = ?",
    			new Object[]{username}, new ListMapper());
    	
    	christmasList.setList(itemList);
    	christmasList.setUsername(username);
    	
    	if (username.equals(currentUser))
    	{
    		christmasList.getList().stream().forEach(item -> {
    			item.setBought(null);
    		});
    	}
    	
    	status = HttpStatus.OK;
    	
        return new ResponseEntity<ChristmasList>(christmasList, status);
    }
    
    /**
     * {@inheritDoc}
     */
	@Transactional
    @Override
    public ResponseEntity<String> listPost(@ApiParam(value = "Username for the list being retrieved.") @Valid
        	@RequestParam(value = "username", required = false) String username,  @Valid
        	@RequestBody final Item item)
    {
    	if (!validator.validateUser(username))
    		return ResponseEntity.badRequest().body("Not a valid user");
    	
    	try
    	{
    		jdbcTemplate.update("insert into ITEMS(USERNAME, NAME, NOTES) values (?,?,?)",
        			username,
        			item.getName(),
        			item.getLinkOrNotes());
    	}
    	catch (final Exception e)
    	{
    		return ResponseEntity.badRequest().body("That item already exists in that list!");
    	}
    	
        return ResponseEntity.ok().build();
    }

    /**
     * {@inheritDoc}
     */
	@Transactional
    @Override
	public ResponseEntity<String> listBuyPost(
			@ApiParam(value = "" ,required = true ) @Valid @RequestBody final Item item,
			@ApiParam(value = "Username for the list being retrieved.") @Valid @RequestParam(value = "username", required = true) final String username,
			@ApiParam(value = "The current user.") @Valid @RequestParam(value = "currentUser", required = true) final String currentUser)
	{
		if (!validator.validateUser(username))
    		return ResponseEntity.badRequest().body("Not a valid user");
		
		final ResponseEntity<ChristmasList> list = this.listGet(username, currentUser);
		for (final Item it : list.getBody().getList())
		{
			if (item.getName().equals(it.getName()) && it.getBought())
			{
				return ResponseEntity.badRequest().body("Item has already been bought!");
			}	
		}

		final int result = jdbcTemplate.update("update ITEMS set BOUGHT = true where USERNAME = ? and NAME = ?",
				username,
				item.getName());
		
		if (result != 1)
			return ResponseEntity.badRequest().body("Item does not exist");
		
        return ResponseEntity.ok().build();

    }
}

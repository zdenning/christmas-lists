/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.denning.demo.api;

import com.denning.demo.model.Item;
import io.swagger.annotations.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-12-05T14:50:01.382-05:00[America/New_York]")

@Validated
@Api(value = "list", description = "the list API")
public interface ListApi
{

    /**
     * GET /list
     * Retrieves the list for specified user.
     *
     * @param username Username for the list being retrieved. (optional)
     * @return Successful Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "listGet", notes = "Retrieves the list for specified user.", response = Item.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Operation", response = Item.class) })
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Item>> listGet(
    	@ApiParam(value = "Username for the list being retrieved.") @Valid @RequestParam(value = "username", required = true) String username, 
    	@ApiParam(value = "Username for the logged in user.") @Valid @RequestParam(value = "currentUser", required = true) String currentUser);


    /**
     * POST /list
     * Adds the list for the logged in user.
     *
     * @param item  (required)
     * @return Successful Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "listPost", notes = "Adds the list for the logged in user.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Operation") })
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/list",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> listPost(
    	@ApiParam(value = "Username for the list being added to.") @Valid @RequestParam(value = "currentUser", required = true) String currentUser,  @Valid @RequestBody final Item item);

    /**
     * POST /list/buy
     * Adds the list for the logged in user.
     *
     * @param item  (required)
     * @param username Username for the list being retrieved. (optional)
     * @param currentUser Username for the list being added to. (optional)
     * @return Successful Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "listBuyPost", notes = "Adds the list for the logged in user.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Operation") })
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/list/buy",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> listBuyPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Item item,
    		@ApiParam(value = "Username for the list being retrieved.") @Valid
    		@RequestParam(value = "username", required = false) String username,
    		@ApiParam(value = "Username for the list being added to.")@Valid
    		@RequestParam(value = "currentUser", required = false) String currentUser);
}

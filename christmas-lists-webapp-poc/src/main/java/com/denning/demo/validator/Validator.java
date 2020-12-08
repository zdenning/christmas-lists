package com.denning.demo.validator;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.denning.demo.mapper.UserMapper;

public class Validator
{
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param jdbcTemplate
	 */
	public Validator(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional
	public boolean validateUser(final String username)
	{
		try 
		{
			jdbcTemplate.queryForObject("select * from USERS where USERNAME = ?",
					new Object[]{username}, new UserMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return false;
		}
		return true;
	}
	
}

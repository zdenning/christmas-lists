package com.denning.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.denning.demo.model.Item;

public class ListMapper implements RowMapper<Item>
{
	
	public Item mapRow(final ResultSet rs, int rowNum) throws SQLException
	{
		final Item item = new Item();
		item.setName(rs.getString("NAME"));
		item.setLinkOrNotes(rs.getString("NOTES"));
		item.setBought(rs.getBoolean("BOUGHT"));
		
		return item;
		
	}
}

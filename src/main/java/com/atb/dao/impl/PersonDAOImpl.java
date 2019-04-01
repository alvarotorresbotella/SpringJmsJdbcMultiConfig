package com.atb.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atb.dao.PersonDAO;
import com.atb.model.Person;

public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger LOGGER = Logger.getLogger(PersonDAOImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertPerson(Person person) {
		
		LOGGER.info("PersonDAOImpl.insertPerson");
		
		String sql = "INSERT INTO person " +
				"(name, age) VALUES (?, ?)";
	 
			jdbcTemplate = new JdbcTemplate(dataSource);
	 
			jdbcTemplate.update(sql, new Object[] { 
					person.getName(), person.getAge()  
			});

	}

}

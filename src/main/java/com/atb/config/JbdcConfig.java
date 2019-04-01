package com.atb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.atb.dao.impl.MsgOrigenDAOImpl;
import com.atb.dao.impl.PersonDAOImpl;


@Configuration
public class JbdcConfig {
	
	@Bean
	public DriverManagerDataSource dataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/activemq");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		
		return dataSource;
	}
	
	@Bean
	public PersonDAOImpl personDAO()
	{
		PersonDAOImpl personDAO = new PersonDAOImpl();
		personDAO.setDataSource(dataSource());
		return personDAO;
	}
	
	@Bean 
	public MsgOrigenDAOImpl msgOrigenDAO()
	{
		MsgOrigenDAOImpl msgOrigenDAO=new MsgOrigenDAOImpl();
		msgOrigenDAO.setDataSource(dataSource());
		return msgOrigenDAO;
	}
	

}

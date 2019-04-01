package com.atb.service;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.atb.config.AppConfig;
import com.atb.dao.PersonDAO;
import com.atb.dao.impl.PersonDAOImpl;
import com.atb.model.Person;

public class PersonSrvImpl {
	
	private static final Logger LOGGER = Logger.getLogger(PersonSrvImpl.class);
	
	private AbstractApplicationContext context;
	private PersonDAO dao;
	
	public void save(Person person)
	{
		LOGGER.info("PersonSrvImpl.save");
		context= new AnnotationConfigApplicationContext(AppConfig.class);
		PersonDAO dao=(PersonDAOImpl) context.getBean("personDAO");
		dao.insertPerson(person);
		
	}

}

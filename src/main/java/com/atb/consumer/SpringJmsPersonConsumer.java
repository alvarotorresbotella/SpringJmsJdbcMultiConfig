package com.atb.consumer;

import java.util.Map;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.jms.core.support.JmsGatewaySupport;

import com.atb.model.Person;
import com.atb.service.PersonSrvImpl;

public class SpringJmsPersonConsumer extends JmsGatewaySupport {
	
	private static final Logger LOGGER = Logger.getLogger(SpringJmsPersonConsumer.class);
	
	public Person receiveMessage() throws JMSException {
		
		LOGGER.info("Recibiendo y conviertiendo el mensaje en la persona");
		Map map = (Map) getJmsTemplate().receiveAndConvert();
		Person person = new Person((String) map.get("name"), (Integer) map.get("age"));
		PersonSrvImpl srv=new PersonSrvImpl();
		srv.save(person);
		return person;	
	}
}

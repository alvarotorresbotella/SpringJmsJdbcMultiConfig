package com.atb.producer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jms.core.support.JmsGatewaySupport;

import com.atb.model.Person;

public class SpringJmsPersonProducer extends JmsGatewaySupport {
	public void sendMessage(final Person person) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", person.getName());
		map.put("age", person.getAge());
		getJmsTemplate().convertAndSend(map);
		System.out.println("Producer sends " + person);
	}
}

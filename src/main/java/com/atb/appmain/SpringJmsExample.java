package com.atb.appmain;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atb.consumer.SpringJmsConsumer;
import com.atb.producer.SpringJmsProducer;

public class SpringJmsExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		BrokerService broker = BrokerFactory.createBroker(new URI(
				"broker:(tcp://localhost:61616)"));
		broker.start();
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");	
		try {
			SpringJmsProducer springJmsProducer = (SpringJmsProducer) context
					.getBean("springJmsProducer");
			springJmsProducer.sendMessage("@tb jms msg ActiveMq");

			SpringJmsConsumer springJmsConsumer = (SpringJmsConsumer) context
					.getBean("springJmsConsumer");
			System.out.println(" Consumer receive : "+springJmsConsumer.receiveMessage());
		} finally {
			broker.stop();
			context.close();
		}
	}
}

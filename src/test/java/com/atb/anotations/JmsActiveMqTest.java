package com.atb.anotations;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import javax.jms.JMSException;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.atb.config.AppConfig;
import com.atb.consumer.SpringJmsConsumer;
import com.atb.producer.SpringJmsProducer;

public class JmsActiveMqTest {
	
	private static final Logger LOGGER = Logger.getLogger(JmsActiveMqTest.class);

	private BrokerService broker;
	private AbstractApplicationContext context; 

	@Before
	public void loadConfig()
	{

		try {
			context= new AnnotationConfigApplicationContext(AppConfig.class);
			broker=BrokerFactory.createBroker(new URI(
					"broker:(tcp://localhost:61616)"));
			broker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	


	@Test
	public void sendAndReciveMsg() throws JMSException, IOException {
		LOGGER.info("Ejecutando JmsActiveMqTest.sendAndReciveMsg()");
		SpringJmsProducer springJmsProducer = (SpringJmsProducer) context
				.getBean("springJmsProducer");
		//springJmsProducer.sendMessage("@tb jms msg ActiveMq");
		springJmsProducer.sendAllMessages();
		SpringJmsConsumer springJmsConsumer = (SpringJmsConsumer) context
				.getBean("springJmsConsumer");
			assertEquals(springJmsConsumer.receiveMessage(), "msg orig 1 upd");
	}
	
/*	@Test
	public void sendAndReceivePersonMsg() throws JMSException{
		LOGGER.info("Ejecutando JmsActiveMqTest.sendAndReciveMsg()");
		SpringJmsPersonProducer springJmsPersonProducer=(SpringJmsPersonProducer)
				context.getBean("springJmsPersonProducer");
		springJmsPersonProducer.sendMessage(new Person("Alvaro",44));
		SpringJmsPersonConsumer springJmsPersonConsumer =(SpringJmsPersonConsumer )
				context.getBean("springJmsPersonConsumer");
		Person persona=new Person();
		persona=springJmsPersonConsumer.receiveMessage();
		LOGGER.info("Recibiendo la persona(JmsActiveMqTest) : "+persona);
		LOGGER.info("Nombre de la persona : "+persona.getName());
		assertEquals(persona.getName(),"Alvaro");
	}*/
		
	@After
	public void close() {
		try {
				broker.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
	}
	
	
	
	
	
}

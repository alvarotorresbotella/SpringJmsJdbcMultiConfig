package com.atb.filleconfig;

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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atb.consumer.SpringJmsConsumer;
import com.atb.producer.SpringJmsProducer;





public class JmsActiveMqTest {
	
	private static final Logger LOGGER = Logger.getLogger(JmsActiveMqTest.class);
	private BrokerService broker;
	private ClassPathXmlApplicationContext context;

	@Before
	public void loadConfig()
	{

		try {
			context=new ClassPathXmlApplicationContext("applicationContext.xml");
			broker=BrokerFactory.createBroker(new URI(
					"broker:(tcp://localhost:61616)"));
			broker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void sendAndReciveMsg() throws JMSException, IOException {
	LOGGER.info("Ejecutando sendAndReciveMsg() JunitTest");
		SpringJmsProducer springJmsProducer = (SpringJmsProducer) context
				.getBean("springJmsProducer");
		springJmsProducer.sendMessage("@tb jms msg ActiveMq");
		SpringJmsConsumer springJmsConsumer = (SpringJmsConsumer) context
				.getBean("springJmsConsumer");
			assertEquals(springJmsConsumer.receiveMessage(), "@tb jms msg ActiveMq");
	}
		
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

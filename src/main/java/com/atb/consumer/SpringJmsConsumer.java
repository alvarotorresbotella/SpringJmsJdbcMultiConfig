package com.atb.consumer;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import com.atb.service.FileSrvImpl;

public class SpringJmsConsumer {
	
	private static final Logger LOGGER = Logger.getLogger(SpringJmsConsumer.class);
	private JmsTemplate jmsTemplate;
	private Destination destination;
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public String receiveMessage() throws JMSException, IOException{
		TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);	
		String msgText=textMessage.getText();
		FileSrvImpl csvSrv=new FileSrvImpl();
		csvSrv.writeCsvMsg(msgText);
		LOGGER.info("Consumer recieve : "+msgText);
		return msgText;
	}
}

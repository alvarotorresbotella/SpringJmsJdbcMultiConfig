package com.atb.producer;



import java.util.ArrayList;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.atb.model.Msg;
import com.atb.service.MsgOrigenSrvImpl;

public class SpringJmsProducer {
	
	 private static final Logger LOGGER = Logger.getLogger(SpringJmsProducer.class);
	    
	private JmsTemplate jmsTemplate;
	private Destination destination;
	private MsgOrigenSrvImpl msgOrigSrv;
	
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

	public void sendMessage(final String msg) {
		LOGGER.info("Producer sends : " + msg);
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}});		
	}
	
	public void sendAllMessages()
	{
		msgOrigSrv=new MsgOrigenSrvImpl();
		ArrayList<Msg> msgPendingList=msgOrigSrv.pendingMsgs();
		for(Msg x:msgPendingList)
		{
			sendMessage(x.getMsg());
		}
	}
	
}

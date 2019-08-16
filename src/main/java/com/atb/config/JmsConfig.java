package com.atb.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.atb.consumer.SpringJmsConsumer;
import com.atb.consumer.SpringJmsPersonConsumer;
import com.atb.producer.SpringJmsPersonProducer;
import com.atb.producer.SpringJmsProducer;
import com.atb.service.PersonMessageConverter;


@Configuration
public class JmsConfig {
	
	
	  private static final Logger LOGGER = Logger.getLogger(JmsConfig.class);
	  private static final String DEFAULT_BROKER_URL= "tcp://localhost:61616";
	  private static final String ORDER_QUEUE = "messageQueue1";
	  private static final String ORDER_QUEUE_DEFAULT = "messageQueue2";
	  private static final long timeout = new Long("10000");

	    @Bean
	    public ConnectionFactory connectionFactory(){
	    	LOGGER.info("....JmsConfiguration.connectionFactory()....");
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
	        //connectionFactory.setTrustedPackages(Arrays.asList("com.atb"));
	        return connectionFactory;
	    }
	    
	    @Bean
	    public ActiveMQQueue messageDestination()
	    {
	    	LOGGER.info("....JmsConfiguration.messageDestination()....");
	    	ActiveMQQueue messageDestination=new ActiveMQQueue(ORDER_QUEUE);
	    	return messageDestination;
	    }
	    
	    @Bean
	    public JmsTemplate jmsTemplate(){
	    	LOGGER.info("....JmsConfiguration.jmsTemplate()....");
	        JmsTemplate template = new JmsTemplate();
	        template.setConnectionFactory(connectionFactory());
	        template.setReceiveTimeout(timeout);
	        template.setDefaultDestinationName(ORDER_QUEUE);
	        return template;
	    }

	    @Bean SpringJmsProducer springJmsProducer()
	    {
	    	LOGGER.info("....JmsConfiguration.springJmsProducer()....");
	    	SpringJmsProducer producer=new SpringJmsProducer();
	    	producer.setJmsTemplate(jmsTemplate());
	    	producer.setDestination(messageDestination());
	    	return producer;
	    }
	    
	    @Bean SpringJmsConsumer springJmsConsumer()
	    {
	    	LOGGER.info("....JmsConfiguration.springJmsConsumer()....");
	    	SpringJmsConsumer consumer=new SpringJmsConsumer();
	    	consumer.setJmsTemplate(jmsTemplate());
	    	consumer.setDestination(messageDestination());
	    	return consumer;
	    }
	    
	    
	    @Bean
	    public JmsTemplate jmsTemplateDefaultMsgDestination(){
	    	LOGGER.info("....JmsConfiguration.jmsTemplateMsgTranformation())....");
	        JmsTemplate template = new JmsTemplate();
	        template.setConnectionFactory(connectionFactory());
	        template.setReceiveTimeout(timeout);
	        template.setDefaultDestinationName(ORDER_QUEUE_DEFAULT);
	        return template;
	    }

	    
	    @Bean 
	    SpringJmsPersonProducer springJmsPersonProducer()
	    {
	    	LOGGER.info("....JmsConfiguration.springJmsPersonProducer())....");
	    	SpringJmsPersonProducer springJmsPersonProducer= new SpringJmsPersonProducer();
	    	springJmsPersonProducer.setJmsTemplate(jmsTemplateDefaultMsgDestination());
	    	//springJmsPersonProducer.setJmsTemplate(jmsTemplate());
	    	return springJmsPersonProducer;
	    }
	    
	    @Bean 
	    SpringJmsPersonConsumer springJmsPersonConsumer()
	    {
	    	SpringJmsPersonConsumer springJmsPersonConsumer= new SpringJmsPersonConsumer();
	    	springJmsPersonConsumer.setJmsTemplate(jmsTemplateDefaultMsgDestination());
	    	//springJmsPersonConsumer.setJmsTemplate(jmsTemplate());
	    	return springJmsPersonConsumer;
	    }
	    
	    
	    
	    @Bean
	    public JmsTemplate jmsTemplateMsgTranformation(){
	    	LOGGER.info("....JmsConfiguration.jmsTemplateMsgTranformation())....");
	        JmsTemplate template = new JmsTemplate();
	        template.setConnectionFactory(connectionFactory());
	        template.setReceiveTimeout(timeout);
	        template.setDefaultDestinationName(ORDER_QUEUE);
	        template.setMessageConverter(personMessageConverter());
	        return template;
	    }
	    
	    
	    @Bean 
	    PersonMessageConverter personMessageConverter()
	    {
	    	return new PersonMessageConverter();
	    }
}

package com.atb.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.atb.config.AppConfig;
import com.atb.dao.MsgOrigenDAO;
import com.atb.model.Msg;

@Service
public class MsgOrigenSrvImpl {
	
private static final Logger LOGGER = Logger.getLogger(PersonSrvImpl.class);
	
	private AbstractApplicationContext context;
	private MsgOrigenDAO dao;
	private ArrayList<Msg> msgList;
	
	public ArrayList<Msg> pendingMsgs()
	{
		msgList=new ArrayList<Msg>();
		context= new AnnotationConfigApplicationContext(AppConfig.class);
		dao=(MsgOrigenDAO)context.getBean("msgOrigenDAO");
		msgList=dao.getAllPendingMsgs();
		return msgList;
		
		
	}

}

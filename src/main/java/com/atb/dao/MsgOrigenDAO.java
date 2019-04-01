package com.atb.dao;

import java.util.ArrayList;

import com.atb.model.Msg;

public interface MsgOrigenDAO {
	
	public ArrayList<Msg> getAllMsgs();
	public ArrayList<Msg> getAllPendingMsgs();
	public Msg getMsgById(int id);

}

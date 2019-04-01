package com.atb.model;

public class Msg {
	
	private int id;
	private String msg;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Msg(int id, String msg, String status) {
		super();
		this.id = id;
		this.msg = msg;
		this.status = status;
	}
	public Msg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	

}

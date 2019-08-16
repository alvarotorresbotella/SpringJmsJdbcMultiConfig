package com.atb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.atb.dao.MsgOrigenDAO;
import com.atb.model.Msg;

public class MsgOrigenDAOImpl implements MsgOrigenDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<Msg> getAllMsgs() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM msgorig";
	 
		ArrayList<Msg> msgList = new ArrayList<Msg>();
	 
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			Msg msg = new Msg();
			msg.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			msg.setMsg((String)row.get("msg"));
			msg.setStatus((String)row.get("status"));
			msgList.add(msg);
		}
	 
		return msgList;
	}


	public ArrayList<Msg> getAllPendingMsgs() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM msgorig where status='01'";
	 
		ArrayList<Msg> msgList = new ArrayList<Msg>();
	 
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			Msg msg = new Msg();
			msg.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			msg.setMsg((String)row.get("msg"));
			msg.setStatus((String)row.get("status"));
			msgList.add(msg);
		}
	 
		return msgList;
	}


	public Msg getMsgById(int id) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT msg,status FROM msgorig WHERE id = ?";
	 
		Msg msg = (Msg)jdbcTemplate.queryForObject(
				sql, new Object[] { id }, Msg.class);
		return msg;
	}

}

package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Ticket;


public class TicketDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static TicketDao ticketDao = new TicketDao();
	private TicketDao() {}
	
	public static TicketDao getInstance(){
		return ticketDao;
	}
	
	public List<Ticket> getTicketList(int schId){
		SqlSession mapper = sqlFactory.openSession();
		List<Ticket> list = mapper.selectList("getTicketList", schId);
		mapper.close();
		return list;
	}
	
	public void insertTicket(Ticket bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("insertTicket", bean);
		mapper.commit();
		mapper.close();
	}

	public int getTicketPrice(int schId) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getTicketPrice", schId);
		mapper.close();
		return n;
	}

	public int getTicketId(int seatId) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getTicketId", seatId);
		mapper.close();
		return n;
	}

	public Ticket getTicketOne(int ticketId) {
		SqlSession mapper = sqlFactory.openSession();
		Ticket bean = mapper.selectOne("getTicketOne", ticketId);
		mapper.close();
		return bean;
	}

}

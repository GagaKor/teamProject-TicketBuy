package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Seat;

public class SeatDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static SeatDao seatDao = new SeatDao();
	private SeatDao() {}
	
	public static SeatDao getInstance(){
		return seatDao;
	}
	
	public int insertSeat(Seat bean) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.insert("insertSeat", bean);
		mapper.commit();
		mapper.close();
		return n;
	}

	public List<Seat> getSeatList(int schId) {
		SqlSession mapper =sqlFactory.openSession();
		List<Seat> list = mapper.selectList("getSeatList", schId);
		mapper.close();
		return list;
	}
	public int getRemainSeat(int schId) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getRemainSeat", schId);
		mapper.close();
		return n;
	}

	public Seat getSeatOne(int schId) {
		SqlSession mapper = sqlFactory.openSession();
		Seat bean = mapper.selectOne("getSeatOne", schId);
		mapper.close();
		return bean;
	}

	public List<Seat> getRemainSeatList(int schId) {
		SqlSession mapper = sqlFactory.openSession();
		List<Seat> list = mapper.selectList("getRemainSeatList",schId);
		 mapper.close();
		return list;
	}

	public void updateIssue(int seatId) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updateIssue",seatId);
		mapper.commit();
		mapper.close();
	}
	
}

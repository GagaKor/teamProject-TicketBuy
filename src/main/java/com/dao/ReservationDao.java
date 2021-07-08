package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Reservation;


public class ReservationDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static ReservationDao reservationDao = new ReservationDao();
	private ReservationDao() {}
	
	public static ReservationDao getInstance(){
		return reservationDao;
	}

	public void insertReservation(Reservation bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("insertReservation", bean);
		mapper.commit();
		mapper.close();
	}
	
	public List<Reservation> getAllReservationNo(int idx) {
		SqlSession mapper = sqlFactory.openSession();
		List<Reservation> list = mapper.selectList("getAllReservationNo", idx);
		mapper.close();
		return list;
	}
	public int getTicketIdToReservationNo(int ticketId) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getTicketIdToReservationNo", ticketId);
		mapper.close();
		return n;
	}

}

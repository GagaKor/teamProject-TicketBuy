package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Sch;

public class SchDao {
	SqlSessionFactory factory = SqlSessionUtil.getSessionFactory();
	private static SchDao schDao = new  SchDao();
	private SchDao () {}
	public static SchDao getInstance() {
		return schDao;
	}
	public void insertSch(Sch bean) {
		SqlSession mapper = factory.openSession();
		mapper.insert("insertSch",bean);
		mapper.commit();
		mapper.close();
	}
	public int getMaxSchId(int performId) {
		SqlSession mapper = factory.openSession();
		int n = mapper.selectOne("getMaxSchId", performId);
		mapper.close();
		return n;
	}
	
	public List<Sch> getSearchSchList(int performId) {
		SqlSession mapper = factory.openSession();
		List<Sch> list = mapper.selectList("getSearchSchList", performId);
		mapper.close();
		return list;
	}
	public String getSeasonDate(int performId) {
		SqlSession mapper = factory.openSession();
		String seasonTime = mapper.selectOne("getSeasonTime",performId);
		mapper.close();
		return seasonTime;
	}
	public int getPlaceId(int performId) {
		SqlSession mapper = factory.openSession();
		int n = mapper.selectOne("getPlaceId", performId);
		mapper.close();
		return n;
	}
	public List<Date> getschTimeList(int performId) {
		SqlSession mapper = factory.openSession();
		List<Date> list = mapper.selectList("getschTimeList",performId);
		mapper.close();
		return list;
	}
	public void deleteSch(int schId) {
		SqlSession mapper = factory.openSession();
		mapper.delete("deleteSch",schId);
		mapper.commit();
		mapper.close();
	}
	public Sch getSchOne(int schId) {
		SqlSession mapper = factory.openSession();
		Sch bean = mapper.selectOne("getSchOne", schId);
		mapper.close();
		return bean;
	}

	
	
}

package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Place;

public class PlaceDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static PlaceDao placeDao = new PlaceDao();
	private PlaceDao() {}
	
	public static PlaceDao getInstance(){
		return placeDao;
	}
	
	public List<Place> getPlaceList(String keyword){
		SqlSession mapper =  sqlFactory.openSession();
		List<Place> list = mapper.selectList("getPlaceaddrList",keyword);
		mapper.close();
		return list;
	}

	public int insertPlace(Place bean) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.insert("insertPlace", bean);
		mapper.commit();
		mapper.close();
		return n;
	}

	public Place getPlaceOne(int placeId) {
		SqlSession mapper =sqlFactory.openSession();
		Place place = mapper.selectOne("getPlaceOne", placeId);
		mapper.close();
		return place;
	}


	
}

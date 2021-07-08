package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Actor;

public class ActorDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static ActorDao actorDao = new ActorDao();
	private ActorDao() {}
	
	public static ActorDao getInstance(){
		return actorDao;
	}
	
	public List<Actor> getActorList(int performId){
		SqlSession mapper = sqlFactory.openSession();
		List<Actor> list = mapper.selectList("getActorList", performId);
		mapper.close();
		return list;
	}
	
	public void insertActor(Actor bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("insertActor", bean);
		mapper.commit();
		mapper.close();
	}
	public void updateActor(Actor bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updateActor", bean);
		mapper.commit();
		mapper.close();
	}

	public void deleteActor(int actorId) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("deleteActor", actorId);
		mapper.commit();
		mapper.close();
	}
	
}

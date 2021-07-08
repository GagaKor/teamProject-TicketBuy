package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Member;

public class MemberDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static MemberDao memberDao = new MemberDao();
	private MemberDao(){}
	
	public static MemberDao getInstance() {
		return memberDao;
	}

	public Member login(String userid, String userpw) {
		SqlSession mapper = sqlFactory.openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid",userid);
		map.put("userpw",userpw);
		
		Member member = mapper.selectOne("login", map);
		mapper.close();
		return member;
	}

	public boolean boolCheckId(String userid) {
		SqlSession mapper = sqlFactory.openSession();
		boolean flag = mapper.selectOne("boolCheckId",userid);
		mapper.close();
		return flag;
	}
	public List<String> getAllId(){
		SqlSession mapper = sqlFactory.openSession();
		List<String> list = mapper.selectList("getAllId");
		mapper.close();
		return list;
	}
	public void insertMember(Member bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("insertMember", bean);
		mapper.commit();
		mapper.close();
	}

	public Member findUser(String phone) {
		SqlSession mapper = sqlFactory.openSession();
		Member bean = mapper.selectOne("findUser", phone);
		mapper.close();
		return bean;
	}
}

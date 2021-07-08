package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.SqlSessionUtil;
import com.vo.Perform;

public class PerformDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	private static PerformDao performDao = new PerformDao();
	private PerformDao() {}
	
	public static PerformDao getInstance(){
		return performDao;
	}
	
	public List<Perform> getPerformCategoryList(int categoryCode){
		SqlSession mapper = sqlFactory.openSession();
		List<Perform>  list = mapper.selectList("getPerformCategoryList",categoryCode);
		mapper.close();
		return list;
	}
	
		public Perform getPerformOne(int performId){	
		SqlSession mapper = sqlFactory.openSession();
		Perform perform = mapper.selectOne("getPerformOne",performId);
		mapper.close();
		return perform;
	}
	
	public void insertPerform(Perform bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("insertPerform", bean);
		mapper.commit();
		mapper.close();
	}
	public int getMaxPerformId(){
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getMaxPerformId");
		mapper.close();
		return n;
	}

	public List<Perform> getPerformAllList() {
		SqlSession mapper = sqlFactory.openSession();
		List<Perform> list = mapper.selectList("getPerformAllList");
		mapper.close();
		return list;
	}

	public String getCategoryName(int perfomId) {
		SqlSession mapper = sqlFactory.openSession();
		String categoryName = mapper.selectOne("getCategoryName", perfomId);
		mapper.close();
		return categoryName;
	}

	public void deletePerfrom(int performId) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("deletePerform", performId);
		mapper.commit();
		mapper.close();
	}

	public void updatePerform(Perform bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updatePerform", bean);
		mapper.commit();
		mapper.close();
	}

	public List<Perform> getNewest() {
		SqlSession mapper = sqlFactory.openSession();
		List<Perform> list = mapper.selectList("getNewest");
		mapper.close();
		return list;
	}
	
	
}

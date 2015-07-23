package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PK;
import com.biyanzhi.dao.PKDao;

@Repository
public class PKImpl implements PKDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int addPK(PK pk) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.addPK(pk);
	}

	public List<PK> getPKList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.getPKList(pk_time);
	}

}

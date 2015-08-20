package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.ShuoShuoPraise;
import com.biyanzhi.dao.ShuoShuoPraiseDao;

@Repository
public class ShuoShuoPraiseDaoImpl implements ShuoShuoPraiseDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(ShuoShuoPraise praise) {
		ShuoShuoPraiseDao dao = sqlSession.getMapper(ShuoShuoPraiseDao.class);
		return dao.insert(praise);
	}

	public List<ShuoShuoPraise> getPariseByShuoShuoID(int shuoshuo_id) {
		ShuoShuoPraiseDao dao = sqlSession.getMapper(ShuoShuoPraiseDao.class);
		return dao.getPariseByShuoShuoID(shuoshuo_id);
	}

}

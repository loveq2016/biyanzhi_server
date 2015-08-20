package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.ShuoShuo;
import com.biyanzhi.dao.ShuoShuoDao;

@Repository
public class ShuoShuoDaoImpl implements ShuoShuoDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(ShuoShuo shuoshuo) {
		ShuoShuoDao dao = sqlSession.getMapper(ShuoShuoDao.class);
		return dao.insert(shuoshuo);
	}

	public List<ShuoShuo> getShuoShuoList(int page) {
		ShuoShuoDao dao = sqlSession.getMapper(ShuoShuoDao.class);
		return dao.getShuoShuoList(page);
	}

}

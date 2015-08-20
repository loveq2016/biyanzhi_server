package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.ShuoShuoComment;
import com.biyanzhi.dao.ShuoShuoCommentDao;

@Repository
public class ShuoShuoCommentDaoImpl implements ShuoShuoCommentDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertComment(ShuoShuoComment comment) {
		ShuoShuoCommentDao dao = sqlSession.getMapper(ShuoShuoCommentDao.class);
		return dao.insertComment(comment);
	}

	public List<ShuoShuoComment> getCommentByShuoShuoID(int shuoshuo_id) {
		ShuoShuoCommentDao dao = sqlSession.getMapper(ShuoShuoCommentDao.class);
		return dao.getCommentByShuoShuoID(shuoshuo_id);
	}

}

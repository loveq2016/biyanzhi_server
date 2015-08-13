package com.biyanzhi.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PKResult;
import com.biyanzhi.dao.PKResultDao;

@Repository
public class PKResultDapImpl implements PKResultDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(PKResult result) {
		PKResultDao dao = sqlSession.getMapper(PKResultDao.class);
		return dao.insert(result);
	}

	public int getResultByUserIDAndPictureID(int user_id, int picture_id) {
		PKResultDao dao = sqlSession.getMapper(PKResultDao.class);
		return dao.getResultByUserIDAndPictureID(user_id, picture_id);
	}

	public int updateWinCount(int user_id, int picture_id) {
		PKResultDao dao = sqlSession.getMapper(PKResultDao.class);
		return dao.updateWinCount(user_id, picture_id);
	}

	public int updateFailCount(int user_id, int picture_id) {
		PKResultDao dao = sqlSession.getMapper(PKResultDao.class);
		return dao.updateFailCount(user_id, picture_id);
	}

}

package com.biyanzhi.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PKVote;
import com.biyanzhi.dao.PKVoteDao;

@Repository
public class PKVoteDaoImpl implements PKVoteDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int addPKVode(PKVote vote) {
		PKVoteDao dao = sqlSession.getMapper(PKVoteDao.class);
		return dao.addPKVode(vote);
	}

	public int findPKVote(PKVote vote) {
		PKVoteDao dao = sqlSession.getMapper(PKVoteDao.class);
		return dao.findPKVote(vote);
	}

}

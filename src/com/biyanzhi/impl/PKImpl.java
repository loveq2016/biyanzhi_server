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

	public List<PK> loadMorePKList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.loadMorePKList(pk_time);
	}

	public int upDatePK2(int pk_id, int pk2_user_id, String pk2_user_picture) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.upDatePK2(pk_id, pk2_user_id, pk2_user_picture);
	}

	public int upDatePK2TicketCount(int pk_id, int pk2_ticket_count) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.upDatePK2TicketCount(pk_id, pk2_ticket_count);
	}

	public int upDatePK1TicketCount(int pk_id, int pk1_ticket_count) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.upDatePK1TicketCount(pk_id, pk1_ticket_count);
	}

	public int upDatePKState(int pk_id, int pk_state, int pk_winer_user_id) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.upDatePKState(pk_id, pk_state, pk_winer_user_id);
	}

	public List<PK> getPKIngList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.getPKIngList(pk_time);
	}

	public List<PK> loadPKIngMorePKList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.loadPKIngMorePKList(pk_time);
	}

	public List<PK> getPKFinishedList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.getPKFinishedList(pk_time);
	}

	public List<PK> loadPKFinishedMorePKList(String pk_time) {
		PKDao dao = sqlSession.getMapper(PKDao.class);
		return dao.loadPKFinishedMorePKList(pk_time);
	}

}
